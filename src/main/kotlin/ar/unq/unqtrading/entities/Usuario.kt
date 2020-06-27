package ar.unq.unqtrading.entities

import javax.persistence.Column


abstract class Usuario {

    @Column(nullable = false)
    open var saldo: Int = 0
}