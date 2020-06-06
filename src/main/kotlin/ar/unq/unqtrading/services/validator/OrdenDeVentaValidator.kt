package ar.unq.unqtrading.services.validator

import ar.unq.unqtrading.entities.OrdenDeVenta
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

class OrdenDeVentaValidator {
    fun validate(ordenDeVenta: OrdenDeVenta) {
        if (ordenDeVenta.cantidadDeAcciones < 1) {
            throw OrdenDeVentaIncorrectaException("La cantidad de acciones debe ser mayor a 0")
        }
        if (ordenDeVenta.precio < 1) {
            throw OrdenDeVentaIncorrectaException("El precio debe ser mayor a 0")
        }
        if (ordenDeVenta.fechaDeVencimiento.isBefore(LocalDate.now().plusDays(1))) {
            throw OrdenDeVentaIncorrectaException("La fecha debe ser posterior a ${LocalDate.now()}")
        }
    }
}

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Orden de venta incorrecta")
class OrdenDeVentaIncorrectaException(override val message: String): Exception()
