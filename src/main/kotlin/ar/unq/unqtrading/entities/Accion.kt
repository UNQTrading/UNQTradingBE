package ar.unq.unqtrading.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDate
import javax.persistence.*

@Entity
class Accion(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var cantidad: Int = 0
    lateinit var nombreEmpresa: String
    @ManyToOne(cascade = [CascadeType.ALL])
    lateinit var usuario: Usuario
    @JsonFormat(pattern = "yyyy-MM-dd")
    lateinit var fechaUltimaCompra: LocalDate

    constructor(cantidad: Int, nombreEmpresa: String, usuario: Usuario, ultimaCompra: LocalDate) : this() {
        this.cantidad = cantidad
        this.nombreEmpresa = nombreEmpresa
        this.usuario = usuario
        this.fechaUltimaCompra = ultimaCompra
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Accion

        if (nombreEmpresa != other.nombreEmpresa) return false
        if (usuario != other.usuario) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nombreEmpresa.hashCode()
        result = 31 * result + usuario.hashCode()
        return result
    }


}