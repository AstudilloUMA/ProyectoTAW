package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.dto.Usuario;
import es.uma.proyectogrupo18.entity.UsuarioEntity;
import es.uma.proyectogrupo18.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class controller {

    @Autowired
    protected UsuarioService usuarioService;

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
    public String doInfoUser (HttpSession httpSession, @RequestParam("id") Integer id,@RequestParam("tipo") String tipo, Model model) {
        Usuario user = this.usuarioService.getUsuarioById(id);
        model.addAttribute("user", user);
        model.addAttribute("tipo", tipo);
        return "infoUsuario";
    }
}
