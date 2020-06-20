package ar.unq.unqtrading.integration

import ar.unq.unqtrading.DataService
import ar.unq.unqtrading.services.exceptions.CuitInvalidoException
import ar.unq.unqtrading.services.exceptions.LoginFallidoException
import ar.unq.unqtrading.services.exceptions.PasswordInvalidaException
import ar.unq.unqtrading.services.interfaces.IEmpresaService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.assertThrows

@SpringBootTest
class LoginEmpresaIntegrationTest {
    @Autowired lateinit var empresaService: IEmpresaService
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
    fun loginCorrectoTest() {
        val empresa = empresaService.login(98765541111, "1234578")
        assertEquals(empresa.nombreEmpresa, "UNQ")
    }

    @Test
    fun loginConCuitMenorAOnceDigitosTest() {
        val exception = assertThrows<CuitInvalidoException> { empresaService.login(98765541, "1234578") }
        assertEquals(exception.message, "El cuit debe tener 11 dígitos")
    }

    @Test
    fun loginConPasswordVaciaTest() {
        val exception = assertThrows<PasswordInvalidaException> { empresaService.login(98765541111, "") }
        assertEquals(exception.message, "La contraseña no puede estar vacía")
    }

    @Test
    fun loginFallidoConPasswordIncorrectaTest() {
        val exception = assertThrows<LoginFallidoException> { empresaService.login(98765541111, "123457") }
        assertEquals(exception.message, "Cuit o contraseña incorrecto")
    }

    @Test
    fun loginFallidoConCuitIncorrectoTest() {
        val exception = assertThrows<LoginFallidoException> { empresaService.login(98765541112, "1234578") }
        assertEquals(exception.message, "Cuit o contraseña incorrecto")
    }
}