package ar.unq.unqtrading.services.validator

import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.services.exceptions.OrdenDeVentaIncorrectaException
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
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
