package gradle.cucumber

import ar.unq.unqtrading.entities.Accion
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
import org.springframework.web.client.getForObject
import java.time.LocalDate


@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"])
class VisualizarAccionesSteps {
    protected var restTemplate = RestTemplate()
    protected val USUARIO_URL = "http://localhost:8080/api/usuario"
    protected val DEFAULT_URL = "http://localhost:8080/api/venta"
    var usuario = Usuario()
    var orden = OrdenDeVenta()
    lateinit var listResponse: List<Accion>

    @Given("un usuario con una accion con cantidad {int} de la empresa {string}")
    fun un_usuario_con_accion_con_cantidad_de_la_empresa(cantidad: Int, empresa: String) {

        val save = "$USUARIO_URL/save"
        usuario.nombre = "Pepe"
        usuario.saldo = 50000
        usuario = restTemplate.postForObject(save, usuario, Usuario::class.java) as Usuario

        val url = "$DEFAULT_URL/save"
        orden.nombreEmpresa = empresa
        orden.cantidadDeAcciones = cantidad
        orden.fechaDeVencimiento = LocalDate.of(2025, 7, 25)
        orden.precio = 10
        orden = restTemplate.postForObject(url, orden, OrdenDeVenta::class.java) as OrdenDeVenta

        val comprar = "$USUARIO_URL/buy?ordenId=${orden.id}&usuarioId=${usuario.id}"
        restTemplate.postForObject(comprar,usuario, Accion::class.java) as Accion

    }

    @When("pregunto cuales son sus acciones")
    fun pregunto_cuales_son_sus_acciones() {
        val url = "$USUARIO_URL/acciones?usuarioId=1"
        listResponse = restTemplate.getForObject(url)
    }

    @Then("debo visualizar {int} acciones")
    fun debo_visualizar_accion_con_cantidad_de_la_empresa(acciones: Int)  {
        Assert.assertEquals(acciones, listResponse.size)
    }
}
