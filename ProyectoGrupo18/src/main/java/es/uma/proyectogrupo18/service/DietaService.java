package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.DietaRepository;
import es.uma.proyectogrupo18.dto.DietaDTO;
import es.uma.proyectogrupo18.entity.DietaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DietaService {

    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private TrabajadorService trabajadorService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ComidaService comidaService;

    @Autowired
    private FeedbackDietaService feedbackDietaService;

    public Optional<DietaDTO> getDietaById(Integer id) {
        Optional<DietaEntity> dietaEntity = dietaRepository.findById(id);
        return dietaEntity.map(this::convertToDTO);
    }

    public List<DietaDTO> getAllDietas() {
        List<DietaEntity> dietaEntities = dietaRepository.findAll();
        return dietaEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DietaDTO convertToDTO(DietaEntity dietaEntity) {
        return DietaDTO.builder()
                .id(dietaEntity.getId())
                .nombre(dietaEntity.getNombre())
                .numComidas(dietaEntity.getNumComidas())
                .tipo(dietaEntity.getTipo())
                .fechaInicio(dietaEntity.getFechaInicio())
                .fechaFin(dietaEntity.getFechaFin())
                .trabajador(trabajadorService.convertToDTO(dietaEntity.getTrabajador()))
                .clientes(dietaEntity.getClientes().stream()
                        .map(clienteService::convertToDTO)
                        .collect(Collectors.toList()))
                .comidas(dietaEntity.getComidas().stream()
                        .map(comidaService::convertToDTO)
                        .collect(Collectors.toList()))
                .feedbackdietas(dietaEntity.getFeedbackdietas().stream()
                        .map(feedbackDietaService::convertToDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
