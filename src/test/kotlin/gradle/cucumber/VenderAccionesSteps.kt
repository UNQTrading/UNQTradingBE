package gradle.cucumber

import ar.unq.unqtrading.dto.OrdenDeVentaDTO
import ar.unq.unqtrading.entities.Accion
import ar.unq.unqtrading.entities.Empresa
import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.entities.Persona
import ar.unq.unqtrading.services.exceptions.OrdenDeVentaIncorrectaException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.Assert
import org.junit.runner.RunWith
import org.springframework.web.client.RestTemplate
import java.time.LocalDate


@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"])
class VenderAccionesSteps {
    var restTemplate = RestTemplate()
    val DEFAULT_URL = "http://localhost:8080/api/venta"
    val USUARIO_URL = "http://localhost:8080/api/usuario"
    val EMPRESA_URL = "http://localhost:8080/api/empresa"
    var persona = Persona()
    var persona2 = Persona()
    var otraPersona = Persona()
    var accion: Accion? = null
    var ordenResult = OrdenDeVenta()
    var empresa = Empresa()
    @Given("una orden de venta con {int} acciones y precio {int} de la empresa {string}")
    fun una_orden_de_venta_con_acciones_de_la_empresa(cantidad: Int, precio: Int, nombre: String) {
        val url = "$DEFAULT_URL/save"
        val saveEmpresa = "$EMPRESA_URL/register"
        var orden = OrdenDeVentaDTO()
        var empresa = Empresa()
        empresa.nombreEmpresa = nombre
        empresa.email = "$nombre@test.com"
        empresa.cuit = 12345678911
        empresa.password = "123123"
        empresa.saldo = 0
        orden.nombreEmpresa = nombre
        orden.cantidadDeAcciones = cantidad
        orden.fechaDeVencimiento = LocalDate.of(2025, 7, 25)
        orden.precio = precio
        empresa = restTemplate.postForObject(saveEmpresa, empresa, Empresa::class.java) as Empresa
        orden.creadorId = empresa.id
        ordenResult = restTemplate.postForObject(url, orden, OrdenDeVenta::class.java) as OrdenDeVenta
    }

    @Given("una orden de venta con {int} acciones y precio {int} de la empresa {string} creada por una persona")
    fun una_orden_de_venta_con_acciones_de_la_empresa_creada_por_una_persona(cantidad: Int, precio: Int, nombre: String) {
    val url = "$DEFAULT_URL/save"
    val saveEmpresa = "$EMPRESA_URL/register"
    val saveUsuario = "$USUARIO_URL/save"
    var orden = OrdenDeVentaDTO()
    var empresa = Empresa()
    var accion = Accion()
    empresa.nombreEmpresa = nombre
    empresa.email = "$nombre@test.com"
    empresa.cuit = 12345676611
    empresa.password = "123123"
    empresa.saldo = 0
    orden.nombreEmpresa = nombre
    orden.cantidadDeAcciones = cantidad
    orden.fechaDeVencimiento = LocalDate.of(2025, 7, 25)
    orden.precio = precio
    empresa = restTemplate.postForObject(saveEmpresa, empresa, Empresa::class.java) as Empresa
    persona2.nombre = "persona2"
    persona2.saldo = ordenResult.precio
    persona2.apellido = "apellido2"
    persona2.cuil = 21111111111
    persona2.dni = 37533749
    persona2.email = "persona2@email.com"
    persona2.username = "username2"
    persona2.password = "password"
    accion.persona = persona2
    accion.fechaUltimaCompra = LocalDate.now().plusDays(5)
    accion.cantidad = cantidad
    accion.empresa = empresa
    persona2.acciones.add(accion)
    persona2 = restTemplate.postForObject(saveUsuario, persona2, Persona::class.java) as Persona
    orden.creadorId = persona2.id

    ordenResult = restTemplate.postForObject(url, orden, OrdenDeVenta::class.java) as OrdenDeVenta
}

    @When("una persona con saldo suficiente compra la orden")
    fun una_persona_con_nombre_compra_la_orden() {
        val save = "$USUARIO_URL/save"
        persona.nombre = "persona"
        persona.saldo = ordenResult.precio
        persona.apellido = "apellido"
        persona.cuil = 11111111111
        persona.dni = 38533749
        persona.email = "email@email.com"
        persona.username = "username"
        persona.password = "password"
        persona = restTemplate.postForObject(save, persona, Persona::class.java) as Persona
        val comprar = "$USUARIO_URL/buy?ordenId=${ordenResult.id}&usuarioId=${persona.id}"
        accion = restTemplate.postForObject(comprar,persona, Accion::class.java) as Accion
    }


    @When("otra persona con saldo suficiente compra la orden")
    fun otra_persona_con_nombre_compra_la_orden() {
        val save = "$USUARIO_URL/save"
        otraPersona.nombre = "otraPersona"
        otraPersona.saldo = ordenResult.precio
        otraPersona.apellido = "otraApellido"
        otraPersona.cuil = 11113311111
        otraPersona.dni = 38535549
        otraPersona.email = "otraPersona@email.com"
        otraPersona.username = "otraPersona"
        otraPersona.password = "password"
        otraPersona = restTemplate.postForObject(save, otraPersona, Persona::class.java) as Persona
        val comprar = "$USUARIO_URL/buy?ordenId=${ordenResult.id}&usuarioId=${otraPersona.id}"
        accion = restTemplate.postForObject(comprar,otraPersona, Accion::class.java) as Accion
    }

    @Then("el monto se ve reflejado en saldo del vendedor")
    fun el_monto_se_ve_reflejado_en_saldo_de_la_empresa() {
        var orden = restTemplate.getForObject("$DEFAULT_URL/find?ordenId=${ordenResult.id}", OrdenDeVenta::class.java)

        Assert.assertEquals(ordenResult.precio, orden?.creador?.saldo)
    }

    @Then("la cantidad se le descuenta a la persona que vendio")
    fun la_cantidad_se_le_descuenta_a_la_persona_que_vendio() {

        //TODO: Verificar por que al persisitirse la persona las acciones no lo hacen y la lista de acciones de la persona se persiste vacia

        var acciones = restTemplate.getForObject("$USUARIO_URL/acciones?usuarioId=${persona2.id}", List::class.java) as List<Accion>
        var acciones2 = restTemplate.getForObject("$USUARIO_URL/acciones?usuarioId=${ordenResult.creador.id}", List::class.java) as List<Accion>
        var acciones3 = persona2.acciones

        Assert.assertFalse(acciones?.any {it.empresa == empresa && it.cantidad != 0}!!)
        Assert.assertFalse(acciones2?.any {it.empresa == empresa && it.cantidad != 0}!!)
        Assert.assertFalse(acciones3?.any {it.empresa == empresa && it.cantidad != 0}!!)
    }
}