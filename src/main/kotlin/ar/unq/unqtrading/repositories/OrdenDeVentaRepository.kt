package ar.unq.unqtrading.repositories

import ar.unq.unqtrading.entities.OrdenDeVenta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OrdenDeVentaRepository : JpaRepository<OrdenDeVenta, Int> {
    fun findAllByEmpresaNombreEmpresa(@Param("nombreEmpresa") nombreEmpresa: String): List<OrdenDeVenta>
}