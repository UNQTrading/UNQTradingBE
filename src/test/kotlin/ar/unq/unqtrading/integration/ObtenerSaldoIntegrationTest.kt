package ar.unq.unqtrading.integration

import ar.unq.unqtrading.DataService
import ar.unq.unqtrading.repositories.EmpresaRepository
import ar.unq.unqtrading.repositories.PersonaRepository
import ar.unq.unqtrading.services.EmpresaService
import ar.unq.unqtrading.services.PersonaService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ObtenerSaldoIntegrationTest {

    @Autowired
    lateinit var dataService: DataService

    @Autowired
    lateinit var personaService: PersonaService

    @Autowired
    lateinit var empresaService: EmpresaService

    @Autowired
    lateinit var personaRepository: PersonaRepository

    @Autowired
    lateinit var EmpresaRepository: EmpresaRepository

    @BeforeEach
    fun init() {
        dataService.crearDatos()
    }

    @AfterEach
    fun destroy() {
        dataService.eliminarDatos()
    }

    @Test
    fun ObtenerSaldoPersonaTest() {
        var usuario = personaRepository.findByDni(12345678)
        assertTrue(personaService.findSaldo(usuario!!.id) == 0)
        personaService.cargarSaldo(12345678, 200)
        usuario = personaRepository.findByDni(12345678)
        assertTrue(personaService.findSaldo(usuario!!.id) == 200)
    }

    @Test
    fun ObtenerSaldoEmpresaTest() {
        var usuario = EmpresaRepository.findByCuit(12345678999)
        assertTrue(empresaService.findSaldo(usuario!!.id) == 0)
        usuario.saldo = 200
        EmpresaRepository.save(usuario)
        usuario = EmpresaRepository.findByCuit(12345678999)
        assertTrue(empresaService.findSaldo(usuario!!.id) == 200)
    }
}