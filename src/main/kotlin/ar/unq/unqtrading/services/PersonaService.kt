package ar.unq.unqtrading.services

import ar.unq.unqtrading.entities.Accion
import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.entities.Persona
import ar.unq.unqtrading.repositories.AccionRepository
import ar.unq.unqtrading.repositories.OrdenDeVentaRepository
import ar.unq.unqtrading.repositories.PersonaRepository
import ar.unq.unqtrading.services.exceptions.UsuarioNoEncontradoException
import ar.unq.unqtrading.services.interfaces.IPersonaService
import ar.unq.unqtrading.services.validator.UsuarioValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonaService : IPersonaService {
    @Autowired lateinit var personaRepository: PersonaRepository
    @Autowired lateinit var ordenService: OrdenDeVentaService
    @Autowired lateinit var accionRepository: AccionRepository
    @Autowired lateinit var usuarioValidator: UsuarioValidator
    @Autowired lateinit var ordenDeVentaRepository: OrdenDeVentaRepository

    override fun save(persona: Persona) : Persona {
        usuarioValidator.validate(persona)
        return personaRepository.save(persona)
    }

    override fun buy(ordenId: Int, usuarioId: Int): Accion {
        var persona: Persona = findById(usuarioId)
        var orden = ordenService.findById(ordenId)
        var accion = persona.buy(orden)
        orden.creador.saldo += orden.precio

        var vendedor = personaRepository.findById(orden.creador.id)
        if (vendedor.isPresent)
            descontarAcciones(vendedor.get(), orden)

        //ordenDeVentaRepository.save(orden)
        personaRepository.save(persona)
        return accion
    }

    private fun descontarAcciones(vendedor: Persona, orden: OrdenDeVenta) {
        var accionVendedor = vendedor.acciones.find { accion -> accion.empresa == orden.empresa }
        if (accionVendedor != null) {
            if (accionVendedor.cantidad != orden.cantidadDeAcciones)
                accionVendedor.cantidad -= orden.cantidadDeAcciones
            else {
                vendedor.acciones.remove(accionVendedor)
                ordenDeVentaRepository.delete(orden)
                accionRepository.delete(accionVendedor)
            }
            personaRepository.save(vendedor)
        }
    }

    override fun findById(personaId: Int): Persona {
        return personaRepository.findById(personaId)
                .orElseThrow{ UsuarioNoEncontradoException("El persona con Id $personaId no existe")}
    }

    override fun findAcciones(personaId: Int): List<Accion> {
        return accionRepository.findByPersonaId(personaId)
    }

    override fun login(dni: Long, username: String, password: String): Persona {
        val usuario = personaRepository.findByDni(dni)
        usuarioValidator.validateLogin(dni, username, password, usuario)
        return usuario!!
    }

    override fun cargarSaldo(dni: Long, saldo: Int) {
        usuarioValidator.validateSaldo(saldo)
        personaRepository.updateSaldo(dni, saldo)
    }

    override fun findSaldo(usuarioId: Int): Int {
        val persona = personaRepository.findById(usuarioId)
        return persona.get().saldo
    }
}