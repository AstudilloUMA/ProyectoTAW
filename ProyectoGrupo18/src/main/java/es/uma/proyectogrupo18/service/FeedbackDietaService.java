package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.FeedbackdietaRepository;
import es.uma.proyectogrupo18.dto.FeedbackDietaDTO;
import es.uma.proyectogrupo18.entity.FeedbackdietaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackDietaService {

    @Autowired
    private FeedbackdietaRepository feedbackDietaRepository;

    @Autowired
    private DietaService dietaService;

    @Autowired
    private ClienteService clienteService;

    public Optional<FeedbackDietaDTO> getFeedbackDietaById(Integer id) {
        Optional<FeedbackdietaEntity> feedbackDietaEntity = feedbackDietaRepository.findById(id);
        return feedbackDietaEntity.map(this::convertToDTO);
    }

    public FeedbackDietaDTO convertToDTO(FeedbackdietaEntity feedbackDietaEntity) {
        return FeedbackDietaDTO.builder()
                .id(feedbackDietaEntity.getId())
                .calificacion(feedbackDietaEntity.getCalificacion())
                .comentarios(feedbackDietaEntity.getComentarios())
                .dietaCodigo(dietaService.convertToDTO(feedbackDietaEntity.getDietaCodigo()))
                .cliente(clienteService.convertToDTO(feedbackDietaEntity.getCliente()))
                .build();
    }
}