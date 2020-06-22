package ar.unq.unqtrading.services.interfaces

import ar.unq.unqtrading.entities.Empresa
import org.springframework.stereotype.Service

@Service
interface IEmpresaService {
    fun save(empresa: Empresa): Empresa
    fun login(cuit: Long, password: String): Empresa
}