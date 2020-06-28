package ar.unq.unqtrading.repositories

import ar.unq.unqtrading.entities.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PersonaRepository: JpaRepository<Persona, Int> {
    fun findByDni(@Param("dni") dni: Long): Persona?
    fun findByCuil(@Param("cuil") cuil: Long): Persona?
    fun findByEmail(@Param("email") email: String): Persona?
    fun findByUsername(@Param("username") username: String): Persona?
    @Transactional
    @Modifying
    @Query("update Persona u set u.saldo = u.saldo + ?2 where u.dni = ?1")
    fun updateSaldo(dni: Long, saldo: Int)
}