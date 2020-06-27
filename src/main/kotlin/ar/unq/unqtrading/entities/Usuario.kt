package ar.unq.unqtrading.entities

import javax.persistence.*

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Tipo", discriminatorType = DiscriminatorType.STRING)
abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Column(nullable = false)
    open var saldo: Int = 0

}