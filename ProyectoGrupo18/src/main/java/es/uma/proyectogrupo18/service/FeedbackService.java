package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;


}
