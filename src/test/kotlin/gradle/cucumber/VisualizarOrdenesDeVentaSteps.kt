package gradle.cucumber

import ar.unq.unqtrading.entities.OrdenDeVenta
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Assert

class VisualizarOrdenesDeVentaSteps {
    lateinit var orden1: OrdenDeVenta
    lateinit var orden2: OrdenDeVenta
    lateinit var ordenes: List<OrdenDeVenta>

    @Given("Given una empresa con nombre {nombre} y {cant} ordenes de venta")
    fun setOrdenesDeVenta(nombre: String, cant: Int) {
        orden1 = OrdenDeVenta()
        orden2 = OrdenDeVenta()
        orden1.nombreEmpresa = nombre
        orden2.nombreEmpresa = nombre
    }

    @When("When pregunto cuales son sus ordenes de venta")
    fun setListadoDeOrdenes(day: String) {
        ordenes = listOf(orden1, orden2)
    }

    @Then("Then debo visualizar {cantidad} ordenes de venta")
    fun assertDay(cantidad: String?) {
        Assert.assertEquals(cantidad, ordenes.size)
    }
}