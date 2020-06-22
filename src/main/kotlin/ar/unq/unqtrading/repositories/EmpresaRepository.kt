package ar.unq.unqtrading.repositories

import ar.unq.unqtrading.entities.Empresa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EmpresaRepository : JpaRepository<Empresa, Int> {
    fun findByNombreEmpresa(@Param ("nombreEmpresa") nombreEmpresa: String): Empresa?
    fun findByCuit(@Param ("cuit") cuit: Long): Empresa?
    fun findByEmail(@Param ("email") email: String): Empresa?
}