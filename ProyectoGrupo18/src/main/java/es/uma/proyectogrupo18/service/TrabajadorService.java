package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.dto.Trabajador;
import es.uma.proyectogrupo18.entity.TrabajadorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorService extends DTOService<Trabajador, TrabajadorEntity> {

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
    public void guardarTrabajador(Trabajador trabajador) {
        Integer id = trabajador.getId();
        TrabajadorEntity trabajadorEntity = this.trabajadorRepository.findById(id).orElse(new TrabajadorEntity());
        trabajadorEntity.setId(id);
        trabajadorEntity.setUsuario(this.usuarioRepository.findById(id).orElse(null));
        trabajadorEntity.setRol(this.rolTrabajadorRepository.findById(trabajador.getRol().getId()).orElse(null));
        trabajadorEntity.setClientesEntrenador(this.clienteRepository.findAllById(trabajador.getClientesEntrenador()));
        trabajadorEntity.setClientesDietista(this.clienteRepository.findAllById(trabajador.getClientesDietista()));
        trabajadorEntity.setDietas(this.dietaRepository.findAllById(trabajador.getDietas()));
        trabajadorEntity.setFeedbacks(this.feedbackRepository.findAllById(trabajador.getFeedbacks()));
        trabajadorEntity.setRutinaSemanals(this.rutinaSemanalRepository.findAllById(trabajador.getRutinaSemanal()));
        trabajadorEntity.setSesionDeEjercicios(this.sesionDeEjercicioRepository.findAllById(trabajador.getSesionDeEjercicios()));
        this.trabajadorRepository.save(trabajadorEntity);
    }
}
