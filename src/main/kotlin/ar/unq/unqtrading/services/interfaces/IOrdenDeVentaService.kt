package ar.unq.unqtrading.services.interfaces

import ar.unq.unqtrading.entities.OrdenDeVenta
import org.springframework.stereotype.Service

@Service
interface IOrdenDeVentaService {
    fun findAll() : List<OrdenDeVenta>
}