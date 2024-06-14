package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.EjercicioRepository;
import es.uma.proyectogrupo18.dao.SesionDeEjercicioRepository;
import es.uma.proyectogrupo18.dao.TipoEjercicioRepository;
import es.uma.proyectogrupo18.dto.Ejercicio;
import es.uma.proyectogrupo18.entity.EjercicioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EjercicioService extends DTOService<Ejercicio, EjercicioEntity> {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private TipoEjercicioRepository tipoEjercicioRepository;

    @Autowired
    private SesionDeEjercicioRepository sesionDeEjercicioRepository;

    // Método para obtener todos los ejercicios
    public List<Ejercicio> getAllEjercicios() {
        List<EjercicioEntity> ejercicios = ejercicioRepository.findAll();
        return this.entidadesADTO(ejercicios);
    }

    // Método para obtener un ejercicio por su ID
    public Ejercicio getEjercicioById(Integer id) {
        EjercicioEntity ejercicio = ejercicioRepository.findById(id).orElse(null);
        if (ejercicio != null) {
            return ejercicio.toDTO();
        } else {
            return null;
        }
    }

    // Método para borrar un ejercicio
    public void deleteEjercicio(Integer id) {
        ejercicioRepository.deleteById(id);
    }

    // Método para guardar un ejercicio
    public void guardarEjercicio(Ejercicio ejercicio) {
        Integer id = ejercicio.getId();
        EjercicioEntity ejercicioEntity = this.ejercicioRepository.findById(id).orElse(new EjercicioEntity());
        ejercicioEntity.setId(id);
        ejercicioEntity.setTipo(this.tipoEjercicioRepository.findById(ejercicio.getTipo().getId()).orElse(null));
        ejercicioEntity.setNombre(ejercicio.getNombre());
        ejercicioEntity.setVideo(ejercicio.getVideo());
        ejercicioEntity.setSesionDeEjercicios(this.sesionDeEjercicioRepository.findAllById(ejercicio.getSesionDeEjercicios()));
        this.ejercicioRepository.save(ejercicioEntity);
    }
}
