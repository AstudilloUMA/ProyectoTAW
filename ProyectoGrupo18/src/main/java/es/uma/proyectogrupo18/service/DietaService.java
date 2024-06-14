package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.DietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietaService {

    @Autowired
    private DietaRepository dietaRepository;

}
