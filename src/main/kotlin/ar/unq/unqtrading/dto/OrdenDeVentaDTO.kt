package ar.unq.unqtrading.dto

import ar.unq.unqtrading.entities.OrdenDeVenta
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

class OrdenDeVentaDTO {
    var id: Int = 0
    lateinit var nombreEmpresa: String
    var cantidadDeAcciones: Int = 0
    var precio: Int = 0
    var creadorId: Int? = null
    @JsonFormat(pattern = "yyyy-MM-dd")
    var fechaDeCreacion: LocalDate = LocalDate.now()
    @JsonFormat(pattern = "yyyy-MM-dd")
    lateinit var fechaDeVencimiento: LocalDate

    fun toModel() : OrdenDeVenta = OrdenDeVenta(cantidadDeAcciones, precio, fechaDeVencimiento)

 }