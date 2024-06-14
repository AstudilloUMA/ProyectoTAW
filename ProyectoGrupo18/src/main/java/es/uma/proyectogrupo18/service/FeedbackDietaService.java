package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.FeedbackdietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackDietaService {

    @Autowired
    private FeedbackdietaRepository feedbackDietaRepository;

}