package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.dto.UsuarioDTO;
import es.uma.proyectogrupo18.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TrabajadorService trabajadorService;

    public Optional<UsuarioDTO> getUsuarioById(Integer id) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        return usuarioEntity.map(this::convertToDTO);
    }

    public UsuarioDTO convertToDTO(UsuarioEntity usuarioEntity) {
        Integer id = usuarioEntity.getId();

        return UsuarioDTO.builder()
                .id(usuarioEntity.getId())
                .usuario(usuarioEntity.getUsuario())
                .nombre(usuarioEntity.getNombre())
                .apellidos(usuarioEntity.getApellidos())
                .dni(usuarioEntity.getDni())
                .edad(usuarioEntity.getEdad())
                .sexo(usuarioEntity.getSexo())
                .administrador(administradorService.getAdministradorById(id).orElse(null))
                .cliente(clienteService.getClienteById(id).orElse(null))
                .trabajador(trabajadorService.getTrabajadorById(id).orElse(null))
                .build();
    }
}
