package ar.unq.unqtrading.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    lateinit var testField: String
}