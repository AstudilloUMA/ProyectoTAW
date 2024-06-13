package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ClienteService clienteService;



}
