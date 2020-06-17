package ar.unq.unqtrading.services

import ar.unq.unqtrading.dto.OrdenDeVentaDTO
import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.repositories.EmpresaRepository
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

    @Autowired
    lateinit var empresaRepository: EmpresaRepository

    val ordenDeVentaValidator = OrdenDeVentaValidator()

    override fun findAllByNombreEmpresa(nombreEmpresa: String): List<OrdenDeVenta> = ordenDeVentaRepository.findAllByEmpresaNombreEmpresa(nombreEmpresa)
    override fun saveOrdenDeVenta(ordenDeVenta: OrdenDeVentaDTO): OrdenDeVenta {
        var orden = ordenDeVenta.toModel()
        orden.empresa = empresaRepository.findByNombreEmpresa(ordenDeVenta.nombreEmpresa)
        ordenDeVentaValidator.validate(orden)
        return ordenDeVentaRepository.save(orden)
    }

    override fun findById(ordenId: Int): OrdenDeVenta {
        return ordenDeVentaRepository.findById(ordenId)
                .orElseThrow { OrdenDeVentaNoEncontradaException("La orden de venta con Id $ordenId no existe") }
    }

    override fun findAll(): List<OrdenDeVenta> = ordenDeVentaRepository.findAll()

}