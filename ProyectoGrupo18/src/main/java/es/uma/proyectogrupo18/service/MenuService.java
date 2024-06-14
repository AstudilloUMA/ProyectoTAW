package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

}