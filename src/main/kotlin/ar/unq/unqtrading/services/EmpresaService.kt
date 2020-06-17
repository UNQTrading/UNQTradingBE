package ar.unq.unqtrading.services

import ar.unq.unqtrading.entities.Empresa
import ar.unq.unqtrading.repositories.EmpresaRepository
import ar.unq.unqtrading.services.interfaces.IEmpresaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmpresaService : IEmpresaService {

    @Autowired
    lateinit var empresaRepository: EmpresaRepository

    override fun save(empresa: Empresa) = empresaRepository.save(empresa)

}