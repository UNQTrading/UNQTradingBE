package ar.unq.unqtrading.entities

import ar.unq.unqtrading.entities.exceptions.SaldoInsuficienteException
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import javax.persistence.*
import java.time.LocalDate

@Entity
@JsonTypeName("Persona")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        defaultImpl=Persona::class)
class Persona() : Usuario() {

    @Column (nullable = false)
    lateinit var nombre: String

    @Column (nullable = false)
    lateinit var apellido: String

    @Column (nullable = false)
    lateinit var username: String

    @Column (nullable = false)
    lateinit var password: String

    @Column (nullable = false)
    lateinit var email: String

    @Column (nullable = false)
    var dni: Long = 0

    @Column (nullable = false)
    var cuil: Long = 0

    @JsonIgnore
    @OneToMany(mappedBy = "persona", cascade = [CascadeType.ALL])
    var acciones: MutableSet<Accion> = mutableSetOf()
    override var saldo: Int = 0

    fun buy(orden: OrdenDeVenta) : Accion{
        var accion = Accion(orden.cantidadDeAcciones, orden.empresa, this, LocalDate.now())
        if (saldo < orden.precio)
            throw SaldoInsuficienteException("No tienes el saldo suficiente para comprar estas acciones")
        if (acciones.any { it.empresa == orden.empresa }) {
            accion = acciones.single { it.empresa == orden.empresa }
            acciones.remove(accion)
            accion.cantidad+= orden.cantidadDeAcciones
        }
        acciones.add(accion)
        saldo -= orden.precio
        return accion
    }

}