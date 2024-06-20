package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.DietaRepository;
import es.uma.proyectogrupo18.dao.FeedbackdietaRepository;
import es.uma.proyectogrupo18.dto.Cliente;
import es.uma.proyectogrupo18.dto.Dieta;
import es.uma.proyectogrupo18.dto.FeedbackDieta;
import es.uma.proyectogrupo18.entity.ClienteEntity;
import es.uma.proyectogrupo18.entity.DietaEntity;
import es.uma.proyectogrupo18.entity.FeedbackdietaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackDietaService extends DTOService<FeedbackDieta, FeedbackdietaEntity> {

    @Autowired
    private FeedbackdietaRepository feedbackDietaRepository;

    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Método para obtener todos los feedbacks de dieta
    public List<FeedbackDieta> getAllFeedbackDietas() {
        List<FeedbackdietaEntity> feedbackDietas = feedbackDietaRepository.findAll();
        return this.entidadesADTO(feedbackDietas);
    }

    // Método para obtener un feedback de dieta por su ID
    public FeedbackDieta getFeedbackDietaById(Integer id) {
        FeedbackdietaEntity feedbackDieta = feedbackDietaRepository.findById(id).orElse(null);
        if (feedbackDieta != null) {
            return feedbackDieta.toDTO();
        } else {
            return null;
        }
    }

    public FeedbackDieta getFeedbackDietaByCliente(Cliente cliente, Dieta dieta) {
        ClienteEntity cli = this.clienteRepository.findById(cliente.getId()).orElse(null);
        DietaEntity die = this.dietaRepository.findById(dieta.getId()).orElse(null);
        FeedbackdietaEntity feedbackDieta = feedbackDietaRepository.findByCliente(cli, die);
        if (feedbackDieta != null) {
            return feedbackDieta.toDTO();
        } else {
            return null;
        }
    }

    // Método para borrar un feedback de dieta
    public void deleteFeedbackDieta(Integer id) {
        feedbackDietaRepository.deleteById(id);
    }

    // Método para guardar un feedback de dieta
    public void guardarFeedbackDieta(FeedbackDieta feedbackDieta) {
        FeedbackdietaEntity feedbackDietaEntity = this.feedbackDietaRepository.findById(feedbackDieta.getId()).orElse(new FeedbackdietaEntity());
        feedbackDietaEntity.setId(feedbackDieta.getId());
        feedbackDietaEntity.setCalificacion(feedbackDieta.getCalificacion());
        feedbackDietaEntity.setComentarios(feedbackDieta.getComentarios());
        feedbackDietaEntity.setDietaCodigo(this.dietaRepository.findById(feedbackDieta.getDietaCodigo().getId()).orElse(null));
        feedbackDietaEntity.setCliente(this.clienteRepository.findById(feedbackDieta.getCliente().getId()).orElse(null));
        this.feedbackDietaRepository.save(feedbackDietaEntity);
    }
}
