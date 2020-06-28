package ar.unq.unqtrading.services.interfaces

import ar.unq.unqtrading.entities.Empresa
import ar.unq.unqtrading.entities.OrdenDeVenta
import org.springframework.stereotype.Service

@Service
interface IEmpresaService {
    fun save(empresa: Empresa): Empresa
    fun login(cuit: Long, password: String): Empresa
    fun findSaldo(usuarioId: Int): Int
}