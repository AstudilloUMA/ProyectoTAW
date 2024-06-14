package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dto.Trabajador;
import es.uma.proyectogrupo18.entity.TrabajadorEntity;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService extends DTOService<Trabajador, TrabajadorEntity> {
/*
    @Autowired
    protected TrabajadorRepository trabajadorRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected RolTrabajadorRepository rolTrabajadorRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected DietaRepository dietaRepository;

    @Autowired
    protected FeedbackRepository feedbackRepository;

    @Autowired
    protected RutinaSemanalRepository rutinaSemanalRepository;

    @Autowired
    protected SesionDeEjercicioRepository sesionDeEjercicioRepository;

    // Método para obtener todos los trabajadores
    public List<Trabajador> getAllTrabajadores() {
        List<TrabajadorEntity> trabajadores = trabajadorRepository.findAll();
        return this.entidadesADTO(trabajadores);
    }

    // Método para obtener un trabajador por su ID
    public Trabajador getTrabajadorById(Integer id) {
        TrabajadorEntity trabajador = trabajadorRepository.findById(id).orElse(null);
        if (trabajador != null) {
            return trabajador.toDTO();
        } else {
            return null;
        }
    }

    // Método para borrar un trabajador
    public void deleteTrabajador(Integer id) {
        trabajadorRepository.deleteById(id);
    }

    // Método para guardar un trabajador
    public void guardarTrabajador(Trabajador trabajadorDTO) {
        Integer id = trabajadorDTO.getId();
        TrabajadorEntity trabajadorEntity = this.trabajadorRepository.findById(id).orElse(new TrabajadorEntity());
        trabajadorEntity.setId(id);
        trabajadorEntity.setUsuario(this.usuarioRepository.findById(id).orElse(null));
        trabajadorEntity.setRol(this.rolTrabajadorRepository.findById(trabajadorDTO.getRol().getId()).orElse(null));
        trabajadorEntity.setClientes(new ArrayList<>(this.clienteRepository.findAllById(trabajadorDTO.getClientesEntrenador().stream().map(Cliente::getId).collect(Collectors.toList()))));
        trabajadorEntity.setDietas(new ArrayList<>(this.dietaRepository.findAllById(trabajadorDTO.getDietas().stream().map(Dieta::getId).collect(Collectors.toList()))));
        trabajadorEntity.setFeedbacks(new ArrayList<>(this.feedbackRepository.findAllById(trabajadorDTO.getFeedbacks().stream().map(Feedback::getId).collect(Collectors.toList()))));
        trabajadorEntity.setRutinaSemanals(new ArrayList<>(this.rutinaSemanalRepository.findAllById(trabajadorDTO.getRutinaSemanals().stream().map(RutinaSemanal::getId).collect(Collectors.toList()))));
        trabajadorEntity.setSesionDeEjercicios(new ArrayList<>(this.sesionDeEjercicioRepository.findAllById(trabajadorDTO.getSesionDeEjercicios().stream().map(SesionDeEjercicio::getId).collect(Collectors.toList()))));
        this.trabajadorRepository.save(trabajadorEntity);
    }
    */
}
