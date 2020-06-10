package ar.unq.unqtrading.services

import ar.unq.unqtrading.entities.Accion
import ar.unq.unqtrading.entities.Usuario
import ar.unq.unqtrading.repositories.OrdenDeVentaRepository
import ar.unq.unqtrading.repositories.UsuarioRepository
import ar.unq.unqtrading.services.interfaces.IUsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioService : IUsuarioService {
    @Autowired lateinit var usuarioRepository: UsuarioRepository
    @Autowired lateinit var ordenRepository: OrdenDeVentaRepository
    override fun save(usuario: Usuario) : Usuario = usuarioRepository.save(usuario)

    override fun buy(ordenId: Int, usuarioId: Int): Accion {
        var usuario: Usuario = usuarioRepository.findById(usuarioId).orElse(null)
        var orden = ordenRepository.findById(ordenId).orElse(null)
        var accion = usuario.comprar(orden)
        usuarioRepository.save(usuario)
        return accion
    }

}