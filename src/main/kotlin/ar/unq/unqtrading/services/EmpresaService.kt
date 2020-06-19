package ar.unq.unqtrading.services

import ar.unq.unqtrading.entities.Empresa
import ar.unq.unqtrading.repositories.EmpresaRepository
import ar.unq.unqtrading.services.interfaces.IEmpresaService
import ar.unq.unqtrading.services.validator.EmpresaValidator
import ar.unq.unqtrading.utils.PasswordEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmpresaService : IEmpresaService {
    @Autowired
    lateinit var empresaRepository: EmpresaRepository
    @Autowired
    lateinit var empresaValidator: EmpresaValidator
    @Autowired
    lateinit var encoder: PasswordEncoder

    override fun save(empresa: Empresa): Empresa {
        var newEmpresa = empresa
        val encodedPassword = encoder.encode(empresa.password)
        newEmpresa.password = encodedPassword
        empresaValidator.validate(empresa)
        return empresaRepository.save(empresa)
    }

    override fun login(cuit: Long, password: String): Empresa {
        val empresa = empresaRepository.findByCuit(cuit)
        empresaValidator.validateLogin(cuit, password, empresa)
        return empresa!!
    }

}