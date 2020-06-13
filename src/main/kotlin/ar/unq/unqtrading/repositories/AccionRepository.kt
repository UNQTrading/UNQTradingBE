package ar.unq.unqtrading.repositories

import ar.unq.unqtrading.entities.Accion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AccionRepository : JpaRepository<Accion, Int> {
    fun findByUsuarioId(@Param("usuario") usuario: Int): List<Accion>
}