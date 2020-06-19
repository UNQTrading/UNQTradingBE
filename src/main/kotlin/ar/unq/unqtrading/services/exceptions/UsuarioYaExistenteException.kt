package ar.unq.unqtrading.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception


@ResponseStatus(HttpStatus.FORBIDDEN)
class UsuarioYaExistenteException(override val message: String?) : Exception()