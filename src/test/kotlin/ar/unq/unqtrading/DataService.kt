package ar.unq.unqtrading

import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.repositories.OrdenDeVentaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DataService {
    @Autowired
    lateinit var ordenDeVentaRepository: OrdenDeVentaRepository

    fun crearDatos() {
            var orden1 = OrdenDeVenta()
            var orden2 = OrdenDeVenta()
            orden1.nombreEmpresa = "UNQ"
            orden2.nombreEmpresa = "UNQ"
            orden1.cantidadDeAcciones = 100
            orden2.cantidadDeAcciones = 200
            orden1.precio = 1000
            orden2.precio = 2000
            orden1.fechaDeVencimiento = LocalDate.of(2020,10,20)
            orden2.fechaDeVencimiento = LocalDate.of(2020,10,20)
            ordenDeVentaRepository.save(orden1)
            ordenDeVentaRepository.save(orden2)
    }

    fun eliminarDatos() {
        ordenDeVentaRepository.deleteAll()
    }
}