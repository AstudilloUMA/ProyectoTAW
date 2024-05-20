package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.RutinaSemanalRepository;
import es.uma.proyectogrupo18.entity.RutinaSemanalEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class customerController {

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected RutinaSemanalRepository rutinaSemanalRepository;

    @GetMapping("/")
    public String doCustomerHome(HttpSession httpSession) {
        if (!"customer".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        } else {
            return "costumerHome";
        }
    }

    @GetMapping("/rutinas")
    public String doCustomerRutinas(@RequestParam("id") Integer usuarioId, HttpSession httpSession, Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }
        else {
            //model.addAttribute("rutinas", rutinaSemanalRepository.findByClienteId(usuarioId));
            return "customerRutinas";
        }
    }
}