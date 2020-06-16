package ar.unq.unqtrading.repositories

import ar.unq.unqtrading.entities.Empresa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmpresaRepository : JpaRepository<Empresa, Int> {
}