package ar.unq.unqtrading.entities

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class OrdenDeVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    var cantidadDeAcciones: Int = 0
    var precio: Int = 0
    var fechaDeCreacion: LocalDate = LocalDate.now()
    var fechaDeVencimiento: LocalDate = LocalDate.now()
}