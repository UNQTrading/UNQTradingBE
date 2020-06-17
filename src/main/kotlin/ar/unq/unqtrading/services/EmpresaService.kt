package ar.unq.unqtrading.services

import ar.unq.unqtrading.entities.Empresa
import ar.unq.unqtrading.repositories.EmpresaRepository
import ar.unq.unqtrading.services.interfaces.IEmpresaService
import ar.unq.unqtrading.services.validator.EmpresaValidator
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmpresaService : IEmpresaService {
    @Autowired
    lateinit var empresaRepository: EmpresaRepository
    @Autowired
    lateinit var empresaValidator: EmpresaValidator
    override fun save(empresa: Empresa): Empresa {
        var newEmpresa = empresa
        val encodedPassword = Base64.encodeBase64String(empresa.contraseña.toByteArray())
        newEmpresa.contraseña = encodedPassword
        empresaValidator.validate(empresa)
        return empresaRepository.save(empresa)
    }

}