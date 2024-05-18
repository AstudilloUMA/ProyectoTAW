package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")

public class customerController {

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;


    @GetMapping("/")
    public String doCustomerHome(HttpSession httpSession) {
        if(httpSession.getAttribute("tipo") != "customer")
            return "sinPermiso";
        else
            return "customerHome";
    }
}
