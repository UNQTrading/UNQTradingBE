package ar.unq.unqtrading.controllers

import ar.unq.unqtrading.entities.Empresa
import ar.unq.unqtrading.services.interfaces.IEmpresaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/empresa")
class EmpresaController {

    @Autowired
    lateinit var empresaService: IEmpresaService

    @PostMapping(value = ["/register"])
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody empresa: Empresa) = empresaService.save(empresa)

    @PostMapping(value = ["/login"])
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun login(@RequestParam cuit: Long, @RequestParam password: String) = empresaService.login(cuit, password)
}