package gradle.cucumber

import ar.unq.unqtrading.DataService
import ar.unq.unqtrading.repositories.EmpresaRepository
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import junit.framework.Assert.assertEquals
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"])
class ObtenerSaldoEmpresaSteps {
    var restTemplate = RestTemplate()
    val FIND_SALDO_URL = "http://localhost:8080/api/empresa/obtenerSaldo"
    @Autowired
    lateinit var dataService: DataService
    @Autowired
    lateinit var empresaRepository: EmpresaRepository

    @Given("una empresa previamente registrada")
    fun runDataService() {
        dataService.crearDatos()
    }

    @When("cargo saldo al cuit {long} con un monto de {int} pesos")
    fun agregarSaldo(cuit: Long, saldo: Int) {
        val empresa = empresaRepository.findByCuit(cuit)
        empresa!!.saldo = saldo
        empresaRepository.save(empresa)
    }

    @Then("se cargaron {int} pesos a la cuenta")
    fun assertSaldo(saldo: Int) {
        val builder = UriComponentsBuilder.fromUriString(FIND_SALDO_URL)
                .queryParam("usuarioId", 2)
        var saldoObtenido = restTemplate.getForObject(builder.toUriString(), Int::class.javaObjectType)
        assertEquals(saldo, saldoObtenido)
    }

}