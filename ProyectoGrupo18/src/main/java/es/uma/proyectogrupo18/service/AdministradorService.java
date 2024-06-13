package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.AdministradorRepository;
import es.uma.proyectogrupo18.dto.AdministradorDTO;
import es.uma.proyectogrupo18.entity.AdministradorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UsuarioService usuarioService;


    public Optional<AdministradorDTO> getAdministradorById(Integer id) {
        Optional<AdministradorEntity> administradorEntity = administradorRepository.findById(id);
        return administradorEntity.map(this::convertToDTO);
    }

    public AdministradorDTO convertToDTO(AdministradorEntity administradorEntity){
        Integer id = administradorEntity.getId();

        return AdministradorDTO.builder()
                .id(administradorEntity.getId())
                .usuario(usuarioService.getUsuarioById(id).orElse(null))
                .build();
    }


}
