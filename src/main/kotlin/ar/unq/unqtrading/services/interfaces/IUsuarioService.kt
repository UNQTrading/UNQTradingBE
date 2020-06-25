package ar.unq.unqtrading.services.interfaces

import ar.unq.unqtrading.entities.Accion
import ar.unq.unqtrading.entities.Usuario
import org.springframework.stereotype.Service

@Service
interface IUsuarioService {
    fun save(usuario: Usuario): Usuario
    fun buy(ordenId: Int, usuarioId: Int): Accion
    fun findById(usuarioId: Int): Usuario
    fun findAcciones(usuarioId: Int): List<Accion>
    fun login(dni: Long, username: String, password: String): Usuario
    fun cargarSaldo(dni: Long, saldo: Int)
}