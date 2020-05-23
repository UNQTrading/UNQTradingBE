package ar.unq.unqtrading.repositories

import ar.unq.unqtrading.entities.TestEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TestEntityRepository : JpaRepository<TestEntity, Int> {

    fun findByTestField(@Param("testField") testField: String)

}
