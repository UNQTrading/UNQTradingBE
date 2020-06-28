package ar.unq.unqtrading.controllers

import ar.unq.unqtrading.dto.OrdenDeVentaDTO
import ar.unq.unqtrading.entities.OrdenDeVenta
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
    fun findAllByNombreEmpresa(@RequestParam nombreEmpresa: String) = ordenDeVentaService.findAllByNombreEmpresa(nombreEmpresa)

    @GetMapping(value = ["/usuario/all"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    fun findAllByCreadorId(@RequestParam creadorId: Int) = ordenDeVentaService.findAllByCreadorId(creadorId)

    @PostMapping(value = ["/save"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    fun saveOrdenDeVenta(@RequestBody ordenDeVenta: OrdenDeVentaDTO) = ordenDeVentaService.saveOrdenDeVenta(ordenDeVenta)

    @GetMapping(value = ["/find"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    fun findById(@RequestParam ordenId: Int) : OrdenDeVenta = ordenDeVentaService.findById(ordenId)

    @GetMapping(value = ["/ordenes"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    fun findAllOrdenes() = ordenDeVentaService.findAll()
}