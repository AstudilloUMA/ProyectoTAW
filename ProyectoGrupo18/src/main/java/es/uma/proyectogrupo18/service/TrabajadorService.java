package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dto.*;
import es.uma.proyectogrupo18.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolTrabajadorService rolTrabajadorService;

    // Método para obtener todos los trabajadores
    public List<TrabajadorDTO> getAllTrabajadores() {
        List<TrabajadorEntity> trabajadores = trabajadorRepository.findAll();
        return trabajadores.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Método para obtener un trabajador por su ID
    public Optional<TrabajadorDTO> getTrabajadorById(Integer id) {
        Optional<TrabajadorEntity> trabajador = trabajadorRepository.findById(id);
        return trabajador.map(this::convertToDTO);
    }

    // Método para convertir una entidad TrabajadorEntity a un DTO TrabajadorDTO
    private TrabajadorDTO convertToDTO(TrabajadorEntity trabajadorEntity) {
        Integer id = trabajadorEntity.getId();
        UsuarioDTO usuarioDTO = usuarioService.getUsuarioById(id).orElse(null);
        RolTrabajadorDTO rolTrabajadorDTO = rolTrabajadorService.getRolById(trabajadorEntity.getRol().getId()).orElse(null);
        ClienteDTO clienteDTO = clienteService.getClienteById(trabajadorEntity.getId()).orElse(null);

        return TrabajadorDTO.builder()
                .id(trabajadorEntity.getId())
                .usuario(usuarioDTO)
                .rol(rolTrabajadorDTO)
                .clientesDietista(trabajadorEntity.getClientesDietista().stream().map(ClienteDTO::new).collect(Collectors.toSet()))
                .clientesEntrenador(trabajadorEntity.getClientesEntrenador().stream().map(ClienteDTO::new).collect(Collectors.toSet()))
                .dietas(trabajadorEntity.getDietas().stream().map(DietaDTO::new).collect(Collectors.toSet()))
                .feedbacks(trabajadorEntity.getFeedbacks().stream().map(FeedbackDTO::new).collect(Collectors.toSet()))
                .rutinaSemanals(trabajadorEntity.getRutinaSemanals().stream().map(RutinaSemanalDTO::new).collect(Collectors.toSet()))
                .sesionDeEjercicios(trabajadorEntity.getSesionDeEjercicios().stream().map(SesionDeEjercicioDTO::new).collect(Collectors.toSet()))
                .build();
    }
}
