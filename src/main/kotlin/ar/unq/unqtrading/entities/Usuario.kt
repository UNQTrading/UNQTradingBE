package ar.unq.unqtrading.entities

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

    fun comprar(orden: OrdenDeVenta) : Accion{
        var accion = Accion()
        accion.cantidad = orden.cantidadDeAcciones
        accion.nombreEmpresa = orden.nombreEmpresa
        accion.usuario = this
        acciones.add(accion)
        return accion
    }

}