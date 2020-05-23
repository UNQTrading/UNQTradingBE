package ar.unq.unqtrading.repositories

import ar.unq.unqtrading.entities.TestEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Se implementa la interfaz JpaRepository que brinda todos los metodos CRUD necesarios
 *
 * Para definir consultas nuevas se puede hacer de dos maneras:
 *
 * Por nomenclatura (esta es la forma por convencion)
 * o escribiendo la query en la anotacion @Query sobre el metodo (esta no es la forma que se usa por convencion)
 *
 * No hace falta crear una clase que implemente esta interafaz, simplemente haciendo un @Autowired de esta interfaz
 * spring se encarga de crear el Component correspondiente, NO se mete logica en el repository
 */
@Repository
interface TestEntityRepository : JpaRepository<TestEntity, Int> {

    /**
     * Si uso findBy es para buscar uno solo, si devuelve mas de uno va a tirar exception de mas de un resultado
     */
    fun findByTestField(@Param("testField") testField: String) : TestEntity

    /**
     * Si uso findAllBy es para buscar una lista
     */
    fun findAllByTestField(@Param("testField") testField: String) : List<TestEntity>

    /**
     * lo mas importante en esto es poner el tipo que devuelve, porque si lo infiere siempre va a inferir que es un elemento
     * y ahi es donde rompe porque tira mas de un resultado
     */


}
