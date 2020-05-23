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

    /**
     * El autowired me inyecta el componente directamente, no hay que inicializar nada
     */
    @Autowired
    lateinit var testEntityService: TestEntityService

    /**
     * En las anotaciones de Mapping (Post,Get,Put,Delete) definimos la ruta, el o los tipos de datos que consume (json, xml, etc)
     * y el o los tipos de datos que produce (json, xml, etc)
     *
     * La anotacion @ResponseBody hace que la respuesta del request se mapee al tipo de dato definido en el Mapping
     * La anotacion @ResponseStatus define el codigo que devuelve el request (200,300,400,500), aclaramos cual es usando el enum
     * HttpStatus
     *
     * La anotacion @RequestBody define el body del request, este mapea el tipo del parametro al tipo que aclaramos en el mapping
     *
     * La anotacion @RequestParam se usa para definir parametros del request, esta anotacion se pone antes del parametro del metodo que queremos
     * que sea parametro del request
     */
    @PostMapping(value= ["/save"], consumes= [MediaType.APPLICATION_JSON_VALUE], produces= [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value= HttpStatus.CREATED)
    fun save(@RequestBody entity: TestEntity) = testEntityService.save(entity)

    @GetMapping(value= ["/find"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value= HttpStatus.ACCEPTED)
    fun find(@RequestParam id: Int) = testEntityService.findById(id)

    @GetMapping(value= ["/findByField"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value= HttpStatus.ACCEPTED)
    fun findByField(@RequestParam field: String) = testEntityService.findByTestField(field)

    @GetMapping(value= ["/findByTestField"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value= HttpStatus.ACCEPTED)
    fun findAllByTestField(@RequestParam field: String) = testEntityService.findAllByTestField(field)
}