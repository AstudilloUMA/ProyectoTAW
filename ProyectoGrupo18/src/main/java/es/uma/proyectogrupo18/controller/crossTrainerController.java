/*
AUTOR --> Pablo Astudillo Fraga
 */

package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.entity.*;
import es.uma.proyectogrupo18.ui.FiltroRutina;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

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
    protected TipoEjercicioRepository tipoEjercicioRepository;

    @Autowired
    private HttpSession httpSession;

    private List<SesionDeEjercicioEntity> personalizadas;

    @GetMapping("/")
    public String doCrosstrainerHome(HttpSession httpSession) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        personalizadas = new ArrayList<>();
        return "TrainerHome";
    }

    @GetMapping("/rutinas")
    public String doListarRutinas(Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";
        else {
            UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
            List<RutinaSemanalEntity> rutinas = this.rutinaSemanalRepository.findRutinasByTrabajadorId(user.getId());

            FiltroRutina filtroRutina = new FiltroRutina();

            model.addAttribute("filtroRutina", filtroRutina);
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

    @PostMapping("/personalizar")
    public String doAsignada(@RequestParam("id") Integer id, @RequestParam("rutinaId") Integer rutinaId, Model model) {
        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(rutinaId).orElse(null);
        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);

        model.addAttribute("rutina", rutina);
        model.addAttribute("cliente", cliente);

        List<SesionDeEjercicioEntity> sesiones = this.sesionDeEjercicioRepository.findSesionesByRutina(rutina);
        model.addAttribute("sesiones", sesiones);

        this.personalizadas = new ArrayList<>();

        return "personalizarRutina";
    }

    @PostMapping("/personalizada")
    public String doPersonalizada(
            @RequestParam("sesionId") int sesionId,
            @RequestParam("clienteId") int clienteId,
            @RequestParam("repeticiones") String repeticiones,
            @RequestParam("cantidad") String cantidad,
            @RequestParam("peso") String peso,
            Model model) {

        SesionDeEjercicioEntity sesion = this.sesionDeEjercicioRepository.findById(sesionId).orElse(null);
        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(sesion.getRutina().getId()).orElse(null);
        ClienteEntity cliente = this.clienteRepository.findById(clienteId).orElse(null);

        SesionDeEjercicioEntity sesionCliente = new SesionDeEjercicioEntity();
        sesionCliente.setFecha(sesion.getFecha());
        sesionCliente.setDia(sesion.getDia());
        sesionCliente.setRepeticiones(repeticiones);
        sesionCliente.setCantidad(cantidad);
        sesionCliente.setPeso(peso);
        sesionCliente.setOrden(sesion.getOrden());
        sesionCliente.setEjercicio(sesion.getEjercicio());
        sesionCliente.setTrabajador(sesion.getTrabajador());
        sesionCliente.setCliente(cliente);
        sesionCliente.setRutina(rutina);

        cliente.setRutina(rutina);

        this.sesionDeEjercicioRepository.saveAndFlush(sesionCliente);
        this.clienteRepository.saveAndFlush(cliente);

        model.addAttribute("rutina", rutina);
        model.addAttribute("cliente", cliente);

        this.personalizadas.add(sesion);

        List<SesionDeEjercicioEntity> sesiones = this.sesionDeEjercicioRepository.findSesionesByRutinaSinPersonalizar(rutina, this.personalizadas);

        model.addAttribute("sesiones", sesiones);

        return "personalizarRutina";
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
        List<SesionDeEjercicioEntity> sesiones = this.sesionDeEjercicioRepository.findSesionesByCliente(cliente);

        this.sesionDeEjercicioRepository.deleteAll(sesiones);
        this.sesionDeEjercicioRepository.flush();

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
    public String doSeguimiento(@RequestParam("id") int id, Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);

        List<FeedbackEntity> feedbacks = this.feedbackRepository.findByCliente(cliente);
        model.addAttribute("feedbacks", feedbacks);

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
    public String doEliminar(@RequestParam("idRutina") int idRutina, @RequestParam("idSesion") int idSesion) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        SesionDeEjercicioEntity sesion = this.sesionDeEjercicioRepository.findById(idSesion).orElse(null);
        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(idRutina).orElse(null);

        List<SesionDeEjercicioEntity> sesiones = rutina.getSesionDeEjercicios();
        sesiones.remove(sesion);
        rutina.setSesionDeEjercicios(sesiones);

        this.sesionDeEjercicioRepository.delete(sesion);
        this.sesionDeEjercicioRepository.flush();

        this.rutinaSemanalRepository.saveAndFlush(rutina);

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
            @RequestParam("peso") String peso,
            @RequestParam("dia") String dia,
            @RequestParam("rutinaId") int rutinaId,
            Model model) {

        SesionDeEjercicioEntity se = this.sesionDeEjercicioRepository.findById(sesionId).orElse(null);
        EjercicioEntity ej = this.ejercicioRepository.findById(ejercicioId).orElse(null);

        se.setOrden(orden);

        se.setRepeticiones(repeticiones);
        se.setCantidad(cantidad);
        se.setPeso(peso);
        se.setDia(dia);

        this.sesionDeEjercicioRepository.saveAndFlush(se);

        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(rutinaId).orElse(null);
        model.addAttribute("rutina", rutina);


        List<SesionDeEjercicioEntity> sesiones = this.sesionDeEjercicioRepository.findSesionesByRutina(rutina);

        model.addAttribute("sesiones", sesiones);

        return "mostrarRutina";
    }


    @GetMapping("/tipo")
    public String mostrarNueva(@RequestParam("id") int id, Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(id).orElse(null);
        model.addAttribute("rutina", rutina);

        model.addAttribute("tipos", this.tipoEjercicioRepository.findAll());

        return "elegirTipo";
    }

    @PostMapping("/elegido")
    public String doElegido(@RequestParam("id") int id, @RequestParam("tipo") int tipo, Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(id).orElse(null);
        model.addAttribute("rutina", rutina);

        TipoEjercicioEntity tipoEjercicio = this.tipoEjercicioRepository.findById(tipo).orElse(null);

        model.addAttribute("ejercicios", this.ejercicioRepository.findByTipo(tipoEjercicio));

        return "nuevaSesion";
    }

    @PostMapping("/crear")
    public String doCrear(
            @RequestParam("rutinaId") int rutinaId,
            @RequestParam("orden") int orden,
            @RequestParam("dia") String dia,
            @RequestParam("ejercicioId") int ejercicioId,
            @RequestParam("repeticiones") String repeticiones,
            @RequestParam("cantidad") String cantidad) {

        RutinaSemanalEntity rutina = this.rutinaSemanalRepository.findById(rutinaId).orElse(null);
        EjercicioEntity ejercicio = this.ejercicioRepository.findById(ejercicioId).orElse(null);

        UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
        TrabajadorEntity trabajador = this.trabajadorRepository.findById(user.getId()).orElse(null);

        SesionDeEjercicioEntity sesion = new SesionDeEjercicioEntity();
        sesion.setOrden(orden);
        sesion.setDia(dia);
        sesion.setEjercicio(ejercicio);
        sesion.setRepeticiones(repeticiones);
        sesion.setCantidad(cantidad);
        sesion.setRutina(rutina);
        sesion.setCliente(null);
        sesion.setTrabajador(trabajador);
        sesion.setFecha(LocalDate.now());

        List<SesionDeEjercicioEntity> sesiones = rutina.getSesionDeEjercicios();
        sesiones.add(sesion);
        rutina.setSesionDeEjercicios(sesiones);

        this.sesionDeEjercicioRepository.saveAndFlush(sesion);
        this.rutinaSemanalRepository.saveAndFlush(rutina);


        return "redirect:/crosstrainer/mostrar?id=" + rutinaId;
    }

    @GetMapping("/nueva")
    public String doNueva(Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        return "formNuevaRutina";
    }

    @PostMapping("/creada")
    public String doCreada(@RequestParam("nombre") String nombre,
                           @RequestParam("inicio")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
                           @RequestParam("fin")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
        TrabajadorEntity trabajador = this.trabajadorRepository.findById(user.getId()).orElse(null);

        RutinaSemanalEntity rutina = new RutinaSemanalEntity();
        rutina.setNombre(nombre);

        java.sql.Date sqlFechaInicio = new java.sql.Date(fechaInicio.getTime());
        java.sql.Date sqlFechaFin = new java.sql.Date(fechaFin.getTime());

        rutina.setFechaInicio(sqlFechaInicio.toLocalDate());
        rutina.setFechaFin(sqlFechaFin.toLocalDate());

        rutina.setTrabajador(trabajador);

        this.rutinaSemanalRepository.saveAndFlush(rutina);

        return "redirect:/crosstrainer/mostrar?id=" + rutina.getId();
    }

    @GetMapping("/ver")
    public String doVerRutina(@RequestParam("id") Integer id, Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        RutinaSemanalEntity rutina = cliente.getRutina();
        List<SesionDeEjercicioEntity> sesiones = this.sesionDeEjercicioRepository.findSesionesByCliente(cliente);

        model.addAttribute("rutina", rutina);
        model.addAttribute("sesiones", sesiones);
        model.addAttribute("cliente", cliente);

        return "mostrarRutinaDeUnCliente";
    }

    @PostMapping("/actualizar")
    public String doActualizar(@RequestParam("id") int id, @RequestParam("repeticiones") String repeticiones,
                               @RequestParam("cantidad") String cantidad,
                               @RequestParam("peso") String peso, Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        SesionDeEjercicioEntity sesion = this.sesionDeEjercicioRepository.findById(id).orElse(null);

        ClienteEntity cliente = sesion.getCliente();
        RutinaSemanalEntity rutina = cliente.getRutina();
        List<SesionDeEjercicioEntity> sesiones = this.sesionDeEjercicioRepository.findSesionesByCliente(cliente);

        sesion.setRepeticiones(repeticiones);
        sesion.setCantidad(cantidad);
        sesion.setPeso(peso);

        model.addAttribute("rutina", rutina);
        model.addAttribute("sesiones", sesiones);
        model.addAttribute("cliente", cliente);

        this.sesionDeEjercicioRepository.saveAndFlush(sesion);

        return "mostrarRutinaDeUnCliente";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtroRutina")FiltroRutina filtroRutina, Model model) {
        if (!"crosstrainer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = (ClienteEntity) httpSession.getAttribute("usuario");
        TrabajadorEntity trabajador = this.trabajadorRepository.findById(cliente.getId()).orElse(null);

        String nombre = (filtroRutina.getNombre().equals("") ? null : filtroRutina.getNombre());
        LocalDate fechaInicio = (filtroRutina.getFechaInicio() == null ? null : filtroRutina.getFechaInicio().toLocalDate());
        LocalDate fechaFin = (filtroRutina.getFechaFin() == null ? null : filtroRutina.getFechaFin().toLocalDate());

        List<RutinaSemanalEntity> rutinas = this.rutinaSemanalRepository.findRutinasFiltradas(trabajador, nombre, fechaInicio, fechaFin);

        model.addAttribute("filtroRutina", filtroRutina);
        model.addAttribute("rutinas", rutinas);

        return "rutinasTrainer";
    }
}

