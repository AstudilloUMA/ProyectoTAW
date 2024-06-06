package es.uma.proyectogrupo18.controller;


import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.entity.*;
import es.uma.proyectogrupo18.ui.Quicksort;
import es.uma.proyectogrupo18.ui.RutinaUi;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

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
    public String doCrosstrainerHome() {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";
        else
            return "crossTrainerHome";
    }

    @GetMapping("/rutinas")
    public String doListarRutinas(Model model) {
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

    @GetMapping("/asignar")
    public String doAsignarRutina(@RequestParam("id") Integer id, Model model) {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        List<RutinaSemanalEntity> rutinas = this.rutinaSemanalRepository.findRutinasByTrabajadorId(((UsuarioEntity) httpSession.getAttribute("usuario")).getId());

        model.addAttribute("cliente",cliente);
        model.addAttribute("rutinas",rutinas);

        return "asignarRutina";
    }

    @PostMapping("/asignada")
    public String doAsignada(@RequestParam("id") Integer id, @RequestParam("rutinaId") Integer rutinaId)
    {
        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(rutinaId).orElse(null);
        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);

        cliente.setRutinaSemanalByRutinaId(rutina);
        Collection<ClienteEntity> clientes = rutina.getClientesById();
        clientes.add(cliente);
        rutina.setClientesById(clientes);

        this.rutinaSemanalRepository.saveAndFlush(rutina);
        this.clienteRepository.saveAndFlush(cliente);

        return "redirect:/crosstrainer/clientes";
    }

    @GetMapping("/clientes")
    public String doListarClientes(Model model) {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";
        else
        {
            UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
            TrabajadorEntity trabajador = this.trabajadorRepository.findById(user.getId()).orElse(null);

            List<ClienteEntity> clientes = this.clienteRepository.findClientesByEntrenador(trabajador);
            model.addAttribute("clientes",clientes);

            return "clientesCrosstrainer";
        }
    }

    @GetMapping("/desasignar")
    public String doDesAsignar(@RequestParam("id") Integer id)
    {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        cliente.setRutinaSemanalByRutinaId(null);

        for (FeedbackEntity f : cliente.getFeedbacksByUsuarioId())
        {
            this.feedbackRepository.delete(f);
        }

        cliente.setFeedbacksByUsuarioId(null);

        this.clienteRepository.saveAndFlush(cliente);
        this.feedbackRepository.flush();

        return "redirect:/crosstrainer/clientes";
    }

    @GetMapping("/seguimiento")
    public String doSeguimiento(@RequestParam("id") Integer id, Model model)
    {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        model.addAttribute("cliente",cliente);

        return "seguimiento";
    }

    @GetMapping("/eliminar")
    public String doEliminar(@RequestParam("id") Integer id)
    {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";

        UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
        TrabajadorEntity entrenador = this.trabajadorRepository.findById(user.getId()).orElse(null);

        Collection<RutinaSemanalEntity> rutinas = entrenador.getRutinaSemanalsByUsuarioId();

        rutinas.remove(this.rutinaSemanalRepository.findById(id).orElse(null));

        entrenador.setRutinaSemanalsByUsuarioId(rutinas);
        this.trabajadorRepository.saveAndFlush(entrenador);

        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(id).orElse(null);
        Collection<ClienteEntity> clientes = rutina.getClientesById();

        for(ClienteEntity c : clientes)
        {
            c.setRutinaSemanalByRutinaId(null);
            this.clienteRepository.saveAndFlush(c);
        }
        
        this.rutinaSemanalRepository.delete(rutina);

        return "redirect:/crosstrainer/rutinas";
    }

    @PostMapping("/borrar")
    public String doEliminar(@RequestParam("idRutina")int idRutina, @RequestParam("idEjercicio")int idEjercicio)
    {
        List<SesionDeEjercicioEntity> se = this.sesionDeEjercicioRepository.findSesionesByEjercicioId(idEjercicio);
        this.sesionDeEjercicioRepository.deleteAll(se);
        this.sesionDeEjercicioRepository.flush();

        return "redirect:/crosstrainer/mostrar?id="+idRutina;
    }

    @GetMapping("/mostrar")
    public String doMostrar(@RequestParam("id") int id, Model model)
    {
        if(httpSession.getAttribute("tipo") != "crosstrainer")
            return "sinPermiso";

        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(id).orElse(null);
        model.addAttribute("rutina",rutina);

        List<SesionDeEjercicioEntity> ses = new ArrayList<>();

        for(RutinaSemanalEntrenamientoEntity r : rutina.getRutinaSemanalEntrenamientosById()) {
            SesionDeEntrenamientoEntity s = r.getSesionDeEntrenamientoBySesionDeEntrenamientoId();
            Collection<EntrenamientoEjercicioEntity> e = s.getEntrenamientoEjerciciosById();
            for (EntrenamientoEjercicioEntity ee : e) {
                ses.add(ee.getSesionDeEjercicioBySesionDeEjercicioId());
            }
        }

        Quicksort.quickSort(ses);

        model.addAttribute("sesiones",ses);
        model.addAttribute("rutinaUi",new RutinaUi());

        return "mostrarRutina";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("rutinaUi") RutinaUi rutina)
    {
        SesionDeEjercicioEntity se = this.sesionDeEjercicioRepository.findById(rutina.getSesionId()).orElse(null);
        EjercicioEntity ej = this.ejercicioRepository.findById(rutina.getEjercicioId()).orElse(null);

        se.setOrden(rutina.getOrden());
        ej.setNombre(rutina.getNombre());
        ej.setTipo(rutina.getTipo());
        se.setRepeticiones(rutina.getRepeticiones());
        se.setCantidad(rutina.getCantidad());
        ej.setVideo(rutina.getVideo());

        this.sesionDeEjercicioRepository.saveAndFlush(se);
        this.ejercicioRepository.saveAndFlush(ej);

        return "redirect:/crosstrainer/mostrar?id="+rutina.getRutinaId();
    }


}
