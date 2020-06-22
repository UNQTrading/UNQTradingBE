package gradle.cucumber

import ar.unq.unqtrading.entities.Empresa
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
class RegistrarEmpresaSteps {
    var restTemplate = RestTemplate()
    val EMPRESA_URL = "http://localhost:8080/api/empresa"
    lateinit var empresa: Empresa
    lateinit var response: ResponseEntity<Empresa>

    @Given("una empresa con nombre: {string} email: {string} password: {string} y cuit: {long}")
    fun una_empresa_con_nombre_email_password_y_cuit(nombre: String, email: String, password: String, cuit: Long) {
        empresa = Empresa()
        empresa.nombreEmpresa = nombre
        empresa.email = email
        empresa.cuit = cuit
        empresa.password = password
    }

    @When("el usuario se registra")
    fun el_usuario_se_registra() {
        val saveEmpresa = "$EMPRESA_URL/register"
        response = restTemplate.postForEntity(saveEmpresa, empresa, Empresa::class.java)
    }

    @Then("la empresa se guardo en el sistema")
    fun la_empresa_se_guardo_en_el_sistema() {
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(1, response.body!!.id)
    }
}