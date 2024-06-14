package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.dto.Dieta;
import es.uma.proyectogrupo18.entity.DietaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietaService extends DTOService<Dieta, DietaEntity> {

    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComidaRepository comidaRepository;

    @Autowired
    private FeedbackdietaRepository feedbackDietaRepository;

    // Método para obtener todas las dietas
    public List<Dieta> getAllDietas() {
        List<DietaEntity> dietas = dietaRepository.findAll();
        return this.entidadesADTO(dietas);
    }

    // Método para obtener una dieta por su ID
    public Dieta getDietaById(Integer id) {
        DietaEntity dieta = dietaRepository.findById(id).orElse(null);
        if (dieta != null) {
            return dieta.toDTO();
        } else {
            return null;
        }
    }

    // Método para borrar una dieta
    public void deleteDieta(Integer id) {
        dietaRepository.deleteById(id);
    }

    // Método para guardar una dieta
    public void guardarDieta(Dieta dieta) {
        DietaEntity dietaEntity = this.dietaRepository.findById(dieta.getId()).orElse(new DietaEntity());
        dietaEntity.setId(dieta.getId());
        dietaEntity.setNombre(dieta.getNombre());
        dietaEntity.setNumComidas(dieta.getNumComidas());
        dietaEntity.setTipo(dieta.getTipo());
        dietaEntity.setFechaInicio(dieta.getFechaInicio());
        dietaEntity.setFechaFin(dieta.getFechaFin());
        dietaEntity.setTrabajador(this.trabajadorRepository.findById(dieta.getTrabajador().getId()).orElse(null));
        dietaEntity.setClientes(this.clienteRepository.findAllById(dieta.getClientes()));
        dietaEntity.setComidas(this.comidaRepository.findAllById(dieta.getComidas()));
        dietaEntity.setFeedbackdietas(this.feedbackDietaRepository.findAllById(dieta.getFeedbackdietas()));
        this.dietaRepository.save(dietaEntity);
    }
}
