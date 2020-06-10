package ar.unq.unqtrading.entities

import com.fasterxml.jackson.databind.annotation.JsonSerialize
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

    constructor(cantidad: Int, nombreEmpresa: String, usuario: Usuario) : this() {
        this.cantidad = cantidad
        this.nombreEmpresa = nombreEmpresa
        this.usuario = usuario
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