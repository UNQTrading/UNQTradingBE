package ar.unq.unqtrading.entities

import com.fasterxml.jackson.annotation.JsonFormat
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
    lateinit var nombreEmpresa: String
    var cantidadDeAcciones: Int = 0
    var precio: Int = 0
    @JsonFormat(pattern = "dd/MM/yyyy")
    var fechaDeCreacion: LocalDate = LocalDate.now()
    @JsonFormat(pattern = "dd/MM/yyyy")
    lateinit var fechaDeVencimiento: LocalDate
}