package ar.unq.unqtrading.filters

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Habilita el consumo de las operaciones desde cualquier origen, permite el acceso desde archivo.
 *
 * Úsese con precaución.
 *
 */
@Component
class SimpleCORSFilter : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*")
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
        filterChain.doFilter(request, response)
    }
}