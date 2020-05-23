package ar.unq.unqtrading.services

import ar.unq.unqtrading.entities.TestEntity
import ar.unq.unqtrading.repositories.TestEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@Service
class TestEntityService {

    @Autowired
    lateinit var testEntityRepository: TestEntityRepository

    fun save(entity: TestEntity) = testEntityRepository.save(entity)
    fun findById(id: Int) : TestEntity? {
        var entity: TestEntity? = testEntityRepository.findByIdOrNull(id) ?: throw EntidadNoEncontradaException("la entidad con id $id no existe")
        return entity
    }

}

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason= "Entidad no encontrada")
class EntidadNoEncontradaException(override val message: String?) : Exception()

