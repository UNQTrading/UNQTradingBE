package gradle.cucumber

import ar.unq.unqtrading.DataService
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"])
class LoginEmpresaSteps {

    var restTemplate = RestTemplate()
    val LOGIN_EMPRESA_URL = "http://localhost:8080/api/empresa/login"
    @Autowired
    lateinit var dataService: DataService
    lateinit var response: ResponseEntity<Void>

    @Given("la empresa UNQ previamente registrada")
    fun runDataService() {
        dataService.crearDatos()
    }

    @When("me logeo con CUIT {long} y contraseña {string}")
    fun loginEmpresa(cuit: Long, password: String) {
        val builder = UriComponentsBuilder.fromUriString(LOGIN_EMPRESA_URL) // Add query parameter
                .queryParam("cuit", cuit)
                .queryParam("password", password)
        response = restTemplate.postForEntity(builder.toUriString(), null, Void::class.java)
    }

    @Then("el status code de la respuesta es {int}")
    fun assertStatusCode(status: Int){
        assertEquals(status, response.statusCodeValue)
    }
}