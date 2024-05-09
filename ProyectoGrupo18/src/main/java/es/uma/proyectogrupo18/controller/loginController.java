package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.AdministradorRepository;
import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.entity.AdministradorEntity;
import es.uma.proyectogrupo18.entity.ClienteEntity;
import es.uma.proyectogrupo18.ui.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    protected AdministradorRepository administradorRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @GetMapping("/")
    public String doLogin()
    {
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutetica(@ModelAttribute("usuario") Usuario usuario)
    {
        AdministradorEntity admin = this.administradorRepository.autentica(usuario.getUser(), usuario.getPwd());
        if(admin != null)
        {
            return ("redirect:/admin?id="+admin.getUsuarioId()+"/");
        }
        ClienteEntity client = this.clienteRepository.autentica(usuario.getUser(), usuario.getPwd());
        if(client != null)
        {
            return ("redirect:/client?id="+client.getUsuarioId()+"/");
        }

        return "redirect:/login/";
    }
}
