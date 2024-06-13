package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dto.*;
import es.uma.proyectogrupo18.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RutinaSemanalService rutinaSemanalService;

    @Autowired
    private DietaService dietaService;

    @Autowired
    private TrabajadorService trabajadorService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private FeedbackDietaService feedbackDietaService;

    @Autowired
    private SesionDeEjercicioService sesionDeEjercicioService;

    // Método para obtener todos los clientes
    public List<ClienteDTO> getAllClientes() {
        List<ClienteEntity> clientes = clienteRepository.findAll();
        return clientes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Método para obtener un cliente por su ID
    public Optional<ClienteDTO> getClienteById(Integer id) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);
        return cliente.map(this::convertToDTO);
    }

    // Método para convertir una entidad ClienteEntity a un DTO ClienteDTO
    public ClienteDTO convertToDTO(ClienteEntity clienteEntity) {
        UsuarioDTO usuarioDTO = usuarioService.getUsuarioById(clienteEntity.getUsuario().getId()).orElse(null);
        RutinaSemanalDTO rutinaSemanalDTO = rutinaSemanalService.getRutinaSemanalById(clienteEntity.getRutinaSemanal().getId()).orElse(null);
        DietaDTO dietaDTO = dietaService.getDietaById(clienteEntity.getDieta().getId()).orElse(null);
        TrabajadorDTO dietistaDTO = trabajadorService.getTrabajadorById(clienteEntity.getDietista().getId()).orElse(null);
        TrabajadorDTO entrenadorDTO = trabajadorService.getTrabajadorById(clienteEntity.getEntrenador().getId()).orElse(null);
        Set<FeedbackDTO> feedbacksDTO = clienteEntity.getFeedbacks().stream().map(feedbackService::convertToDTO).collect(Collectors.toSet());
        Set<FeedbackDietaDTO> feedbackDietasDTO = clienteEntity.getFeedbackDietas().stream().map(feedbackDietaService::convertToDTO).collect(Collectors.toSet());
        Set<SesionDeEjercicioDTO> sesionDeEjerciciosDTO = clienteEntity.getSesionDeEjercicios().stream().map(sesionDeEjercicioService::convertToDTO).collect(Collectors.toSet());

        return ClienteDTO.builder()
                .id(clienteEntity.getId())
                .usuario(usuarioDTO)
                .peso(clienteEntity.getPeso())
                .altura(clienteEntity.getAltura())
                .edad(clienteEntity.getEdad())
                .rutinaSemanal(rutinaSemanalDTO)
                .dieta(dietaDTO)
                .dietista(dietistaDTO)
                .entrenador(entrenadorDTO)
                .feedbacks(feedbacksDTO)
                .feedbackDietas(feedbackDietasDTO)
                .sesionDeEjercicios(sesionDeEjerciciosDTO)
                .build();
    }
}
