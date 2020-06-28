package ar.unq.unqtrading.services.validator

import ar.unq.unqtrading.entities.Persona
import ar.unq.unqtrading.repositories.PersonaRepository
import ar.unq.unqtrading.services.exceptions.*
import ar.unq.unqtrading.utils.ObjectStructureUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UsuarioValidator {
    @Autowired
    lateinit var personaRepository: PersonaRepository

    fun validate(persona: Persona) {
        ObjectStructureUtils.checkEmptyAttributes(persona)
        if (personaRepository.findByDni(persona.dni) != null) {
            throw UsuarioYaExistenteException("Ya hay un usuario registrado con el dni ${persona.dni}")
        }
        if (personaRepository.findByCuil(persona.cuil) != null) {
            throw UsuarioYaExistenteException("Ya hay un usuario registrado con el cuil ${persona.cuil}")
        }
        if(personaRepository.findByEmail(persona.email) != null ) {
            throw UsuarioYaExistenteException("Ya hay un usuario registrado con el email ${persona.email}")
        }
        if(personaRepository.findByUsername(persona.username) != null ) {
            throw UsuarioYaExistenteException("Ya hay un usuario registrado con el nombre de persona ${persona.username}")
        }
    }

    fun validateLogin(dni: Long, username: String, password: String, persona: Persona?) {
        validateDni(dni)
        validateUsername(username)
        validatePassword(password)
        if (persona === null || !persona.password.contentEquals(password) ||
                        !persona.username.contentEquals(username)) {
            throw LoginFallidoException("Datos ingresados incorrectos")
        }
    }

    fun validateSaldo(saldo: Int) {
        if (saldo < 1) {
            throw SaldoCeroONegativoException("El saldo debe ser mayor a 0")
        }
    }

    private fun validateDni(dni: Long) {
        if ("$dni".length == 0 ) {
            throw DniInvalidoException("El DNI no puede estar vacío")
        }
    }

    private fun validateUsername(username: String) {
        if (username.isEmpty()) {
            throw UsernameInvalidoException("El nombre de persona no puede estar vacío")
        }
    }

    private fun validatePassword(password: String) {
        if (password.isEmpty()) {
            throw PasswordInvalidaException("La contraseña no puede estar vacía")
        }
    }
}