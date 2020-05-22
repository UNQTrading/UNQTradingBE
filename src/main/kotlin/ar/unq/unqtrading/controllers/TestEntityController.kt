package ar.unq.unqtrading.controllers

import ar.unq.unqtrading.entities.TestEntity
import ar.unq.unqtrading.services.TestEntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/test")
class TestEntityController {

    @Autowired lateinit var testEntityService: TestEntityService

    @PostMapping(value= ["/save"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun save(@RequestBody entity: TestEntity) = testEntityService.save(entity)
}