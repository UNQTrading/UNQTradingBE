package ar.unq.unqtrading.services

import ar.unq.unqtrading.repositories.OrdenDeVentaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrdenDeVentaService {
    @Autowired
    lateinit var ordenDeVentaRepository: OrdenDeVentaRepository
}