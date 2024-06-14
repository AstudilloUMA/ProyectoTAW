package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.ComidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;

}
