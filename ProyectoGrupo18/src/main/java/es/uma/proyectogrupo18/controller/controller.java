package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.entity.UsuarioEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class controller {

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String doInicio() {return "inicio";}

    @GetMapping("/logout")
    public String doLogOut (HttpSession httpSession)
    {
        httpSession.removeAttribute("usuario");
        httpSession.removeAttribute("tipo");
        httpSession.removeAttribute("error");
        return "redirect:/";
    }

    @GetMapping("/infoUsuario")
    public String doInfoUser (HttpSession httpSession, @RequestParam("id") Integer id, Model model) {
        UsuarioEntity user = this.usuarioRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "infoUsuario";
    }
}
