package ar.unq.unqtrading.controllers

import ar.unq.unqtrading.entities.Accion
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

    @GetMapping(value = ["/find"])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun findUsuario(@RequestParam usuarioId: Int) : Usuario = usuarioService.findById(usuarioId)

    @GetMapping(value = ["/acciones"])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun findAcciones(@RequestParam usuarioId: Int) : List<Accion> = usuarioService.findAcciones(usuarioId)

    @PostMapping(value = ["/login"])
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun login(@RequestParam dni: Long, @RequestParam username: String, @RequestParam password: String) = usuarioService.login(dni, username, password)

    @PatchMapping(value = ["/cargarSaldo"])
    @ResponseStatus(HttpStatus.OK)
    fun cargarSaldo(@RequestParam dni: Long, @RequestParam saldo: Int) = usuarioService.cargarSaldo(dni, saldo)
}
