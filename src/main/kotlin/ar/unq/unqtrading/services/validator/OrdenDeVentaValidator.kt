package ar.unq.unqtrading.services.validator

import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.repositories.OrdenDeVentaRepository
import ar.unq.unqtrading.services.exceptions.OrdenDeVentaIncorrectaException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class OrdenDeVentaValidator {
    @Autowired
    lateinit var ordenDeVentaRepository: OrdenDeVentaRepository

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
        if (ordenDeVentaRepository.findAllByCreadorId(ordenDeVenta.creador.id).any {
                    it.cantidadDeAcciones == ordenDeVenta.cantidadDeAcciones &&
                    it.empresa == ordenDeVenta.empresa &&
                    it.fechaDeVencimiento == ordenDeVenta.fechaDeVencimiento &&
                    it.precio == ordenDeVenta.precio
                }) {
            throw OrdenDeVentaIncorrectaException("Ya existe una orden de venta con esos datos")
        }
    }
}
