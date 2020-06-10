package gradle.cucumber

import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.entities.Usuario
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.Assert
import org.junit.runner.RunWith
import java.time.LocalDate


@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"])
class ComprarAccionesSteps : SpringIntegrationTest() {
    var usuario = Usuario()
    var orden = OrdenDeVenta()


    @Given("una orden de venta con {int} acciones de la empresa {string}")
    fun una_orden_de_venta_con_acciones_de_la_empresa(cantidad: Int, nombre: String) {
        val url = "$DEFAULT_URL/save"
        orden.nombreEmpresa = "UNQ"
        orden.cantidadDeAcciones = 10
        orden.fechaDeVencimiento = LocalDate.of(2025, 7, 25)
        orden.precio = 10
        restTemplate.postForObject(url, orden, OrdenDeVenta::class.java)
    }

    @When("un usuario con nombre {string} compra la orden")
    fun un_usuario_con_nombre_compra_la_orden(nombre: String) {
        val save = "$USUARIO_URL/save"
        val comprar = "$USUARIO_URL/buy?ordenId=${orden.id}?usuarioId=${usuario.id}"
        usuario.nombre = "user"
        usuario.saldo = 1000
        restTemplate.postForObject(save, usuario, Usuario::class.java)
        restTemplate.postForObject(comprar, usuario, Usuario::class.java)
    }

    @Then("el usuario debe tener esa orden en su cuenta")
    fun el_usuario_debe_tener_esa_orden_en_su_cuenta() {
        Assert.assertTrue(usuario.acciones.isNotEmpty())
    }

}