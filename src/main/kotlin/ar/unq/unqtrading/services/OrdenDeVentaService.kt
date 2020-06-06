package ar.unq.unqtrading.services

import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.repositories.OrdenDeVentaRepository
import ar.unq.unqtrading.services.interfaces.IOrdenDeVentaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrdenDeVentaService : IOrdenDeVentaService {
    @Autowired
    lateinit var ordenDeVentaRepository: OrdenDeVentaRepository

    override fun findAllByNombreEmpresa(nombreEmpresa: String): List<OrdenDeVenta> = ordenDeVentaRepository.findAllByNombreEmpresa(nombreEmpresa)

}