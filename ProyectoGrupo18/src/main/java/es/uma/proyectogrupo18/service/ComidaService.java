package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.ComidaRepository;
import es.uma.proyectogrupo18.dto.ComidaDTO;
import es.uma.proyectogrupo18.entity.ComidaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;

    @Autowired
    private DietaService dietaService;

    @Autowired
    private MenuService menuService;

    public Optional<ComidaDTO> getComidaById(Integer id) {
        Optional<ComidaEntity> comidaEntity = comidaRepository.findById(id);
        return comidaEntity.map(this::convertToDTO);
    }

    public ComidaDTO convertToDTO(ComidaEntity comidaEntity) {
        return ComidaDTO.builder()
                .id(comidaEntity.getId())
                .nombre(comidaEntity.getNombre())
                .kilocaloriasTotales(comidaEntity.getKilocaloriasTotales())
                .orden(comidaEntity.getOrden())
                .dietas(comidaEntity.getDietas().stream()
                        .map(dietaService::convertToDTO)
                        .collect(Collectors.toList()))
                .menus(comidaEntity.getMenus().stream()
                        .map(menuService::convertToDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
