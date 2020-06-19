package ar.unq.unqtrading.repositories

import ar.unq.unqtrading.entities.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository: JpaRepository<Usuario, Int> {
    fun findByDni(@Param("dni") dni: Long): Usuario?
    fun findByCuil(@Param("cuil") cuil: Long): Usuario?
    fun findByEmail(@Param("email") email: String): Usuario?
    fun findByUsername(@Param("username") username: String): Usuario?
}