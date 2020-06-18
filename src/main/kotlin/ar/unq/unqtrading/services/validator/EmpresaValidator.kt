package ar.unq.unqtrading.services.validator

import ar.unq.unqtrading.entities.Empresa
import ar.unq.unqtrading.repositories.EmpresaRepository
import ar.unq.unqtrading.services.exceptions.EmpresaYaExisteException
import ar.unq.unqtrading.utils.ObjectStructureUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EmpresaValidator {
    @Autowired
    lateinit var empresaRepository: EmpresaRepository

    fun validate(empresa: Empresa) {
        ObjectStructureUtils.checkEmptyAttributes(empresa)
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
}