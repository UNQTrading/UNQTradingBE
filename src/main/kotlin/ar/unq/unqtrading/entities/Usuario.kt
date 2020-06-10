package ar.unq.unqtrading.entities

import javax.persistence.*

@Entity
class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    lateinit var nombre: String
    @OneToMany(mappedBy = "usuario")
    lateinit var acciones: List<Accion>

}