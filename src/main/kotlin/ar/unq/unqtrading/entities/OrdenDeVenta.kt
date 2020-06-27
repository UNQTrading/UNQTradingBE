package ar.unq.unqtrading.entities

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import javax.persistence.*

@Entity
class OrdenDeVenta() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    @ManyToOne
    lateinit var empresa: Empresa
    var cantidadDeAcciones: Int = 0
    var precio: Int = 0
    @ManyToOne
    lateinit var creador : Usuario
    @JsonFormat(pattern = "yyyy-MM-dd")
    var fechaDeCreacion: LocalDate = LocalDate.now()
    @JsonFormat(pattern = "yyyy-MM-dd")
    lateinit var fechaDeVencimiento: LocalDate

    constructor(cantidadAcciones: Int, precio: Int, fechaDeVencimiento: LocalDate) : this() {
        this.cantidadDeAcciones = cantidadAcciones
        this.precio = precio
        this.fechaDeVencimiento = fechaDeVencimiento
    }

}