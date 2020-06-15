package ar.unq.unqtrading.services.interfaces

import ar.unq.unqtrading.entities.OrdenDeVenta
import org.springframework.stereotype.Service

@Service
interface IOrdenDeVentaService {
    fun findAllByNombreEmpresa(nombreEmpresa: String) : List<OrdenDeVenta>
    fun saveOrdenDeVenta(ordenDeVenta: OrdenDeVenta) : OrdenDeVenta
    fun findById(ordenId: Int): OrdenDeVenta
    fun findAll() : List<OrdenDeVenta>
}