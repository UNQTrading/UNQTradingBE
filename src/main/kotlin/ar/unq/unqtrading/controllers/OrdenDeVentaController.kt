package ar.unq.unqtrading.controllers

import ar.unq.unqtrading.services.interfaces.IOrdenDeVentaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/venta")
class OrdenDeVentaController {
    @Autowired
    lateinit var ordenDeVentaService: IOrdenDeVentaService

    @GetMapping(value = ["/all"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    fun findAll() = ordenDeVentaService.findAll()
}