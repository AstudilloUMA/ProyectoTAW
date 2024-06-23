/*
 * 
 *  Alvaro Morales Perujo -> 70%
 * 
 */

package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.RolTrabajadorRepository;
import es.uma.proyectogrupo18.dto.RolTrabajador;
import es.uma.proyectogrupo18.entity.RolTrabajadorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolTrabajadorService extends DTOService<RolTrabajador, RolTrabajadorEntity> {

    @Autowired
    private RolTrabajadorRepository rolTrabajadorRepository;

    // Método para obtener todos los roles de trabajador
    public List<RolTrabajador> getAllRolesTrabajador() {
        List<RolTrabajadorEntity> roles = rolTrabajadorRepository.findAll();
        return this.entidadesADTO(roles);
    }

    // Método para obtener un rol de trabajador por su ID
    public RolTrabajador getRolTrabajadorById(Integer id) {
        RolTrabajadorEntity rol = rolTrabajadorRepository.findById(id).orElse(null);
        if (rol != null) {
            return rol.toDTO();
        } else {
            return null;
        }
    }

}
