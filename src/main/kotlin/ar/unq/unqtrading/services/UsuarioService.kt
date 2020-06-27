package ar.unq.unqtrading.services

import ar.unq.unqtrading.entities.Accion
import ar.unq.unqtrading.entities.Usuario
import ar.unq.unqtrading.repositories.AccionRepository
import ar.unq.unqtrading.repositories.UsuarioRepository
import ar.unq.unqtrading.services.exceptions.UsuarioNoEncontradoException
import ar.unq.unqtrading.services.interfaces.IUsuarioService
import ar.unq.unqtrading.services.validator.UsuarioValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioService : IUsuarioService {
    @Autowired lateinit var usuarioRepository: UsuarioRepository
    @Autowired lateinit var ordenService: OrdenDeVentaService
    @Autowired lateinit var accionRepository: AccionRepository
    @Autowired lateinit var usuarioValidator: UsuarioValidator

    override fun save(usuario: Usuario) : Usuario {
        usuarioValidator.validate(usuario)
        return usuarioRepository.save(usuario)
    }

    override fun buy(ordenId: Int, usuarioId: Int): Accion {
        var usuario: Usuario = findById(usuarioId)
        var orden = ordenService.findById(ordenId)
        var accion = usuario.buy(orden)
        usuarioRepository.save(usuario)
        return accion
    }

    override fun findById(usuarioId: Int): Usuario {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow{ UsuarioNoEncontradoException("El usuario con Id $usuarioId no existe")}
    }

    override fun findAcciones(usuarioId: Int): List<Accion> {
        return accionRepository.findByUsuarioId(usuarioId)
    }

    override fun login(dni: Long, username: String, password: String): Usuario {
        val usuario = usuarioRepository.findByDni(dni)
        usuarioValidator.validateLogin(dni, username, password, usuario)
        return usuario!!
    }

    override fun cargarSaldo(dni: Long, saldo: Int) {
        usuarioValidator.validateSaldo(saldo)
        usuarioRepository.updateSaldo(dni, saldo)
    }
}