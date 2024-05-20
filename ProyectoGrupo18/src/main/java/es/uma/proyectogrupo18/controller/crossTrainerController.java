package es.uma.proyectogrupo18.controller;


import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.RutinaSemanalRepository;
import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.entity.RutinaSemanalEntity;
import es.uma.proyectogrupo18.ui.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/crosstrainer")
public class crossTrainerController {

    @Autowired
    protected TrabajadorRepository trabajadorRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected RutinaSemanalRepository rutinaSemanalRepository;

    @Autowired
    protected ClienteRepository clienteRepository;


    @GetMapping("/")
    public String doCrosstrainerHome(HttpSession httpSession) {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";
        else
            return "crossTrainerHome";
    }

    @GetMapping("/rutinas")
    public String doListarRutinas(HttpSession httpSession, Model model) {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";
        else
        {
            List<RutinaSemanalEntity> rutinas = this.rutinaSemanalRepository.findAll();
            model.addAttribute("rutinas",rutinas);
            return "rutinasCrosstrainer";
        }
    }

    @PostMapping("/asignar")
    public String doAsignarRutina(@RequestParam("rutina") Integer rutina, HttpSession httpSession, Model model) {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";
        else
        {
            model.addAttribute("rutina", this.rutinaSemanalRepository.findById(rutina).orElse(null));
            model.addAttribute("clientes", this.clienteRepository.findAll());
            return "asignarRutina";
        }
    }
}
