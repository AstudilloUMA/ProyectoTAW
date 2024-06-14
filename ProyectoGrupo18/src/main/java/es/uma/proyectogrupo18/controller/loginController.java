/*
AUTOR --> Pablo Astudillo Fraga
 */

package es.uma.proyectogrupo18.controller;


import es.uma.proyectogrupo18.dto.Administrador;
import es.uma.proyectogrupo18.dto.Cliente;
import es.uma.proyectogrupo18.dto.Trabajador;
import es.uma.proyectogrupo18.dto.Usuario;
import es.uma.proyectogrupo18.service.AdministradorService;
import es.uma.proyectogrupo18.service.ClienteService;
import es.uma.proyectogrupo18.service.TrabajadorService;
import es.uma.proyectogrupo18.service.UsuarioService;
import es.uma.proyectogrupo18.ui.UsuarioUI;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    protected AdministradorService administradorService;

    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected TrabajadorService trabajadorService;

    @GetMapping("/")
    public String doLogin(Model model)
    {
        model.addAttribute("usuarioUI", new UsuarioUI());
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutetica(@ModelAttribute("usuario") UsuarioUI usuarioUI, HttpSession httpSession) {

        Usuario user = this.usuarioService.autentica(usuarioUI.getUser(), usuarioUI.getPwd());

        if (user != null)
        {
            httpSession.setAttribute("usuario", user);

            Administrador admin = this.administradorService.getAdministradorById(user.getId());

            if (admin != null) {
                httpSession.setAttribute("tipo", "admin");
                return ("redirect:/admin/");
            }

            Trabajador worker = null;
            if (admin == null)
                worker = this.trabajadorService.getTrabajadorById(user.getId());

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

            Cliente customer = null;
            if (admin == null)
                customer = this.clienteService.getClienteById(user.getId());

            if (customer != null) {
                httpSession.setAttribute("tipo", "customer");
                return ("redirect:/customer/");
            }
        }
        else {
            httpSession.setAttribute("error", "El usuarioUI o la contraseña son incorrectos");
            return "redirect:/login/";
        }

        return null;
    }
}
