package ar.unq.unqtrading.services

import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.repositories.OrdenDeVentaRepository
import ar.unq.unqtrading.services.exceptions.OrdenDeVentaNoEncontradaException
import ar.unq.unqtrading.services.interfaces.IOrdenDeVentaService
import ar.unq.unqtrading.services.validator.OrdenDeVentaValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrdenDeVentaService : IOrdenDeVentaService {
    @Autowired
    lateinit var ordenDeVentaRepository: OrdenDeVentaRepository
    val ordenDeVentaValidator = OrdenDeVentaValidator()

    override fun findAllByNombreEmpresa(nombreEmpresa: String): List<OrdenDeVenta> = ordenDeVentaRepository.findAllByNombreEmpresa(nombreEmpresa)
    override fun saveOrdenDeVenta(ordenDeVenta: OrdenDeVenta): OrdenDeVenta {
        ordenDeVentaValidator.validate(ordenDeVenta)
        return ordenDeVentaRepository.save(ordenDeVenta)
    }

    override fun findById(ordenId: Int): OrdenDeVenta {
        return ordenDeVentaRepository.findById(ordenId)
                .orElseThrow { OrdenDeVentaNoEncontradaException("La orden de venta con Id $ordenId no existe") }
    }
}