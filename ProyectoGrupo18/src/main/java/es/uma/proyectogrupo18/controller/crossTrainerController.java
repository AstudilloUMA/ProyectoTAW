package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.entity.*;
import es.uma.proyectogrupo18.ui.RutinaUi;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @Autowired
    protected FeedbackRepository feedbackRepository;

    @Autowired
    protected EjercicioRepository ejercicioRepository;

    @Autowired
    protected SesionDeEjercicioRepository sesionDeEjercicioRepository;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/")
    public String doCrosstrainerHome(HttpSession httpSession) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";
        else
            return "TrainerHome";
    }

    @GetMapping("/rutinas")
    public String doListarRutinas(Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";
        else {
            UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
            List<RutinaSemanalEntity> rutinas = this.rutinaSemanalRepository.findRutinasByTrabajadorId(user.getId());
            model.addAttribute("rutinas", rutinas);
            return "rutinasTrainer";
        }
    }

    @GetMapping("/asignar")
    public String doAsignarRutina(@RequestParam("id") Integer id, Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        List<RutinaSemanalEntity> rutinas = this.rutinaSemanalRepository.findRutinasByTrabajadorId(((UsuarioEntity) httpSession.getAttribute("usuario")).getId());

        model.addAttribute("cliente", cliente);
        model.addAttribute("rutinas", rutinas);

        return "asignarRutina";
    }

    @PostMapping("/asignada")
    public String doAsignada(@RequestParam("id") Integer id, @RequestParam("rutinaId") Integer rutinaId) {
        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(rutinaId).orElse(null);
        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);

        cliente.setRutina(rutina);

        this.clienteRepository.saveAndFlush(cliente);

        return "redirect:/crosstrainer/clientes";
    }

    @GetMapping("/clientes")
    public String doListarClientes(Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";
        else {
            UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
            TrabajadorEntity trabajador = this.trabajadorRepository.findById(user.getId()).orElse(null);

            List<ClienteEntity> clientes = this.clienteRepository.findClientesByEntrenador(trabajador);
            model.addAttribute("clientes", clientes);

            return "clientesTrainer";
        }
    }

    @GetMapping("/desasignar")
    public String doDesAsignar(@RequestParam("id") Integer id) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        cliente.setRutina(null);

        for (FeedbackEntity f : cliente.getFeedbacks()) {
            this.feedbackRepository.delete(f);
        }

        cliente.setFeedbacks(null);

        this.clienteRepository.saveAndFlush(cliente);
        this.feedbackRepository.flush();

        return "redirect:/crosstrainer/clientes";
    }

    @GetMapping("/seguimiento")
    public String doSeguimiento(@RequestParam("id") Integer id, Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);

        return "seguimiento";
    }

    @GetMapping("/eliminar")
    public String doEliminar(@RequestParam("id") Integer id) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
        TrabajadorEntity entrenador = this.trabajadorRepository.findById(user.getId()).orElse(null);

        Set<RutinaSemanalEntity> rutinas = entrenador.getRutinaSemanals();

        rutinas.remove(this.rutinaSemanalRepository.findById(id).orElse(null));

        entrenador.setRutinaSemanals(rutinas);
        this.trabajadorRepository.saveAndFlush(entrenador);

        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(id).orElse(null);
        Collection<ClienteEntity> clientes = rutina.getClientes();

        for (ClienteEntity c : clientes) {
            c.setRutina(null);
            this.clienteRepository.saveAndFlush(c);
        }

        this.rutinaSemanalRepository.delete(rutina);

        return "redirect:/crosstrainer/rutinas";
    }

    @PostMapping("/borrar")
    public String doEliminar(@RequestParam("idRutina") int idRutina, @RequestParam("idEjercicio") int idEjercicio) {
        List<SesionDeEjercicioEntity> se = this.sesionDeEjercicioRepository.findSesionesByEjercicioId(idEjercicio);
        this.sesionDeEjercicioRepository.deleteAll(se);
        this.sesionDeEjercicioRepository.flush();

        return "redirect:/crosstrainer/mostrar?id=" + idRutina;
    }

    @GetMapping("/mostrar")
    public String doMostrar(@RequestParam("id") int id, Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";


        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(id).orElse(null);
        model.addAttribute("rutina", rutina);


        List<SesionDeEjercicioEntity> sesiones = this.sesionDeEjercicioRepository.findSesionesByRutina(rutina);

        model.addAttribute("sesiones", sesiones);
        model.addAttribute("rutinaUi", new RutinaUi());
        model.addAttribute("ejercicios", this.ejercicioRepository.findAllOrdered());

        return "mostrarRutina";
    }

    @PostMapping("/guardar")
    public String doGuardar(
            @RequestParam("sesionId") int sesionId,
            @RequestParam("ejercicioId") int ejercicioId,
            @RequestParam("orden") int orden,
            @RequestParam("repeticiones") String repeticiones,
            @RequestParam("cantidad") String cantidad,
            @RequestParam("dia") String dia,
            @RequestParam("video") String video,
            @RequestParam("rutinaId") int rutinaId,
            Model model) {

        SesionDeEjercicioEntity se = this.sesionDeEjercicioRepository.findById(sesionId).orElse(null);
        EjercicioEntity ej = this.ejercicioRepository.findById(ejercicioId).orElse(null);

        se.setOrden(orden);

        se.setRepeticiones(repeticiones);
        se.setCantidad(cantidad);
        se.setDia(dia);
        ej.setVideo(video);

        this.sesionDeEjercicioRepository.saveAndFlush(se);
        this.ejercicioRepository.saveAndFlush(ej);

        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(rutinaId).orElse(null);
        model.addAttribute("rutina", rutina);


        List<SesionDeEjercicioEntity> sesiones = this.sesionDeEjercicioRepository.findSesionesByRutina(rutina);

        model.addAttribute("sesiones", sesiones);

        return "mostrarRutina";
    }
}
