package gradle.cucumber

import ar.unq.unqtrading.dto.OrdenDeVentaDTO
import ar.unq.unqtrading.entities.Accion
import ar.unq.unqtrading.entities.Empresa
import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.entities.Usuario
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
class ComprarAccionesSteps {
    var restTemplate = RestTemplate()
    val DEFAULT_URL = "http://localhost:8080/api/venta"
    val USUARIO_URL = "http://localhost:8080/api/usuario"
    val EMPRESA_URL = "http://localhost:8080/api/empresa"
    var usuario = Usuario()
    var accion: Accion? = null
    var ordenResult = OrdenDeVenta()
    @Given("una orden de venta con {int} acciones de la empresa {string}")
    fun una_orden_de_venta_con_acciones_de_la_empresa(cantidad: Int, nombre: String) {
        val url = "$DEFAULT_URL/save"
        val saveEmpresa = "$EMPRESA_URL/register"
        var orden = OrdenDeVentaDTO()
        var empresa = Empresa()
        empresa.nombreEmpresa = "UNQ"
        empresa.email = "test@test.com"
        empresa.cuil = 12345
        empresa.password = "123124"
        orden.nombreEmpresa = nombre
        orden.cantidadDeAcciones = cantidad
        orden.fechaDeVencimiento = LocalDate.of(2025, 7, 25)
        orden.precio = 10
        restTemplate.postForObject(saveEmpresa, empresa, Empresa::class.java) as Empresa
        ordenResult = restTemplate.postForObject(url, orden, OrdenDeVenta::class.java) as OrdenDeVenta
    }

    @When("un usuario con nombre {string} compra la orden")
    fun un_usuario_con_nombre_compra_la_orden(nombre: String) {
        val save = "$USUARIO_URL/save"
        usuario.nombre = nombre
        usuario.saldo = 1000
        usuario = restTemplate.postForObject(save, usuario, Usuario::class.java) as Usuario
        val comprar = "$USUARIO_URL/buy?ordenId=${ordenResult.id}&usuarioId=${usuario.id}"
        accion = restTemplate.postForObject(comprar,usuario, Accion::class.java) as Accion
    }

    @Then("el usuario debe tener esa orden en su cuenta")
    fun el_usuario_debe_tener_esa_orden_en_su_cuenta() {
        Assert.assertEquals(usuario.id, accion!!.usuario.id)
    }
}