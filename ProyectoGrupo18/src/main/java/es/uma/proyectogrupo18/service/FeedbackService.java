package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.FeedbackRepository;
import es.uma.proyectogrupo18.dao.SesionDeEjercicioRepository;
import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dto.Feedback;
import es.uma.proyectogrupo18.entity.FeedbackEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService extends DTOService<Feedback, FeedbackEntity> {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private SesionDeEjercicioRepository sesionDeEjercicioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    // Método para obtener todos los feedbacks
    public List<Feedback> getAllFeedbacks() {
        List<FeedbackEntity> feedbacks = feedbackRepository.findAll();
        return this.entidadesADTO(feedbacks);
    }

    // Método para obtener un feedback por su ID
    public Feedback getFeedbackById(Integer id) {
        FeedbackEntity feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback != null) {
            return feedback.toDTO();
        } else {
            return null;
        }
    }

    public List<Feedback> getFeedbackByClienteId(Integer cliente) {
        List<FeedbackEntity> feedbacks = feedbackRepository.findByCliente(cliente);
        return this.entidadesADTO(feedbacks);
    }

    public Feedback getFeedbackByClienteYSesion(Integer cliente, Integer sesion) {
        FeedbackEntity feedback = feedbackRepository.findBySesion(cliente, sesion);
        if (feedback != null) {
            return feedback.toDTO();
        } else {
            return null;
        }
    }


    // Método para borrar un feedback
    public void deleteFeedback(Integer id) {
        feedbackRepository.deleteById(id);
    }

    // Método para guardar un feedback
    public void guardarFeedback(Feedback feedback) {
        FeedbackEntity feedbackEntity = this.feedbackRepository.findById(feedback.getId()).orElse(new FeedbackEntity());
        feedbackEntity.setId(feedback.getId());
        feedbackEntity.setCalificacion(feedback.getCalificacion());
        feedbackEntity.setEstadoDelCliente(feedback.getEstadoDelCliente());
        feedbackEntity.setComentarios(feedback.getComentarios());
        feedbackEntity.setSeries(feedback.getSeries());
        feedbackEntity.setPeso(feedback.getPeso());
        feedbackEntity.setRepeticiones(feedback.getRepeticiones());
        feedbackEntity.setSesion(this.sesionDeEjercicioRepository.findById(feedback.getSesion().getId()).orElse(null));
        feedbackEntity.setCliente(this.clienteRepository.findById(feedback.getCliente().getId()).orElse(null));
        feedbackEntity.setTrabajador(this.trabajadorRepository.findById(feedback.getTrabajador().getId()).orElse(null));
        this.feedbackRepository.save(feedbackEntity);
    }
}
