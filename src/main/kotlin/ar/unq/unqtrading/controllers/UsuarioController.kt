package ar.unq.unqtrading.controllers

import ar.unq.unqtrading.entities.Accion
import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.entities.Usuario
import ar.unq.unqtrading.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuario")
class UsuarioController {

    @Autowired lateinit var usuarioService: UsuarioService

    @PostMapping(value = ["/save"],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    fun save(@RequestBody usuario: Usuario) : Usuario{
        return usuarioService.save(usuario)
    }

    @PostMapping(value = ["/buy"])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    fun comprarOrden(@RequestParam ordenId: Int, @RequestParam usuarioId: Int) : Accion{
        return usuarioService.buy(ordenId, usuarioId)
    }
}