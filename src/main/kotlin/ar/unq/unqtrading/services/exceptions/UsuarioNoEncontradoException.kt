package ar.unq.unqtrading.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class UsuarioNoEncontradoException(override val message: String?) : RuntimeException()