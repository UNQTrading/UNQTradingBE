package ar.unq.unqtrading.services

import ar.unq.unqtrading.entities.TestEntity
import ar.unq.unqtrading.repositories.TestEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TestEntityService {
    @Autowired
    lateinit var testEntityRepository: TestEntityRepository

    fun save(entity: TestEntity) = testEntityRepository.save(entity)
}