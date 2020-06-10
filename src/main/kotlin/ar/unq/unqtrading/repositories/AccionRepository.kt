package ar.unq.unqtrading.repositories

import ar.unq.unqtrading.entities.Accion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccionRepository : JpaRepository<Accion, Int> {
}