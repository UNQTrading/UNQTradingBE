package ar.unq.unqtrading.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(HttpStatus.BAD_REQUEST)
class DniInvalidoException(override val message: String): Exception()