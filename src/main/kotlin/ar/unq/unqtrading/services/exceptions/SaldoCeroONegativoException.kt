package ar.unq.unqtrading.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class SaldoCeroONegativoException(override val message: String) : Exception() {
}