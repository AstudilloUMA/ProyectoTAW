package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bodybuilder")
public class bodyBuilderController {
    @Autowired
    protected TrabajadorRepository trabajadorRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;


    @GetMapping("/")
    public String doBodyBuilderHome(HttpSession httpSession) {
        if(httpSession.getAttribute("tipo") != "bodybuilder")
            return "sinPermiso";
        else
            return "bodyBuilderHome";
    }
}
