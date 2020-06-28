package ar.unq.unqtrading.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import javax.persistence.*

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Tipo", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(value = Persona::class, name = "Persona"),
        JsonSubTypes.Type(value = Empresa::class, name = "Empresa"))
abstract class Usuario () {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Column(nullable = false)
    open var saldo: Int = 0

    @JsonIgnore
    open var type: String = this.javaClass.simpleName
}
