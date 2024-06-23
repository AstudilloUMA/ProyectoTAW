/*
Autores:
Pablo Astudillo Fraga: 35%
Álvaro Morales Perujo: 65%
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
        Cliente cliente = this.clienteService.getClienteById(id);

        model.addAttribute("rutina", rutina);
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

        SesionDeEjercicio sesion = this.sesionDeEjercicioService.getSesionDeEjercicioById(sesionId);
        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(sesion.getRutina().getId());
        Cliente cliente = this.clienteService.getClienteById(clienteId);


        sesion.setRepeticiones(repeticiones);
        sesion.setCantidad(cantidad);
        sesion.setPeso(peso);
        sesion.setCliente(cliente);
        this.sesionDeEjercicioService.guardarSesionDeEjercicio(sesion);

        rutina.setClientes(rutina.getClientes().add(cliente.getId()) ? rutina.getClientes() : new ArrayList<>());
        rutina.setSesionesDeEjercicio(rutina.getSesionesDeEjercicio().add(sesion.getId()) ? rutina.getSesionesDeEjercicio() : new ArrayList<>());
        this.rutinaSemanalService.guardarRutinaSemanal(rutina);
        cliente.setSesionDeEjercicios(cliente.getSesionDeEjercicios().add(sesion.getId()) ? cliente.getSesionDeEjercicios() : new ArrayList<>());
        cliente.setRutinaSemanal(rutina);
        this.clienteService.guardarCliente(cliente);

        model.addAttribute("rutina", rutina);
        model.addAttribute("cliente", cliente);

        this.personalizadas.add(sesion.getId());

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
        List<SesionDeEjercicio> sesiones = this.sesionDeEjercicioService.getSesionDeEjercicioByRutinaId(cliente.getRutinaSemanal().getId());

        this.sesionDeEjercicioService.desasignar(sesiones);

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
        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(rutinaId);

        se.setOrden(orden);

        se.setRepeticiones(repeticiones);
        se.setCantidad(cantidad);
        se.setPeso(peso);
        se.setDia(dia);
        se.setRutina(rutina);

        rutina.setSesionesDeEjercicio(rutina.getSesionesDeEjercicio().add(se.getId()) ? rutina.getSesionesDeEjercicio() : new ArrayList<>());

        this.sesionDeEjercicioService.guardarSesionDeEjercicio(se);
        this.rutinaSemanalService.guardarRutinaSemanal(rutina);

        model.addAttribute("rutina", rutina);
        model.addAttribute("sesiones", this.sesionDeEjercicioService.getSesionDeEjercicioByRutinaId(rutina.getId()));

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
        Integer sesionId = this.sesionDeEjercicioService.guardarSesionDeEjercicio(sesion);


        rutina.setSesionesDeEjercicio(rutina.getSesionesDeEjercicio().add(sesionId) ? rutina.getSesionesDeEjercicio() : new ArrayList<>());
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
        List<SesionDeEjercicio> sesiones = this.sesionDeEjercicioService.getSesionDeEjercicioByRutinaId(rutina.getId());

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

    @GetMapping("/duplicar")
    public String doDuplicar(@RequestParam("id") Integer id){
        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(id);

        RutinaSemanal nuevaRutina = new RutinaSemanal();
        String nombre = this.rutinaSemanalService.generarNombreDuplicado(rutina.getNombre());
        nuevaRutina.setNombre(nombre);
        nuevaRutina.setNombre(nombre);
        nuevaRutina.setFechaInicio(rutina.getFechaInicio());
        nuevaRutina.setFechaFin(rutina.getFechaFin());
        nuevaRutina.setTrabajador(rutina.getTrabajador());
        nuevaRutina.setClientes(new ArrayList<>());
        this.rutinaSemanalService.guardarRutinaSemanal(nuevaRutina);
        nuevaRutina.setSesionesDeEjercicio(duplicarSesiones(rutina, nuevaRutina));
        this.rutinaSemanalService.guardarRutinaSemanal(nuevaRutina);

        return "redirect:/bodybuilder/rutinas";
    }

    private List<Integer> duplicarSesiones(RutinaSemanal rutina, RutinaSemanal nuevaRutina){
        List<Integer> lista = new ArrayList<>();

        for(Integer i : rutina.getSesionesDeEjercicio()){
            SesionDeEjercicio sesion = this.sesionDeEjercicioService.getSesionDeEjercicioById(i);
            SesionDeEjercicio nuevaSesion = new SesionDeEjercicio();
            nuevaSesion.setOrden(sesion.getOrden());
            nuevaSesion.setDia(sesion.getDia());
            nuevaSesion.setEjercicio(sesion.getEjercicio());
            nuevaSesion.setRepeticiones(null);
            nuevaSesion.setCantidad(sesion.getCantidad());
            nuevaSesion.setPeso(null);
            nuevaSesion.setRutina(nuevaRutina);
            nuevaSesion.setCliente(null);
            nuevaSesion.setTrabajador(sesion.getTrabajador());
            nuevaSesion.setFecha(sesion.getFecha());

            int id = this.sesionDeEjercicioService.guardarSesionDeEjercicio(nuevaSesion);
            lista.add(id);
        }

        return lista;
    }

    @PostMapping("/actualizarAtributos")
    public String doActualizarAtributos(@RequestParam("id") Integer id, @RequestParam("nombre") String nombre,
                                        @RequestParam("fechaDeInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                                        @RequestParam("fechaDeFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        if (!"bodybuilder".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        RutinaSemanal rutina = this.rutinaSemanalService.getRutinaSemanalById(id);
        rutina.setNombre(nombre);
        rutina.setFechaInicio(fechaInicio);
        rutina.setFechaFin(fechaFin);
        this.rutinaSemanalService.guardarRutinaSemanal(rutina);


        return "redirect:/bodybuilder/rutinas";    }


}
