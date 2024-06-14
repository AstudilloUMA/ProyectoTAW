/*
Autores:
Pablo Astudillo Fraga: 50%
Álvaro Morales Perujo:50%
 */

package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dto.*;
import es.uma.proyectogrupo18.service.*;
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
@RequestMapping("/bodybuilder")
public class bodyBuilderController {
    @Autowired
    protected TrabajadorService trabajadorService;

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected RutinaSemanalService rutinaSemanalService;

    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected FeedbackService feedbackService;

    @Autowired
    protected EjercicioService ejercicioService;

    @Autowired
    protected SesionDeEjercicioService sesionDeEjercicioService;

    @Autowired
    protected TipoEjercicioService tipoEjercicioService;

    @Autowired
    private HttpSession httpSession;

    private List<Integer> personalizadas;

    @GetMapping("/")
    public String doBodyBuilderHome(HttpSession httpSession) {
        if(!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";
        else
            personalizadas = new ArrayList<>();
            return "TrainerHome";
    }

    @GetMapping("/rutinas")
    public String doListarRutinas(Model model) {
        if(!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";
        else {
            Usuario user = (Usuario) httpSession.getAttribute("usuario");
            List<RutinaSemanal> rutinas = this.rutinaSemanalService.getRutinaSemanalByTrainerId(user.getId());

            FiltroRutina filtroRutina = new FiltroRutina();

            model.addAttribute("noResults", false);
            model.addAttribute("filtroRutina", filtroRutina);
            model.addAttribute("rutinas", rutinas);
            return "rutinasTrainer";
        }
    }

    @GetMapping("/asignar")
    public String doAsignarRutina(@RequestParam("id") Integer id, Model model) {
        if (!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        Usuario usuario = this.usuarioService.getUsuarioById(id);
        Cliente cliente = usuario.getCliente();
        List<RutinaSemanal> rutinas = this.rutinaSemanalService.getRutinaSemanalByTrainerId(((Usuario) httpSession.getAttribute("usuario")).getId());

        model.addAttribute("usuario", usuario);
        model.addAttribute("cliente", cliente);
        model.addAttribute("rutinas", rutinas);

        return "asignarRutina";
    }

    @PostMapping("/personalizar")
    public String doAsignada(@RequestParam("id") Integer id, @RequestParam("rutinaId") Integer rutinaId, Model model) {
        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(rutinaId);
        Usuario usuario = this.usuarioService.getUsuarioById(id);
        Cliente cliente = usuario.getCliente();

        model.addAttribute("rutina", rutina);
        model.addAttribute("usuario", usuario);
        model.addAttribute("cliente", cliente);

        List<SesionDeEjercicio> sesiones = this.sesionDeEjercicioService.getSesionDeEjercicioByRutinaId(rutina.getId());
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
        //TODO
        SesionDeEjercicio sesion = this.sesionDeEjercicioService.getSesionDeEjercicioById(sesionId);
        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(sesion.getRutina().getId());
        Usuario usuario = this.usuarioService.getUsuarioById(clienteId);
        Cliente cliente = usuario.getCliente();

        SesionDeEjercicio sesionCliente = new SesionDeEjercicio();
        sesionCliente.setFecha(sesion.getFecha());
        sesionCliente.setDia(sesion.getDia());
        sesionCliente.setOrden(sesion.getOrden());
        sesionCliente.setRepeticiones(repeticiones);
        sesionCliente.setCantidad(cantidad);
        sesionCliente.setPeso(peso);
        sesionCliente.setEjercicio(sesion.getEjercicio());
        sesionCliente.setRutina(rutina);
        sesionCliente.setTrabajador(sesion.getTrabajador());
        sesionCliente.setCliente(cliente);
        this.sesionDeEjercicioService.guardarSesionDeEjercicio(sesionCliente);

        List<Integer> sesionesRutina = rutina.getSesionesDeEjercicio();
        sesionesRutina.remove(sesion.getId());
        sesionesRutina.add(sesionCliente.getId());
        rutina.setSesionesDeEjercicio(sesionesRutina);

        rutina.setTrabajador(sesion.getTrabajador());
        this.rutinaSemanalService.guardarRutinaSemanal(rutina);

        cliente.setRutinaSemanal(rutina);
        this.clienteService.guardarCliente(cliente);

        model.addAttribute("rutina", rutina);
        model.addAttribute("cliente", cliente);
        model.addAttribute("usuario", usuario);

        this.personalizadas.add(sesionCliente.getId());

        List<SesionDeEjercicio> sesiones = this.sesionDeEjercicioService.getSesionDeEjercicioByRutinaIdSinPersonalizar(rutina.getId(), this.personalizadas);

        model.addAttribute("sesiones", sesiones);

        return "personalizarRutina";
    }

    @GetMapping("/clientes")
    public String doListarClientes(Model model) {
        if(!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";
        else {
            Usuario user = (Usuario) httpSession.getAttribute("usuario");
            Trabajador trabajador = this.trabajadorService.getTrabajadorById(user.getId());

            List<Cliente> clientes = this.clienteService.getClienteByTrainerId(trabajador.getId());
            model.addAttribute("clientes", clientes);

            return "clientesTrainer";
        }
    }

    @GetMapping("/desasignar")
    public String doDesAsignar(@RequestParam("id") Integer id) {
        if(!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        Cliente cliente = this.clienteService.getClienteById(id);
        List<SesionDeEjercicio> sesiones = this.sesionDeEjercicioService.getSesionDeEjercicioByClienteId(cliente.getId());

        this.sesionDeEjercicioService.deleteAll(sesiones);

        cliente.setRutinaSemanal(null);

        for (Integer f : cliente.getFeedbacks()) {
            this.feedbackService.deleteFeedback(f);
        }

        cliente.setFeedbacks(null);

        this.clienteService.guardarCliente(cliente);

        return "redirect:/bodybuilder/clientes";
    }

    @GetMapping("/seguimiento")
    public String doSeguimiento(@RequestParam("id") int id, Model model) {
        if(!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        Usuario usuario = this.usuarioService.getUsuarioById(id);
        model.addAttribute("usuario", usuario);

        Cliente cliente = usuario.getCliente();
        model.addAttribute("cliente", cliente);

        List<Feedback> feedbacks = this.feedbackService.getFeedbackByClienteId(cliente.getId());
        model.addAttribute("feedbacks", feedbacks);

        return "seguimiento";
    }

    @GetMapping("/eliminar")
    public String doEliminar(@RequestParam("id") Integer id) {
        if(!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        Usuario user = (Usuario) httpSession.getAttribute("usuario");
        Trabajador entrenador = this.trabajadorService.getTrabajadorById(user.getId());

        List<Integer> rutinas = entrenador.getRutinaSemanal();

        rutinas.remove(this.rutinaSemanalService.getRutinaSemanalById(id).getId());

        entrenador.setRutinaSemanal(rutinas);
        this.trabajadorService.guardarTrabajador(entrenador);

        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(id);
        List<Integer> clientes = rutina.getClientes();

        for(Integer c : clientes) {
            Cliente cliente = this.clienteService.getClienteById(c);
            cliente.setRutinaSemanal(null);
            this.clienteService.guardarCliente(cliente);
        }

        this.rutinaSemanalService.deleteRutinaSemanal(rutina.getId());

        return "redirect:/bodybuilder/rutinas";
    }

    @PostMapping("/borrar")
    public String doEliminar(@RequestParam("idRutina") int idRutina, @RequestParam("idSesion") int idSesion) {
        if (!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        SesionDeEjercicio sesion = this.sesionDeEjercicioService.getSesionDeEjercicioById(idSesion);
        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(idRutina);

        List<Integer> sesiones = rutina.getSesionesDeEjercicio();
        sesiones.remove(sesion.getId());
        rutina.setSesionesDeEjercicio(sesiones);

        this.sesionDeEjercicioService.deleteSesionDeEjercicio(sesion.getId());

        rutina.setTrabajador(this.trabajadorService.getTrabajadorById(((Usuario) httpSession.getAttribute("usuario")).getId()));
        this.rutinaSemanalService.guardarRutinaSemanal(rutina);

        return "redirect:/bodybuilder/mostrar?id=" + idRutina;
    }

    @GetMapping("/mostrar")
    public String doMostrar(@RequestParam("id") int id, Model model) {
        if(!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(id);
        model.addAttribute("rutina", rutina);


        List<SesionDeEjercicio> sesiones = this.sesionDeEjercicioService.getSesionDeEjercicioByRutinaId(rutina.getId());

        model.addAttribute("sesiones", sesiones);
        model.addAttribute("ejercicios", this.ejercicioService.getAllEjerciciosOrdered());

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

        SesionDeEjercicio se = this.sesionDeEjercicioService.getSesionDeEjercicioById(sesionId);
        Ejercicio ej = this.ejercicioService.getEjercicioById(ejercicioId);

        se.setOrden(orden);

        se.setRepeticiones(repeticiones);
        se.setCantidad(cantidad);
        se.setPeso(peso);
        se.setDia(dia);

        this.sesionDeEjercicioService.guardarSesionDeEjercicio(se);

        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(rutinaId);

        List<SesionDeEjercicio> sesiones = this.sesionDeEjercicioService.getSesionDeEjercicioByRutinaId(rutina.getId());
        List<Integer> sesionesId = rutina.getSesionesDeEjercicio();
        sesionesId.add(se.getId());
        rutina.setSesionesDeEjercicio(sesionesId);

        this.sesionDeEjercicioService.guardarSesionDeEjercicio(se);
        this.rutinaSemanalService.guardarRutinaSemanal(rutina);

        model.addAttribute("rutina", rutina);
        model.addAttribute("sesiones", sesiones);

        return "mostrarRutina";
    }

    @GetMapping("/tipo")
    public String mostrarNueva(@RequestParam("id") int id, Model model) {
        if (!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(id);
        model.addAttribute("rutina", rutina);

        //Mostrar solo ejercicios de fuerza para bodybuilding

        List<TipoEjercicio> tipos = new ArrayList<>();
        tipos.add(this.tipoEjercicioService.getTipoEjercicioById(1));

        model.addAttribute("tipos", tipos);

        return "elegirTipo";
    }

    @PostMapping("/elegido")
    public String doElegido(@RequestParam("id") int id, @RequestParam("tipo") int tipo, Model model) {
        if (!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(id);
        model.addAttribute("rutina", rutina);

        TipoEjercicio tipoEjercicio = this.tipoEjercicioService.getTipoEjercicioById(tipo);

        model.addAttribute("ejercicios", this.ejercicioService.getAllEjerciciosByTipo(tipoEjercicio.getId()));

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

        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(rutinaId);
        Ejercicio ejercicio = this.ejercicioService.getEjercicioById(ejercicioId);

        Usuario user = (Usuario) httpSession.getAttribute("usuario");
        Trabajador trabajador = this.trabajadorService.getTrabajadorById(user.getId());

        SesionDeEjercicio sesion = new SesionDeEjercicio();
        sesion.setOrden(orden);
        sesion.setDia(dia);
        sesion.setEjercicio(ejercicio);
        sesion.setRepeticiones(repeticiones);
        sesion.setCantidad(cantidad);
        sesion.setRutina(rutina);
        sesion.setCliente(null);
        sesion.setTrabajador(trabajador);
        sesion.setFecha(LocalDate.now());
        this.sesionDeEjercicioService.guardarSesionDeEjercicio(sesion);

        List<Integer> sesiones = rutina.getSesionesDeEjercicio();
        sesiones.add(sesion.getId());
        rutina.setSesionesDeEjercicio(sesiones.add(sesion.getId()) ? sesiones : new ArrayList<>());
        rutina.setTrabajador(trabajador);
        this.rutinaSemanalService.guardarRutinaSemanal(rutina);


        return "redirect:/bodybuilder/mostrar?id=" + rutinaId;
    }

    @GetMapping("/nueva")
    public String doNueva(Model model) {
        if (!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        RutinaSemanal rutina = new RutinaSemanal();
        model.addAttribute("rutina", rutina);
        return "formNuevaRutina";
    }

    @PostMapping("/creada")
    public String doCreada(@ModelAttribute("rutina") RutinaSemanal rutina) {
        if (!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        Usuario user = (Usuario) httpSession.getAttribute("usuario");
        Trabajador trabajador = this.trabajadorService.getTrabajadorById(user.getId());

        rutina.setTrabajador(trabajador);
        rutina.setClientes(new ArrayList<>());
        rutina.setSesionesDeEjercicio(new ArrayList<>());

        this.rutinaSemanalService.guardarRutinaSemanal(rutina);
        trabajador.setRutinaSemanal(trabajador.getRutinaSemanal().add(rutina.getId()) ? trabajador.getRutinaSemanal() : new ArrayList<>());
        this.trabajadorService.guardarTrabajador(trabajador);

        return "redirect:/bodybuilder/mostrar?id=" + rutina.getId();
    }

    @GetMapping("/ver")
    public String doVerRutina(@RequestParam("id") Integer id, Model model) {
        if (!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        Usuario usuario = this.usuarioService.getUsuarioById(id);
        Cliente cliente = usuario.getCliente();
        RutinaSemanal rutina = cliente.getRutinaSemanal();
        List<SesionDeEjercicio> sesiones = this.sesionDeEjercicioService.getSesionDeEjercicioByClienteId(cliente.getId());

        model.addAttribute("rutina", rutina);
        model.addAttribute("sesiones", sesiones);
        model.addAttribute("cliente", cliente);
        model.addAttribute("usuario", usuario);

        return "mostrarRutinaDeUnCliente";
    }

    @PostMapping("/actualizar")
    public String doActualizar(@RequestParam("id") int id, @RequestParam("repeticiones") String repeticiones,
                               @RequestParam("cantidad") String cantidad,
                               @RequestParam("peso") String peso, Model model) {
        if (!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        SesionDeEjercicio sesion = this.sesionDeEjercicioService.getSesionDeEjercicioById(id);

        Cliente cliente = sesion.getCliente();
        Usuario usuario = usuarioService.getUsuarioById(cliente.getId());
        RutinaSemanal rutina = cliente.getRutinaSemanal();
        List<SesionDeEjercicio> sesiones = this.sesionDeEjercicioService.getSesionDeEjercicioByClienteId(cliente.getId());

        sesion.setRepeticiones(repeticiones);
        sesion.setCantidad(cantidad);
        sesion.setPeso(peso);

        model.addAttribute("usuario", usuario);
        model.addAttribute("rutina", rutina);
        model.addAttribute("sesiones", sesiones);
        model.addAttribute("cliente", cliente);

        this.sesionDeEjercicioService.guardarSesionDeEjercicio(sesion);

        return "mostrarRutinaDeUnCliente";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtroRutina") FiltroRutina filtroRutina, Model model) {
        if (!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        Usuario user = (Usuario) httpSession.getAttribute("usuario");
        Trabajador trabajador = this.trabajadorService.getTrabajadorById(user.getId());

        String nombre = (filtroRutina.getNombre().isEmpty() ? null : filtroRutina.getNombre());

        LocalDate fechaInicio = null;
        if (filtroRutina.getFechaInicio() != null && !filtroRutina.getFechaInicio().isEmpty() && !"yyyy-mm-dd".equals(filtroRutina.getFechaInicio())) {
            fechaInicio = LocalDate.parse(filtroRutina.getFechaInicio());
        }

        LocalDate fechaFin = null;
        if (filtroRutina.getFechaFin() != null && !filtroRutina.getFechaFin().isEmpty() && !"yyyy-mm-dd".equals(filtroRutina.getFechaFin())) {
            fechaFin = LocalDate.parse(filtroRutina.getFechaFin());
        }

        List<RutinaSemanal> rutinas = this.rutinaSemanalService.getRutinaSemanalFiltradas(trabajador.getId(), nombre, fechaInicio, fechaFin);

        if (rutinas.isEmpty()) {
            model.addAttribute("noResults", true);
        }else {
            model.addAttribute("noResults", false);
        }

        model.addAttribute("filtroRutina", filtroRutina);
        model.addAttribute("rutinas", rutinas);

        return "rutinasTrainer";
    }




}
