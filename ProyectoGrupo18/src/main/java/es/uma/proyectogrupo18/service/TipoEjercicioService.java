/*
 * 
 *  Alvaro Morales Perujo -> 100%
 * 
 */

package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.TipoEjercicioRepository;
import es.uma.proyectogrupo18.dto.TipoEjercicio;
import es.uma.proyectogrupo18.entity.TipoEjercicioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEjercicioService extends DTOService<TipoEjercicio, TipoEjercicioEntity> {

    @Autowired
    private TipoEjercicioRepository tipoEjercicioRepository;

    // Método para obtener todos los tipos de ejercicio
    public List<TipoEjercicio> getAllTiposEjercicio() {
        List<TipoEjercicioEntity> tipos = tipoEjercicioRepository.findAll();
        return this.entidadesADTO(tipos);
    }

    // Método para obtener un tipo de ejercicio por su ID
    public TipoEjercicio getTipoEjercicioById(Integer id) {
        TipoEjercicioEntity tipo = tipoEjercicioRepository.findById(id).orElse(null);
        if (tipo != null) {
            return tipo.toDTO();
        } else {
            return null;
        }
    }

    public TipoEjercicio getTipoEjercicioByNombre(String ejTipo) {
        TipoEjercicioEntity tipo = tipoEjercicioRepository.findByNombre(ejTipo);
        if (tipo != null) {
            return tipo.toDTO();
        } else {
            return null;
        }
    }
}
