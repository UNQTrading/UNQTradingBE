package ar.unq.unqtrading.integration

import ar.unq.unqtrading.DataService
import ar.unq.unqtrading.services.exceptions.DniInvalidoException
import ar.unq.unqtrading.services.exceptions.UsernameInvalidoException
import ar.unq.unqtrading.services.exceptions.LoginFallidoException
import ar.unq.unqtrading.services.exceptions.PasswordInvalidaException
import ar.unq.unqtrading.services.interfaces.IUsuarioService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.assertThrows

@SpringBootTest
class LoginPersonaIntegrationTest {
    @Autowired lateinit var usuarioService: IUsuarioService
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
        val persona = usuarioService.login(12345678, "Fede", "123456")
        assertEquals(persona.cuil, 20123456787)
        assertEquals(persona.nombre, "Federico")
        assertEquals(persona.apellido,"Garetti")
        assertEquals(persona.email, "fede@fede.com")
    }

    @Test
    fun loginFallidoConDniIncorrectoTest() {
        val exception = assertThrows<LoginFallidoException> { usuarioService.login(1234567, "Fede", "123456") }
        assertEquals(exception.message, "Datos ingresados incorrectos")
    }

    @Test
    fun loginConUsernameVacioTest() {
        val exception = assertThrows<UsernameInvalidoException> { usuarioService.login(12345678, "", "123456")}
        assertEquals(exception.message, "El nombre de usuario no puede estar vacío")
    }

    @Test
    fun loginFallidoConUsernameIncorrectoTest() {
        val exception = assertThrows<LoginFallidoException> { usuarioService.login(12345678, "Fed", "123456") }
        assertEquals(exception.message, "Datos ingresados incorrectos")
    }

    @Test
    fun loginConPasswordVaciaTest() {
        val exception = assertThrows<PasswordInvalidaException> { usuarioService.login(12345678, "Fede", "") }
        assertEquals(exception.message, "La contraseña no puede estar vacía")
    }

    @Test
    fun loginFallidoConPasswordIncorrectaTest() {
        val exception = assertThrows<LoginFallidoException> { usuarioService.login(12345678, "Fede", "654321") }
        assertEquals(exception.message, "Datos ingresados incorrectos")
    }


}
