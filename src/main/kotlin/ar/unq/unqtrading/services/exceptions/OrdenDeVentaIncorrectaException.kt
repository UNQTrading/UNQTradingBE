package ar.unq.unqtrading.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class OrdenDeVentaIncorrectaException(override val message: String): Exception()