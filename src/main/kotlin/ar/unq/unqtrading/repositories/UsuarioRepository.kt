package ar.unq.unqtrading.repositories

import ar.unq.unqtrading.entities.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface UsuarioRepository: JpaRepository<Usuario, Int> {
    fun findByDni(@Param("dni") dni: Long): Usuario?
    fun findByCuil(@Param("cuil") cuil: Long): Usuario?
    fun findByEmail(@Param("email") email: String): Usuario?
    fun findByUsername(@Param("username") username: String): Usuario?
    @Transactional
    @Modifying
    @Query("update Usuario u set u.saldo = u.saldo + ?2 where u.dni = ?1")
    fun updateSaldo(dni: Long, saldo: Int)
}