package ar.unq.unqtrading.repositories

import ar.unq.unqtrading.entities.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository: JpaRepository<Usuario, Int> {
}