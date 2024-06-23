/*
 * 
 *  Alvaro Morales Perujo -> 100%
 * 
 */

package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.RutinaSemanalRepository;
import es.uma.proyectogrupo18.dao.SesionDeEjercicioRepository;
import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dto.RutinaSemanal;
import es.uma.proyectogrupo18.dto.SesionDeEjercicio;
import es.uma.proyectogrupo18.entity.RutinaSemanalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RutinaSemanalService extends DTOService<RutinaSemanal, RutinaSemanalEntity> {

    @Autowired
    private RutinaSemanalRepository rutinaSemanalRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SesionDeEjercicioRepository sesionDeEjercicioRepository;

    // Método para obtener todas las rutinas semanales
    public List<RutinaSemanal> getAllRutinasSemanales() {
        List<RutinaSemanalEntity> rutinas = rutinaSemanalRepository.findAll();
        return this.entidadesADTO(rutinas);
    }

    // Método para obtener una rutina semanal por su ID
    public RutinaSemanal getRutinaSemanalById(Integer id) {
        RutinaSemanalEntity rutina = rutinaSemanalRepository.findById(id).orElse(null);
        if (rutina != null) {
            return rutina.toDTO();
        } else {
            return null;
        }
    }

    // Método para obtener una rutina semanal por el id del entrenador
    public List<RutinaSemanal> getRutinaSemanalByTrainerId(Integer id) {
        List<RutinaSemanalEntity> rutinas = rutinaSemanalRepository.findRutinasByTrabajadorId(id);
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaSemanal> getRutinaSemanalFiltradas(Integer trabajador, String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        List<RutinaSemanalEntity> rutinas = rutinaSemanalRepository.findRutinasFiltradas(trabajador, nombre, fechaInicio, fechaFin);
        return this.entidadesADTO(rutinas);
    }


    // Método para borrar una rutina semanal
    public void deleteRutinaSemanal(Integer id) {
        rutinaSemanalRepository.deleteById(id);
    }

    // Método para guardar una rutina semanal
    public void guardarRutinaSemanal(RutinaSemanal rutina) {
        RutinaSemanalEntity rutinaEntity = this.rutinaSemanalRepository.findById(rutina.getId()).orElse(new RutinaSemanalEntity());
        rutinaEntity.setNombre(rutina.getNombre());
        rutinaEntity.setFechaInicio(rutina.getFechaInicio());
        rutinaEntity.setFechaFin(rutina.getFechaFin());
        rutinaEntity.setTrabajador(this.trabajadorRepository.findById(rutina.getTrabajador().getId()).orElse(null));
        rutinaEntity.setClientes(this.clienteRepository.findAllById(rutina.getClientes()));
        rutinaEntity.setSesionDeEjercicios(rutina.getSesionesDeEjercicio() != null ? this.sesionDeEjercicioRepository.findAllById(rutina.getSesionesDeEjercicio()) : null);
        this.rutinaSemanalRepository.save(rutinaEntity);
        rutina.setId(rutinaEntity.getId());
    }

    public String generarNombreDuplicado(String nombreBase) {
        String baseName = nombreBase.replaceAll(" - copia( \\d+)?$", "");
        long count = rutinaSemanalRepository.countByNombreBase(baseName);
        if (count == 0) {
            return baseName + " - copia";
        } else {
            return baseName + " - copia " + (count + 1);
        }
    }

}
