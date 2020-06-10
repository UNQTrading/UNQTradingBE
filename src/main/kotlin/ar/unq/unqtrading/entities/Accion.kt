package ar.unq.unqtrading.entities

import javax.persistence.*

@Entity
class Accion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var cantidad: Int = 0
    lateinit var nombreEmpresa: String
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    lateinit var usuario: Usuario

}