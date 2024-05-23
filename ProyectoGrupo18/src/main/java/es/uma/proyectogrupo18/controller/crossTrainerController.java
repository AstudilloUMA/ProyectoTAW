package es.uma.proyectogrupo18.controller;


import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.RutinaSemanalRepository;
import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.entity.ClienteEntity;
import es.uma.proyectogrupo18.entity.RutinaSemanalEntity;
import es.uma.proyectogrupo18.entity.UsuarioEntity;
import es.uma.proyectogrupo18.ui.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/crosstrainer")
public class crossTrainerController {

    @Autowired
    protected TrabajadorRepository trabajadorRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected RutinaSemanalRepository rutinaSemanalRepository;

    @Autowired
    protected ClienteRepository clienteRepository;


    @GetMapping("/")
    public String doCrosstrainerHome(HttpSession httpSession) {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";
        else
            return "crossTrainerHome";
    }

    @GetMapping("/rutinas")
    public String doListarRutinas(HttpSession httpSession, Model model) {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";
        else
        {
            UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
            List<RutinaSemanalEntity> rutinas = this.rutinaSemanalRepository.findRutinasByTrabajadorId(user.getId());
            model.addAttribute("rutinas",rutinas);
            return "rutinasCrosstrainer";
        }
    }

    @PostMapping("/asignar")
    public String doAsignarRutina(@RequestParam("rutina") Integer idRutina, HttpSession httpSession, Model model) {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";

        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(idRutina).orElse(null);
        model.addAttribute("rutina", rutina);

        List<ClienteEntity> clientes = this.clienteRepository.findUsuariosSinRutina((List<ClienteEntity>) rutina.getClientesById());
        model.addAttribute("clientes", clientes);

        return "asignarRutina";
    }

    @PostMapping("/asignada")
    public String doAsignada(@RequestParam("idRutina") Integer idRutina, @RequestParam("id") Integer id)
    {
        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(idRutina).orElse(null);
        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);

        cliente.setRutinaSemanalByRutinaId(rutina);
        Collection<ClienteEntity> clientes = rutina.getClientesById();
        clientes.add(cliente);
        rutina.setClientesById(clientes);

        this.rutinaSemanalRepository.saveAndFlush(rutina);
        this.clienteRepository.saveAndFlush(cliente);

        return "redirect:/crosstrainer/rutinas";
    }
}
