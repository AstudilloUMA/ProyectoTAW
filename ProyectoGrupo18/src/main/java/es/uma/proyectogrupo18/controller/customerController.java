package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.entity.*;
import es.uma.proyectogrupo18.ui.Quicksort;
import es.uma.proyectogrupo18.ui.SesionEjercicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/customer")
public class customerController {

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected RutinaSemanalRepository rutinaSemanalRepository;

    @Autowired
    protected SesionDeEjercicioRepository sesionDeEjercicioRepository;

    @Autowired
    protected DietaRepository dietaRepository;

    @Autowired
    protected FeedbackRepository feedbackRepository;

    @Autowired
    protected FeedbackdietaRepository feedbackdietaRepository;

    @Autowired
    private HttpSession httpSession;


    @GetMapping("/")
    public String doCustomerHome() {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        return "costumerHome";
    }

    @GetMapping("/rutina")
    public String doCustomerRutinas(@RequestParam("id") Integer usuarioId, Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(usuarioId).orElse(null);
        RutinaSemanalEntity rutina = cliente.getRutina();

        List<SesionEjercicio> ses = new ArrayList<>();

        for (SesionDeEjercicioEntity s : rutina.getSesionDeEjercicios()) {
            ses.add(new SesionEjercicio(s, s.getDia()));
        }

        //Quicksort.quickSort(ses);

        model.addAttribute("rutina", rutina);
        model.addAttribute("sesiones", ses);

        return "mostrarRutinaCliente";
    }
    @GetMapping("/actualizarProgreso")
    public String actualizarProgreso(@RequestParam("sesionId") Integer id, Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        SesionDeEjercicioEntity sesionDeEjercicio = this.sesionDeEjercicioRepository.findById(id).orElse(null);

        EjercicioEntity ejercicio = sesionDeEjercicio.getEjercicio();

        model.addAttribute("sesionDeEjercicio", sesionDeEjercicio);
        model.addAttribute("ejercicio", ejercicio);

        return "actualizarProgreso";
    }



    @GetMapping("/dieta")
    public String verDieta(@RequestParam("id") Integer usuarioId, Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(usuarioId).orElse(null);

        DietaEntity dieta = cliente.getDietaCodigo();
        model.addAttribute("dieta", dieta);

        List<ComidaEntity> comidas = dieta.getComidas();

        Quicksort.quickSortDietas(comidas);

        model.addAttribute("comidas", comidas);

        return "verDietaCustomer";
    }

    @GetMapping("/actualizarProgresoDieta")
    public String actualizarProgresoDieta(@RequestParam("id") Integer id, Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        DietaEntity dieta = this.dietaRepository.findById(id).orElse(null);

        model.addAttribute("dieta", dieta);

        return "actualizarProgresoDieta";
    }
}