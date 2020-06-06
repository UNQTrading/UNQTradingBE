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

    @Given("una empresa con nombre {string} y {int} ordenes de venta")
    fun una_empresa_con_nombre_y_ordenes_de_venta(nombre: String, cant: Int) {
        orden1 = OrdenDeVenta()
        orden2 = OrdenDeVenta()
        orden1.nombreEmpresa = nombre
        orden2.nombreEmpresa = nombre
    }

    @When("pregunto cuales son sus ordenes de venta")
    fun pregunto_cuales_son_sus_ordenes_de_venta() {
        ordenes = listOf(orden1, orden2)
    }

    @Then("debo visualizar {int} ordenes de venta")
    fun debo_visualizar_ordenes_de_venta(cantidad: Int?) {
        Assert.assertEquals(cantidad, ordenes.size)
    }
}