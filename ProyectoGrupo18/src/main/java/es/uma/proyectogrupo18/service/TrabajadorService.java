package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dto.*;
import es.uma.proyectogrupo18.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolTrabajadorService rolTrabajadorService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DietaService dietaService;

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
        List<ClienteDTO> clientesDTO = clienteService.getAllClientes();
        List<DietaDTO> dietasDTO = dietaService.getAllDietas();
        List<FeedbackDTO> feedbacksDTO = feedbackService.getAllFeedbacks();
        List<RutinaSemanalDTO> rutinaSemanalDTO = rutinaSemanalService.getAllRutinaSemanal();
        List<SesionDeEjercicioDTO> sesionDeEjercicioDTO = sesionDeEjercicioService.getAllSesionDeEjercicio();

        return TrabajadorDTO.builder()
                .id(id)
                .usuario(usuarioDTO)
                .rol(rolTrabajadorDTO)
                .clientes(clientesDTO)
                .dietas(dietasDTO)
                .feedbacks(feedbacksDTO)
                .rutinaSemanal(rutinaSemanalDTO)
                .sesionDeEjercicios(sesionDeEjercicioDTO)
                .build();
    }
}
