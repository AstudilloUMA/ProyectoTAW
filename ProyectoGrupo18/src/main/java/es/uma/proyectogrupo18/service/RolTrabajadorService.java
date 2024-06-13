package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.RolTrabajadorRepository;
import es.uma.proyectogrupo18.dto.RolTrabajadorDTO;
import es.uma.proyectogrupo18.entity.RolTrabajadorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolTrabajadorService {

    @Autowired
    private RolTrabajadorRepository rolTrabajadorRepository;

    public Optional<RolTrabajadorDTO> getRolById(Integer id) {
        Optional<RolTrabajadorEntity> rolEntity = rolTrabajadorRepository.findById(id);
        return rolEntity.map(this::convertToDTO);
    }

    public RolTrabajadorDTO convertToDTO(RolTrabajadorEntity rolTrabajadorEntity) {
        return RolTrabajadorDTO.builder()
                .id(rolTrabajadorEntity.getId())
                .rol(rolTrabajadorEntity.getRol())
                .build();
    }
}
