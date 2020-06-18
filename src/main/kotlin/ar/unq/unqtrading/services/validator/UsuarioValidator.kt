package ar.unq.unqtrading.services.validator

import ar.unq.unqtrading.entities.Usuario
import ar.unq.unqtrading.repositories.UsuarioRepository
import ar.unq.unqtrading.services.exceptions.UsuarioYaExistenteException
import ar.unq.unqtrading.utils.ObjectStructureUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UsuarioValidator {
    @Autowired
    lateinit var usuarioRepository: UsuarioRepository

    fun validate(usuario: Usuario) {
        ObjectStructureUtils.checkEmptyAttributes(usuario)
        if (usuarioRepository.findByDni(usuario.dni) != null) {
            throw UsuarioYaExistenteException("Ya hay un usuario registrado con el dni ${usuario.dni}")
        }
        if (usuarioRepository.findByCuil(usuario.cuil) != null) {
            throw UsuarioYaExistenteException("Ya hay un usuario registrado con el cuil ${usuario.cuil}")
        }
        if(usuarioRepository.findByEmail(usuario.email) != null ) {
            throw UsuarioYaExistenteException("Ya hay un usuario registrado con el email ${usuario.email}")
        }
        if(usuarioRepository.findByUsername(usuario.username) != null ) {
            throw UsuarioYaExistenteException("Ya hay un usuario registrado con el nombre de usuario ${usuario.username}")
        }
    }
}