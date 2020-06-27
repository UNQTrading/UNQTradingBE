package ar.unq.unqtrading.entities

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Column(nullable = false)
    open var saldo: Int = 0
}