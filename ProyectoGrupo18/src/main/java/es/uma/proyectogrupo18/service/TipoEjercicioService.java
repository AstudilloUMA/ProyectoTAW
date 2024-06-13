package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.TipoEjercicioRepository;
import es.uma.proyectogrupo18.dto.EjercicioDTO;
import es.uma.proyectogrupo18.entity.TipoEjercicioEntity;
import es.uma.proyectogrupo18.dto.TipoEjercicioDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TipoEjercicioService {

    @Autowired
    private TipoEjercicioRepository tipoEjercicioRepository;

    @Autowired
    private EjercicioService ejercicioService;

    public Optional<TipoEjercicioDTO> getTipoEjercicioById(Integer id) {
        Optional<TipoEjercicioEntity> tipoEjercicioEntity = tipoEjercicioRepository.findById(id);
        return tipoEjercicioEntity.map(this::convertToDTO);
    }

    public TipoEjercicioDTO convertToDTO(TipoEjercicioEntity tipoEjercicioEntity) {
        Integer id = tipoEjercicioEntity.getId();
        Set<EjercicioDTO> ejerciciosDTO = tipoEjercicioEntity.getEjercicios().stream()
                .map(ejercicioService::convertToDTO)
                .collect(Collectors.toSet());

        return TipoEjercicioDTO.builder()
                .id(tipoEjercicioEntity.getId())
                .tipo(tipoEjercicioEntity.getTipo())
                .ejercicios(ejerciciosDTO) // Añade el conjunto de ejercicios aquí
                .build();
    }

    public Set<EjercicioDTO> getEjerciciosDTO(TipoEjercicioEntity tipoEjercicioEntity) {
        return tipoEjercicioEntity.getEjercicios().stream()
                .map(ejercicioService::convertToDTO)
                .collect(Collectors.toSet());
    }
}
