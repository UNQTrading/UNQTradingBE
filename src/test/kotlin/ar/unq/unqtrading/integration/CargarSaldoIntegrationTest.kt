package ar.unq.unqtrading.integration

import ar.unq.unqtrading.DataService
import ar.unq.unqtrading.repositories.UsuarioRepository
import ar.unq.unqtrading.services.UsuarioService
import ar.unq.unqtrading.services.exceptions.SaldoCeroONegativoException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CargarSaldoIntegrationTest {
    @Autowired
    lateinit var dataService: DataService
    @Autowired
    lateinit var usuarioService: UsuarioService
    @Autowired
    lateinit var usuarioRepository: UsuarioRepository

    @BeforeEach
    fun init() {
        dataService.crearDatos()
    }

    @AfterEach
    fun destroy() {
        dataService.eliminarDatos()
    }

    @Test
    fun cargarSaldoTest() {
        var usuario = usuarioRepository.findByDni(12345678)
        assertTrue(usuario!!.saldo == 0)
        usuarioService.cargarSaldo(12345678, 100)
        usuario = usuarioRepository.findByDni(12345678)
        assertTrue(usuario!!.saldo == 100)
    }

    @Test
    fun cargarSaldoNegativoTest() {
        assertThrows<SaldoCeroONegativoException> { usuarioService.cargarSaldo(12345678, -50) }
    }
}