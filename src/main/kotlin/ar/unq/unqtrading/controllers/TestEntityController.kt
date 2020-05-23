package ar.unq.unqtrading.controllers

import ar.unq.unqtrading.entities.TestEntity
import ar.unq.unqtrading.services.TestEntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/test")
class TestEntityController {

    @Autowired
    lateinit var testEntityService: TestEntityService

    @PostMapping(value= ["/save"], consumes= [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    fun save(@RequestBody entity: TestEntity) = testEntityService.save(entity)

    @GetMapping(value= ["/find"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun find(@RequestParam id: Int) = testEntityService.findById(id)

}