/*
 * 
 *  Alvaro Morales Perujo -> 70%
 * 
 */

package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.AdministradorRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.dto.Administrador;
import es.uma.proyectogrupo18.entity.AdministradorEntity;
import es.uma.proyectogrupo18.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministradorService extends DTOService<Administrador, AdministradorEntity> {

    @Autowired
    protected AdministradorRepository administradorRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    // Método para obtener todos los administradores
    public List<Administrador> getAllAdministradores() {
        List<AdministradorEntity> administradores = administradorRepository.findAll();
        return this.entidadesADTO(administradores);
    }

    // Método para obtener un administrador por su ID
    public Administrador getAdministradorById(Integer id) {
        AdministradorEntity administrador = administradorRepository.findById(id).orElse(null);
        if (administrador != null) {
            return administrador.toDTO();
        } else {
            return null;
        }
    }

    // Método para borrar un usuario
    public void deleteAdministrador(Integer id) {
        administradorRepository.deleteById(id);
    }

    public void guardarAdministrador(Administrador administrador) {
        AdministradorEntity administradorE = this.administradorRepository.findById(administrador.getId()).orElse(new AdministradorEntity());
        administradorE.setId(administrador.getId());
        administradorE.setUsuario(this.usuarioRepository.findById(administrador.getId()).orElse(null));
        this.administradorRepository.save(administradorE);
    }

    public void crearAdministrador(Administrador administrador) {
        if (administrador.getId() == null) {
            throw new IllegalArgumentException("El ID del administrador no puede ser nulo");
        }

        UsuarioEntity usuarioEntity = usuarioRepository.findById(administrador.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        AdministradorEntity administradorEntity = new AdministradorEntity();
        administradorEntity.setId(administrador.getId());
        administradorEntity.setUsuario(usuarioEntity);

        usuarioEntity.setAdministrador(administradorEntity);

        usuarioRepository.save(usuarioEntity);  // Esto también debería guardar el administrador debido al cascade
    }
}
