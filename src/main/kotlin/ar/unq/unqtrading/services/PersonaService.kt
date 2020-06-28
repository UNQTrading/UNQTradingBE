package ar.unq.unqtrading.services

import ar.unq.unqtrading.entities.Accion
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
        ordenDeVentaRepository.save(orden)
        personaRepository.save(persona)
        return accion
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
}