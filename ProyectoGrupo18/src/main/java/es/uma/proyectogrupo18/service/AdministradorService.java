package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.AdministradorRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.dto.Administrador;
import es.uma.proyectogrupo18.entity.AdministradorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministradorService extends DTOService<Administrador, AdministradorEntity> {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

}
