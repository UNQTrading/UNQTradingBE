package ar.unq.unqtrading.services.interfaces

import ar.unq.unqtrading.dto.OrdenDeVentaDTO
import ar.unq.unqtrading.entities.OrdenDeVenta
import org.springframework.stereotype.Service

@Service
interface IOrdenDeVentaService {
    fun findAllByNombreEmpresa(nombreEmpresa: String) : List<OrdenDeVenta>
    fun findAllByCreadorId(creadorId: Int) : List<OrdenDeVenta>
    fun saveOrdenDeVenta(ordenDeVenta: OrdenDeVentaDTO) : OrdenDeVenta
    fun findById(ordenId: Int): OrdenDeVenta
    fun findAll() : List<OrdenDeVenta>
}