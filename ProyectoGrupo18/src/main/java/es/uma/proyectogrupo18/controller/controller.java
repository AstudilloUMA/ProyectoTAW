package es.uma.proyectogrupo18.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class controller {

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
}
