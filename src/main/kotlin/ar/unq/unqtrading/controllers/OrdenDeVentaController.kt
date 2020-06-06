package ar.unq.unqtrading.controllers

import ar.unq.unqtrading.services.interfaces.IOrdenDeVentaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/venta")
class OrdenDeVentaController {
    @Autowired
    lateinit var ordenDeVentaService: IOrdenDeVentaService
}