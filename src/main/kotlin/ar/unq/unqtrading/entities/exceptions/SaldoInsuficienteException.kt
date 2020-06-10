package ar.unq.unqtrading.entities.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.FORBIDDEN)
class SaldoInsuficienteException(override val message: String?) : RuntimeException()
