package ar.unq.unqtrading.utils

import org.springframework.http.HttpStatus
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Modifier
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaGetter

/**
 * Utils para chequeos relacionados a la estructura de objetos de manera generica
 */
object ObjectStructureUtils {

    /**
     * Metodo para validar que la estructura de un objeto no contenga nulls o strings vacios
     * Nota: los ID estan exceptuados de este chequeo
     *
     * @param o (Any)
     * @throws EmptyPropertyException
     */
    fun checkEmptyAttributes(o: Any) {
        val properties = o::class.declaredMemberProperties.filter{ isFieldAccessible(it) }
        properties.forEach {
            if (isNullOrEmpty(it, o)) {
                throw EmptyPropertyException("El campo ${it.name} esta vacio")
            }
        }
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    class EmptyPropertyException(override val message: String?) : Exception() {

    }

    private fun isFieldAccessible(property: KProperty1<*, *>): Boolean {
        return property.javaGetter?.modifiers?.let { !Modifier.isPrivate(it) } ?: false
    }

    private fun isNullOrEmpty(property: KProperty1<*, *>, o: Any) : Boolean {
        val field = o.javaClass.getDeclaredField(property.name)
        val propertyValue = try { property.javaGetter!!.invoke(o) } catch (e: InvocationTargetException) { null }
        return field.name != "id" && propertyValue == null ||
                property.javaGetter!!.returnType == String::class.java &&
                (StringUtils.isEmpty(propertyValue))
    }

}