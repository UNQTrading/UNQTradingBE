package ar.unq.unqtrading.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoder {
    val encoder = BCryptPasswordEncoder()
    fun encode(password: String) = encoder.encode(password)
}