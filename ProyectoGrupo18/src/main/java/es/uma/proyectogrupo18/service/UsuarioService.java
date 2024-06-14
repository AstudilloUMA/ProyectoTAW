package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.AdministradorRepository;
import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.dto.Usuario;
import es.uma.proyectogrupo18.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService extends DTOService<Usuario, UsuarioEntity> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    protected TrabajadorRepository trabajadorRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    // Método para obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return this.entidadesADTO(usuarios);
    }

    // Método para obtener un usuario por su ID
    public Usuario getUsuarioById(Integer id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            return usuario.toDTO();
        } else {
            return null;
        }
    }

    public Usuario autentica(String nombre, String pwd) {
        UsuarioEntity usuario = usuarioRepository.autentica(nombre, pwd);
        if (usuario != null) {
            return usuario.toDTO();
        } else {
            return null;
        }
    }



    // Método para borrar un usuario
    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    // Método para guardar un usuario
    public void guardarUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(usuario.getId()).orElse(new UsuarioEntity());
        usuarioEntity.setId(usuario.getId());
        usuarioEntity.setUsuario(usuario.getUsuario());
        usuarioEntity.setNombre(usuario.getNombre());
        usuarioEntity.setApellidos(usuario.getApellidos());
        usuarioEntity.setDni(usuario.getDni());
        usuarioEntity.setEdad(usuario.getEdad());
        usuarioEntity.setSexo(usuario.getSexo());
        usuarioEntity.setAdministrador(usuario.getAdministrador().getId() != null ? this.administradorRepository.findById(usuario.getAdministrador().getId()).orElse(null) : null);
        usuarioEntity.setCliente(usuario.getCliente().getId() != null ? this.clienteRepository.findById(usuario.getCliente().getId()).orElse(null) : null);
        usuarioEntity.setTrabajador(usuario.getTrabajador().getId() != null ? this.trabajadorRepository.findById(usuario.getTrabajador().getId()).orElse(null) : null);
        this.usuarioRepository.save(usuarioEntity);
    }
}
