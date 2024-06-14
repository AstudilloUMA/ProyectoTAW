package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.RolTrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolTrabajadorService {

    @Autowired
    private RolTrabajadorRepository rolTrabajadorRepository;

}
