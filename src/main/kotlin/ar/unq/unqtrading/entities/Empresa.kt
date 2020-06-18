package ar.unq.unqtrading.entities

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
class Empresa() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    @Column(name = "nombre_empresa", unique = true)
    lateinit var nombreEmpresa: String
    @Column(unique = true)
    lateinit var email: String
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    lateinit var password: String
    @Column(unique = true)
    var cuit: Long = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Empresa

        if (id != other.id) return false
        if (nombreEmpresa != other.nombreEmpresa) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (cuit != other.cuit) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + nombreEmpresa.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + cuit.hashCode()
        return result
    }

}