package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.AdministradorRepository;
import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.entity.AdministradorEntity;
import es.uma.proyectogrupo18.entity.ClienteEntity;
import es.uma.proyectogrupo18.entity.TrabajadorEntity;
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

    @Autowired
    protected TrabajadorRepository trabajadorRepository;

    @GetMapping("/")
    public String doLogin(Model model)
    {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutetica(@ModelAttribute("usuario") Usuario usuario, HttpSession httpSession) {

        UsuarioEntity user = this.usuarioRepository.autentica(usuario.getUser(), usuario.getPwd());

        if (user != null)
        {
            httpSession.setAttribute("usuario", user);

            AdministradorEntity admin = this.administradorRepository.findById(user.getId()).orElse(null);

            if (admin != null) {
                httpSession.setAttribute("tipo", "admin");
                return ("redirect:/admin/");
            }

            TrabajadorEntity worker = null;
            if (admin == null)
                worker = this.trabajadorRepository.findById(user.getId()).orElse(null);

            if (worker != null) {
                String tipo = "";
                switch (worker.getRol().getRol()) {
                    case "Dietista":
                        tipo = "dietista";
                        break;

                    case "Entrenador Bodybuilding":
                        tipo = "bodybuilder";
                        break;

                    case "Entrenador Cross-training":
                        tipo = "crosstrainer";
                        break;
                }

                httpSession.setAttribute("tipo", tipo);
                return ("redirect:/" + tipo + "/");
            }

            ClienteEntity customer = null;
            if (admin == null)
                customer = this.clienteRepository.findById(user.getId()).orElse(null);

            if (customer != null) {
                httpSession.setAttribute("tipo", "customer");
                return ("redirect:/customer/");
            }
        }
        else {
            httpSession.setAttribute("error", "El usuario o la contraseña son incorrectos");
            return "redirect:/login/";
        }

        return null;
    }
}
