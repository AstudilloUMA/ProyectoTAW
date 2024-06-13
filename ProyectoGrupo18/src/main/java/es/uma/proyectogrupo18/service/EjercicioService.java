package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.EjercicioRepository;
import es.uma.proyectogrupo18.dto.EjercicioDTO;
import es.uma.proyectogrupo18.entity.EjercicioEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private TipoEjercicioService tipoEjercicioService;


    public Optional<EjercicioDTO> getEjercicioById(Integer id) {
        Optional<EjercicioEntity> ejercicioEntity = ejercicioRepository.findById(id);
        return ejercicioEntity.map(this::convertToDTO);
    }

    public EjercicioDTO convertToDTO(EjercicioEntity ejercicioEntity){
        Integer id = ejercicioEntity.getId();

        return EjercicioDTO.builder()
                .id(ejercicioEntity.getId())
                .video(ejercicioEntity.getVideo())
                .nombre(ejercicioEntity.getNombre())
           //     .tipo(tipoEjercicioService.getTipoEjercicioById(ejercicioEntity.getTipo().getId()).orElse(null))
           //     .sesionDeEjercicios()
                .build();
    }
}
