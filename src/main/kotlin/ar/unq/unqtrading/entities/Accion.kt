package ar.unq.unqtrading.entities

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import javax.persistence.*

@Entity
class Accion(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var cantidad: Int = 0
    @ManyToOne
    lateinit var empresa: Empresa
    @ManyToOne(cascade = [CascadeType.ALL])
    lateinit var persona: Persona
    @JsonFormat(pattern = "yyyy-MM-dd")
    lateinit var fechaUltimaCompra: LocalDate

    constructor(cantidad: Int, empresa: Empresa, persona: Persona, ultimaCompra: LocalDate) : this() {
        this.cantidad = cantidad
        this.empresa = empresa
        this.persona = persona
        this.fechaUltimaCompra = ultimaCompra
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Accion

        if (empresa != other.empresa) return false
        if (persona != other.persona) return false

        return true
    }

    override fun hashCode(): Int {
        var result = empresa.hashCode()
        result = 31 * result + persona.hashCode()
        return result
    }


}