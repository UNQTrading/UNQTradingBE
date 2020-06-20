package ar.unq.unqtrading.integration

import ar.unq.unqtrading.DataService
import ar.unq.unqtrading.dto.OrdenDeVentaDTO
import ar.unq.unqtrading.services.exceptions.OrdenDeVentaIncorrectaException
import ar.unq.unqtrading.services.interfaces.IOrdenDeVentaService
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class OrdenDeVentaIntegrationTest {
    @Autowired lateinit var ordenDeVentaService: IOrdenDeVentaService
    @Autowired lateinit var dataService: DataService
    val ordenCocaCola = OrdenDeVentaDTO()

    @BeforeEach
    fun init() {
        dataService.crearDatos()
        ordenCocaCola.nombreEmpresa = "Coca-Cola"
        ordenCocaCola.cantidadDeAcciones = 1000
        ordenCocaCola.precio = 500
        ordenCocaCola.fechaDeCreacion = LocalDate.now()
        ordenCocaCola.fechaDeVencimiento = LocalDate.of(2020, 7, 25)
    }

    @AfterEach
    fun destroy() {
        dataService.eliminarDatos()
    }

    @Test
    fun findAllByNombreEmpresaTest() {
        val ordenes = ordenDeVentaService.findAllByNombreEmpresa("UNQ")
        assertEquals(2, ordenes.size)
    }

    @Test
    fun saveOrdenDeVentaTest() {
        assertEquals(0, ordenDeVentaService.findAllByNombreEmpresa("Coca-Cola").size)
        ordenDeVentaService.saveOrdenDeVenta(ordenCocaCola)
        assertEquals(1, ordenDeVentaService.findAllByNombreEmpresa("Coca-Cola").size)
    }

    @Test
    fun saveOrdenDeVentaAccionesNegativasTest() {
        ordenCocaCola.cantidadDeAcciones = -500
        val exception = assertThrows<OrdenDeVentaIncorrectaException> { ordenDeVentaService.saveOrdenDeVenta(ordenCocaCola) }
        assertEquals("La cantidad de acciones debe ser mayor a 0", exception.message)
    }

    @Test
    fun saveOrdenDeVentaPrecioNegativoTest() {
        ordenCocaCola.precio = -50
        val exception = assertThrows<OrdenDeVentaIncorrectaException> { ordenDeVentaService.saveOrdenDeVenta(ordenCocaCola) }
        assertEquals("El precio debe ser mayor a 0", exception.message)
    }

    @Test
    fun saveOrdenDeVentaFechaDeVencimientoIncorrectaTest() {
        ordenCocaCola.fechaDeVencimiento = LocalDate.now().minusDays(1)
        val exception = assertThrows<OrdenDeVentaIncorrectaException> { ordenDeVentaService.saveOrdenDeVenta(ordenCocaCola) }
        assertEquals("La fecha debe ser posterior a ${LocalDate.now()}", exception.message)
    }

    @Test
    fun findAllByTest() {
        val ordenes = ordenDeVentaService.findAll()
        assertEquals(2, ordenes.size)
    }
}