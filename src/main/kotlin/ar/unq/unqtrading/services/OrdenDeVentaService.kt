package ar.unq.unqtrading.services

import ar.unq.unqtrading.dto.OrdenDeVentaDTO
import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.entities.Usuario
import ar.unq.unqtrading.repositories.EmpresaRepository
import ar.unq.unqtrading.repositories.OrdenDeVentaRepository
import ar.unq.unqtrading.repositories.PersonaRepository
import ar.unq.unqtrading.services.exceptions.OrdenDeVentaNoEncontradaException
import ar.unq.unqtrading.services.interfaces.IOrdenDeVentaService
import ar.unq.unqtrading.services.validator.OrdenDeVentaValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrdenDeVentaService : IOrdenDeVentaService {
    @Autowired
    lateinit var ordenDeVentaRepository: OrdenDeVentaRepository

    @Autowired
    lateinit var empresaRepository: EmpresaRepository

    @Autowired
    lateinit var ordenDeVentaValidator: OrdenDeVentaValidator

    @Autowired
    lateinit var personaRepository: PersonaRepository

    override fun findAllByNombreEmpresa(nombreEmpresa: String): List<OrdenDeVenta> = ordenDeVentaRepository.findAllByEmpresaNombreEmpresa(nombreEmpresa)
    override fun saveOrdenDeVenta(ordenDeVenta: OrdenDeVentaDTO): OrdenDeVenta {
        var orden = ordenDeVenta.toModel()
        orden.empresa = empresaRepository.findByNombreEmpresa(ordenDeVenta.nombreEmpresa)!!

        //TODO: acomodar esto
        var creador = personaRepository.findById(ordenDeVenta.creadorId!!)
        if (!creador.isPresent){
            orden.creador = empresaRepository.findById(ordenDeVenta.creadorId!!).get()
        }

        ordenDeVentaValidator.validate(orden)
        return ordenDeVentaRepository.save(orden)
    }

    override fun findById(ordenId: Int): OrdenDeVenta {
        return ordenDeVentaRepository.findById(ordenId)
                .orElseThrow { OrdenDeVentaNoEncontradaException("La orden de venta con Id $ordenId no existe") }
    }

    override fun findAll(): List<OrdenDeVenta> = ordenDeVentaRepository.findAll()

}