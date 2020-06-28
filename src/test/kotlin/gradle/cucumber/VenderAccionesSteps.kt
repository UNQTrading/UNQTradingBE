package gradle.cucumber

import ar.unq.unqtrading.dto.OrdenDeVentaDTO
import ar.unq.unqtrading.entities.Accion
import ar.unq.unqtrading.entities.Empresa
import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.entities.Persona
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
    var usuario = Persona()
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

    @When("una persona con saldo suficiente compra la orden")
    fun una_persona_con_nombre_compra_la_orden() {
        val save = "$USUARIO_URL/save"
        usuario.nombre = "persona"
        usuario.saldo = ordenResult.precio
        usuario.apellido = "apellido"
        usuario.cuil = 11111111111
        usuario.dni = 38533749
        usuario.email = "email@email.com"
        usuario.username = "username"
        usuario.password = "password"
        usuario = restTemplate.postForObject(save, usuario, Persona::class.java) as Persona
        val comprar = "$USUARIO_URL/buy?ordenId=${ordenResult.id}&usuarioId=${usuario.id}"
        accion = restTemplate.postForObject(comprar,usuario, Accion::class.java) as Accion
    }

    @Then("el monto se ve reflejado en saldo del vendedor")
    fun el_monto_se_ve_reflejado_en_saldo_de_la_empresa() {
        var orden = restTemplate.getForObject("$DEFAULT_URL/find?ordenId=${ordenResult.id}", OrdenDeVenta::class.java)

        Assert.assertEquals(ordenResult.precio, orden?.creador?.saldo)
    }
}