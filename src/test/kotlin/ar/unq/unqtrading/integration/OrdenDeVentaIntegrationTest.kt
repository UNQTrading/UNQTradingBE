package ar.unq.unqtrading.integration

import ar.unq.unqtrading.DataService
import ar.unq.unqtrading.services.OrdenDeVentaService
import ar.unq.unqtrading.services.interfaces.IOrdenDeVentaService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrdenDeVentaIntegrationTest {
    @Autowired lateinit var ordenDeVentaService: IOrdenDeVentaService
    @Autowired lateinit var dataService: DataService

    @BeforeEach
    fun init() {
        dataService.crearDatos()
    }

    @AfterEach
    fun destroy() {
        dataService.eliminarDatos()
    }
    @Test
    fun findAllTest() {
        var ordenes = ordenDeVentaService.findAll()
        assertEquals(2, ordenes.size)
    }
}