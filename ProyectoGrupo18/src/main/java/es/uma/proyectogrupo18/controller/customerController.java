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
import org.springframework.web.bind.annotation.PostMapping;
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

   /* @PostMapping("/guardarProgreso")
    public String guardarProgreso(
            @RequestParam("sesionId") Integer id,
            @RequestParam("series") Integer series,
            @RequestParam("repeticiones") Integer repeticiones,
            @RequestParam("calificacion") Integer calificacion,
            @RequestParam("comentario") String comentario,
            Model model) {

        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = (ClienteEntity) httpSession.getAttribute("cliente");

        if (cliente == null) {
            return "redirect:/login"; // O cualquier otra página de error o login
        }

        SesionDeEjercicioEntity sesionDeEjercicio = this.sesionDeEjercicioRepository.findById(id).orElse(null);

        if (sesionDeEjercicio != null) {
            sesionDeEjercicio.setSeriesCompletadas(series);
            sesionDeEjercicio.setRepeticionesCompletadas(repeticiones);
            sesionDeEjercicio.setCalificacion(calificacion);
            sesionDeEjercicio.setComentario(comentario);
            this.sesionDeEjercicioRepository.save(sesionDeEjercicio);
        }

        return "redirect:/customer/verProgreso?sesionId=" + id;
    }

*/
    @GetMapping("/dieta")
    public String verDieta(@RequestParam("id") Integer usuarioId, Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(usuarioId).orElse(null);

        DietaEntity dieta = cliente.getDietaCodigo();
        model.addAttribute("cliente", cliente);
        model.addAttribute("dieta", dieta);

        List<ComidaEntity> comidas = dieta.getComidas();

        Quicksort.quickSortDietas(comidas);

        model.addAttribute("comidas", comidas);

        return "verDietaCustomer";
    }

    @GetMapping("/actualizarProgresoDieta")
    public String actualizarProgresoDieta(Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        UsuarioEntity usuario = (UsuarioEntity) httpSession.getAttribute("usuario");
        ClienteEntity cliente = this.clienteRepository.findById(usuario.getId()).orElse(null);
        DietaEntity dieta = cliente.getDietaCodigo();

        model.addAttribute("cliente", cliente);
        model.addAttribute("dieta", dieta);

        return "actualizarPDieta";
    }

    @PostMapping("/guardarProgresoDieta")
    public String guardarProgresoDieta(
            @RequestParam("calificacion") Integer calificacion,
            @RequestParam("comentarios") String comentarios,
            Model model) {

        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        UsuarioEntity usuario = (UsuarioEntity) httpSession.getAttribute("usuario");
        ClienteEntity cliente = this.clienteRepository.findById(usuario.getId()).orElse(null);

        DietaEntity dieta = cliente.getDietaCodigo();


        model.addAttribute("cliente", cliente);
        model.addAttribute("dieta", dieta);

        if (dieta != null) {
            FeedbackdietaEntity feedback = new FeedbackdietaEntity();
            feedback.setDietaCodigo(dieta);
            feedback.setCalificacion(calificacion);
            feedback.setComentarios(comentarios);

            feedback.setCliente(cliente);
            this.feedbackdietaRepository.save(feedback);
        }

        return "redirect:/customer/";
    }


}