package ar.unq.unqtrading.entities

import ar.unq.unqtrading.entities.exceptions.SaldoInsuficienteException
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Usuario() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    lateinit var nombre: String
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = [CascadeType.ALL])
    var acciones: MutableList<Accion> = mutableListOf()
    var saldo: Int = 0

    fun buy(orden: OrdenDeVenta) : Accion{
        if (saldo < orden.precio)
            throw SaldoInsuficienteException("No tienes el saldo suficiente para comprar estas acciones")
        var accion = Accion(orden.cantidadDeAcciones, orden.nombreEmpresa, this)
        acciones.add(accion)
        saldo -= orden.precio
        return accion
    }

}