package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;


@Controller
public class controlador {

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected ComidaRepository comidaRepository;


    @GetMapping("/")
    public String prueba (Model model) {
        ComidaEntity comida = new ComidaEntity();
        comida.setNombre("comida");
        comida.setOrden(1);
        comida.setKilocaloriasTotales(1);
        comidaRepository.save(comida);

        List<ComidaEntity> comidas = this.comidaRepository.findAll();
        model.addAttribute("comidas",comidas);
        return "prueba";
    }
}
