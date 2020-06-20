package gradle.cucumber

import ar.unq.unqtrading.DataService
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.Assert
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"])
class LoginUsuarioSteps {

    var restTemplate = RestTemplate()
    val LOGIN_PERSONA_URL = "http://localhost:8080/api/usuario/login"
    @Autowired
    lateinit var dataService: DataService
    lateinit var response: ResponseEntity<Void>

    @Given("el usuario Fede previamente registrado")
    fun runDataService() {
        dataService.crearDatos()
    }

    @When("me logeo con DNI {long}, username {string} y contraseña {string}")
    fun loginUsuario(dni: Long, username: String, password: String) {
        val builder = UriComponentsBuilder.fromUriString(LOGIN_PERSONA_URL)
                .queryParam("dni", dni)
                .queryParam("username", username)
                .queryParam("password", password)
        response = restTemplate.postForEntity(builder.toUriString(), null, Void::class.java)
    }

    @Then("el status code de la respuesta es {int}")
    fun assertStatusCode(status: Int){
        Assert.assertEquals(status, response.statusCodeValue)
    }
}
