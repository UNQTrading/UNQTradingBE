package ar.unq.unqtrading

import ar.unq.unqtrading.dto.OrdenDeVentaDTO
import ar.unq.unqtrading.entities.Empresa
import ar.unq.unqtrading.entities.OrdenDeVenta
import ar.unq.unqtrading.repositories.EmpresaRepository
import ar.unq.unqtrading.repositories.OrdenDeVentaRepository
import ar.unq.unqtrading.services.interfaces.IEmpresaService
import ar.unq.unqtrading.services.interfaces.IOrdenDeVentaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DataService {
    @Autowired
    lateinit var ordenDeVentaService: IOrdenDeVentaService
    @Autowired
    lateinit var ordenDeVentaRepository: OrdenDeVentaRepository
    @Autowired
    lateinit var empresaService: IEmpresaService
    @Autowired
    lateinit var empresaRepository: EmpresaRepository
    fun crearDatos() {
        var orden1 = OrdenDeVentaDTO()
        var orden2 = OrdenDeVentaDTO()
        var empresa = Empresa()
        var coca = Empresa()
        coca.nombreEmpresa = "Coca-Cola"
        coca.contraseña = "12345"
        coca.email = "coca@coca.com"
        coca.cuil = 123456789
        empresa.nombreEmpresa = "UNQ"
        empresa.contraseña = "1234578"
        empresa.email = "unq@unq.com"
        empresa.cuil = 98765541
        empresaService.save(empresa)
        empresaService.save(coca)
        orden1.nombreEmpresa = "UNQ"
        orden2.nombreEmpresa = "UNQ"
        orden1.cantidadDeAcciones = 100
        orden2.cantidadDeAcciones = 200
        orden1.precio = 1000
        orden2.precio = 2000
        orden1.fechaDeVencimiento = LocalDate.of(2020,10,20)
        orden2.fechaDeVencimiento = LocalDate.of(2020,10,20)
        ordenDeVentaService.saveOrdenDeVenta(orden1)
        ordenDeVentaService.saveOrdenDeVenta(orden2)
    }

    fun eliminarDatos() {
        ordenDeVentaRepository.deleteAll()
        empresaRepository.deleteAll()
    }
}