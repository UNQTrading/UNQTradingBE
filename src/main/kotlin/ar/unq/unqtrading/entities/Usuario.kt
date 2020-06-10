package ar.unq.unqtrading.entities

import javax.persistence.*

@Entity
class Usuario() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    lateinit var nombre: String
    @OneToMany(mappedBy = "usuario")
    lateinit var acciones: MutableList<Accion>
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