package ar.unq.unqtrading.services.validator

import ar.unq.unqtrading.entities.Empresa
import ar.unq.unqtrading.repositories.EmpresaRepository
import ar.unq.unqtrading.services.exceptions.CuitInvalidoException
import ar.unq.unqtrading.services.exceptions.EmpresaYaExisteException
import ar.unq.unqtrading.services.exceptions.LoginFallidoException
import ar.unq.unqtrading.services.exceptions.PasswordInvalidaException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EmpresaValidator {
    @Autowired
    lateinit var empresaRepository: EmpresaRepository

    fun validate(empresa: Empresa) {
        if (empresaRepository.findByNombreEmpresa(empresa.nombreEmpresa) != null) {
            throw EmpresaYaExisteException("Ya hay una empresa registrada con el nombre ${empresa.nombreEmpresa}")
        }
        if (empresaRepository.findByCuit(empresa.cuit) != null) {
            throw EmpresaYaExisteException("Ya hay una empresa registrada con el cuil ${empresa.cuit}")
        }
        if(empresaRepository.findByEmail(empresa.email) != null ) {
            throw EmpresaYaExisteException("Ya hay una empresa registrada con el email ${empresa.email}")
        }
    }

    fun validateLogin(cuit: Long, password: String, empresa: Empresa?) {
        validateCuit(cuit)
        validatePassword(password)
        if (empresa === null || !empresa.password.contentEquals(password)) {
            throw LoginFallidoException("Cuit o contraseña incorrecto")
        }
    }

    private fun validateCuit(cuit: Long) {
        if ("$cuit".length !== 11) {
            throw CuitInvalidoException("El cuit debe tener 11 dígitos")
        }
    }

    private fun validatePassword(password: String) {
        if (password.isEmpty()) {
            throw PasswordInvalidaException("La contraseña no puede estar vacía")
        }
    }
}