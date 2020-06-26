package gradle.cucumber

import ar.unq.unqtrading.DataService
import ar.unq.unqtrading.entities.Usuario
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import junit.framework.Assert.assertEquals
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"])
class CargarSaldoSteps {

    var restTemplate = RestTemplate(HttpComponentsClientHttpRequestFactory())
    val CARGAR_SALDO_URL = "http://localhost:8080/api/usuario/cargarSaldo"
    val FIND_USUARIO_URL = "http://localhost:8080/api/usuario/find"
    lateinit var response: ResponseEntity<Usuario>
    @Autowired
    lateinit var dataService: DataService

    @Given("un usuario previamente registrado")
    fun runDataService() {
        dataService.crearDatos()
    }

    @When("cargo saldo al dni {int} con un monto de {int} pesos")
    fun cargarSaldo(dni: Int, saldo: Int) {
        val builder = UriComponentsBuilder.fromUriString(CARGAR_SALDO_URL)
                .queryParam("dni", dni)
                .queryParam("saldo", saldo)
        restTemplate.patchForObject(builder.toUriString(), null, Void::class.java)
    }

    @Then("los {int} pesos fueron cargados a su cuenta")
    fun assertSaldo(saldo: Int) {
        val builder = UriComponentsBuilder.fromUriString(FIND_USUARIO_URL)
                .queryParam("usuarioId", 1)
        response = restTemplate.getForEntity(builder.toUriString(), Usuario::class.java)
        assertEquals(saldo, response.body!!.saldo)
    }
}