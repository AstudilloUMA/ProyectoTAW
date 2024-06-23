/*
 * 
 *  Alvaro Morales Perujo -> 100%
 * 
 */

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

    public List<Ejercicio> getAllEjerciciosOrdered() {
        List<EjercicioEntity> ejercicios = ejercicioRepository.findAllOrdered();
        return this.entidadesADTO(ejercicios);
    }

    public List<Ejercicio> getAllEjerciciosByTipo(Integer tipoEjercicio) {
        List<EjercicioEntity> ejercicios = ejercicioRepository.findByTipo(tipoEjercicio);
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
        EjercicioEntity ejercicioEntity = this.ejercicioRepository.findById(ejercicio.getId()).orElse(new EjercicioEntity());
        ejercicioEntity.setId(ejercicio.getId());
        ejercicioEntity.setTipo(this.tipoEjercicioRepository.findById(ejercicio.getTipo().getId()).orElse(null));
        ejercicioEntity.setNombre(ejercicio.getNombre());
        ejercicioEntity.setVideo(ejercicio.getVideo());
        ejercicioEntity.setSesionDeEjercicios(ejercicio.getSesionDeEjercicios()!=null?this.sesionDeEjercicioRepository.findAllById(ejercicio.getSesionDeEjercicios()):null);
        this.ejercicioRepository.save(ejercicioEntity);
    }

    public List<Ejercicio> getEjercicioByFiltro(String ejTipo, String ejNombre) {
        List<EjercicioEntity> ejercicios = ejercicioRepository.findByFiltro(ejTipo, ejNombre);
        return this.entidadesADTO(ejercicios);
    }
}
