package gradle.cucumber

import ar.unq.unqtrading.entities.Persona
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"])
class RegistrarPersonaSteps {
    var restTemplate = RestTemplate()
    val USUARIO_URL = "http://localhost:8080/api/usuario"
    lateinit var persona: Persona
    lateinit var response: ResponseEntity<Persona>

    @Given("una persona con nombre: {string} apellido: {string} email: {string} password: {string} dni: {long} username: {string} y cuil: {long}")
    fun una_persona_con_nombre_apellido_email_password_dni_username_y_cuil(nombre: String, apellido: String, email: String, password: String, dni: Long, username: String, cuil: Long) {
        persona = Persona()
        persona.nombre = nombre
        persona.apellido = apellido
        persona.email = email
        persona.cuil = cuil
        persona.dni = dni
        persona.username = username
        persona.password = password
    }

    @When("la persona se registra")
    fun la_persona_se_registra() {
        val save = "$USUARIO_URL/save"
        response = restTemplate.postForEntity(save, persona, Persona::class.java)
    }

    @Then("la persona se guardo en el sistema")
    fun la_persona_se_guardo_en_el_sistema() {
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(1, response.body!!.id)
    }
}