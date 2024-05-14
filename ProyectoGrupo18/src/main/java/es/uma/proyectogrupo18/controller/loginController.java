package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.AdministradorRepository;
import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.entity.AdministradorEntity;
import es.uma.proyectogrupo18.entity.ClienteEntity;
import es.uma.proyectogrupo18.entity.UsuarioEntity;
import es.uma.proyectogrupo18.ui.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    protected AdministradorRepository administradorRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String doLogin(Model model)
    {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutetica(@ModelAttribute("usuario") Usuario usuario, Model model, HttpSession httpSession)
    {
        UsuarioEntity user;
        AdministradorEntity admin = this.administradorRepository.autentica(usuario.getUser(), usuario.getPwd());

        if(admin != null)
        {
            user = this.usuarioRepository.findById(admin.getUsuarioId()).orElse(null);
            httpSession.setAttribute("usuario", user);
            httpSession.setAttribute("tipo","admin");
            return ("redirect:/admin/");
        }
        ClienteEntity client = this.clienteRepository.autentica(usuario.getUser(), usuario.getPwd());
        if(client != null)
        {
            user = this.usuarioRepository.findById(client.getUsuarioId()).orElse(null);
            httpSession.setAttribute("usuario", user);
            httpSession.setAttribute("tipo","customer");
            return ("redirect:/customer/");
        }

        httpSession.setAttribute("error","El usuario o la contraseña son incorrectos");
        return "redirect:/login/";
    }
}
