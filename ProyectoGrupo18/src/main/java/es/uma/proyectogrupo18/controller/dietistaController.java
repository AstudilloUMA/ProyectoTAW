/*
Autores:
-Miguel Sánchez Hontoria:80%
Pablo Astudillo fraga: 20%
Álvaro Morales Perujo:5%
 */
package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dto.*;
import es.uma.proyectogrupo18.service.*;
import es.uma.proyectogrupo18.ui.FiltroDieta;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/dietista")
public class dietistaController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    protected TrabajadorService trabajadorService;

    @Autowired
    protected DietaService dietaService;

    @Autowired
    protected ComidaService comidaService;

    @Autowired
    protected MenuService menuService;

    @GetMapping("/")
    public String doDietistaHome() {
        if(httpSession.getAttribute("tipo") != "dietista")
            return "sinPermiso";
        else
            return "dietistaHome";
    }

    @GetMapping("/info")
    public String getDietistaInfo(Model model, @RequestParam("id") Integer id) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }

        model.addAttribute("filtro", new FiltroDieta());
        Trabajador trabajador = this.trabajadorService.getTrabajadorById(id);
        if (trabajador != null) {
            Integer dietistaId = trabajador.getId();
            httpSession.setAttribute("usuarioid", dietistaId);

            List<Dieta> lista = this.dietaService.getDietasByDietistaId(dietistaId);
            model.addAttribute("dietas", lista);
            return "dietasInfo";
        } else {
            return "sinPermiso";
        }
    }

    @GetMapping("/ver")
    public String verDieta(Model model, @RequestParam("id") Integer id) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }

        Dieta dieta = this.dietaService.getDietaById(id);
        model.addAttribute("dieta", dieta);

        List<Comida> comidas = this.comidaService.getComidaByDietaId(dieta.getId());
        model.addAttribute("comidas", comidas);

        return "verDieta";
    }

    @GetMapping("/menu")
    public String doMenu(@RequestParam("id") Integer id, @RequestParam("dietaid") Integer id2, Model model, @RequestParam(value = "from", required = false) String from) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }
        Comida comida = this.comidaService.getComidaById(id);
        int menuId = comida.getId();
        Menu menu = this.menuService.getMenuById(menuId);
        model.addAttribute("menuA", menu);
        Dieta dieta = this.dietaService.getDietaById(id2);
        model.addAttribute("dieta", dieta);
        model.addAttribute("from", from);
        return "menu";
    }

/*
    @PostMapping("/filtrar")
    public String filtrarDietas(@ModelAttribute("filtro") FiltroDieta filtro, Model model, @RequestParam("id") Integer id) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }
        String strTo = "dietasInfo";
        if (filtro.estaVacio()) {
            strTo = "redirect:/dietista/info?id=" + id;
        } else if(filtro.getFiltro1().equals("") && !filtro.getFiltro2().equals("")) {
            List<Dieta> lista = this.dietaService.getDietaFiltradas(filtro.getFiltro2(), id);
            model.addAttribute("dietas", lista);
            model.addAttribute("filtro", filtro);
        } else {
            List<DietaEntity> lista = this.dietaRepository.filtrarDietas(filtro.getIntegerComidas() ,filtro.getFiltro2(), id);
            model.addAttribute("dietas", lista);
            model.addAttribute("filtro", filtro);
        }

        return strTo;
    }


    @GetMapping("/eliminar")
    public String borrarDieta(@RequestParam("id") Integer id) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }

        UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
        TrabajadorEntity dietista = this.trabajadorRepository.findById(user.getId()).orElse(null);

        if (dietista != null) {
            DietaEntity dietaToRemove = this.dietaRepository.findById(id).orElse(null);

            if (dietaToRemove != null) {
                List<ClienteEntity> clientes = dietaToRemove.getClientes();
                for (ClienteEntity cliente : clientes) {
                    if (cliente.getDietaCodigo() != null && cliente.getDietaCodigo().getId() == dietaToRemove.getId()) {
                        cliente.setDietaCodigo(null);
                        this.clienteRepository.saveAndFlush(cliente);
                    }
                }

                List<DietaEntity> dietas = dietista.getDietas();
                dietas.remove(dietaToRemove);
                dietista.setDietas(dietas);
                this.trabajadorRepository.saveAndFlush(dietista);
                this.dietaRepository.delete(dietaToRemove);
                this.dietaRepository.flush();
            }
        }

        return "redirect:/dietista/info?id=" + dietista.getId();
    }

    @GetMapping("/crear")
    public String crearDieta(Model model){
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }
        DietaEntity dieta = new DietaEntity();
        model.addAttribute("dieta", dieta);
        List<ComidaEntity> comidas = this.comidaRepository.findAll();
        model.addAttribute("comidas", comidas);
        return "crearDieta";
    }

    @PostMapping("/guardar")
    public String guardarDieta(@RequestParam("id") Integer id,
                                @RequestParam("id2") Integer id2,
                                @RequestParam("nombre") String nombre,
                               @RequestParam("tipo") String tipo,
                               @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fI,
                               @RequestParam("fechaFin")@DateTimeFormat(pattern = "yyyy-MM-dd") Date fF,
                               @RequestParam("numComidas")Integer num,
                               @RequestParam("comid") Integer[] comids,
                               Model model) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }

        if ((num < 3) || (num > 5) || (num != comids.length)) {
            if(id2 == 0){
                String opc = "crear";
                model.addAttribute("opc", opc);
            }else{
                String opc = "modificar";
                model.addAttribute("id", id2);
                model.addAttribute("opc", opc);
            }
            return "errorDieta1";
        }

        Calendar calInicio = Calendar.getInstance();
        calInicio.setTime(fI);

        Calendar calFin = Calendar.getInstance();
        calFin.setTime(fF);

        long diffInMillis = calFin.getTimeInMillis() - calInicio.getTimeInMillis();
        long dias = diffInMillis / (1000 * 60 * 60 * 24);

        if (dias != 7) {
            if(id2 == 0){
                String opc = "crear";
                model.addAttribute("opc", opc);
            }else{
                String opc = "modificar";
                model.addAttribute("id", id2);
                model.addAttribute("opc", opc);
            }
            return "errorDieta2";
        }

        java.sql.Date sqlFechaInicio = new java.sql.Date(fI.getTime());
        java.sql.Date sqlFechaFin = new java.sql.Date(fF.getTime());
        java.time.LocalDate fechaInicio = sqlFechaInicio.toLocalDate();
        java.time.LocalDate fechaFin = sqlFechaFin.toLocalDate();

        if(id2 == 0){
            DietaEntity nuevaDieta = new DietaEntity();
            nuevaDieta.setNombre(nombre);
            nuevaDieta.setTipo(tipo);
            nuevaDieta.setFechaInicio(fechaInicio);
            nuevaDieta.setFechaFin(fechaFin);
            nuevaDieta.setNumComidas(num);
            nuevaDieta.setTrabajador(this.trabajadorRepository.findById(id).orElse(null));

            List<ComidaEntity> comidas = new ArrayList<>();
            for (Integer comid : comids) {
                ComidaEntity comida = this.comidaRepository.findById(comid).orElse(null);
                if (comida != null) {
                    comidas.add(comida);
                }
            }
            nuevaDieta.setComidas(comidas);

            this.dietaRepository.saveAndFlush(nuevaDieta);
        }else {
            DietaEntity dieta = this.dietaRepository.findById(id2).orElse(null);
            dieta.setNombre(nombre);
            dieta.setTipo(tipo);
            dieta.setFechaInicio(fechaInicio);
            dieta.setFechaFin(fechaFin);
            dieta.setNumComidas(num);
            dieta.setTrabajador(this.trabajadorRepository.findById(id).orElse(null));

            List<ComidaEntity> comidas = new ArrayList<>();
            for (Integer comid : comids) {
                ComidaEntity comida = this.comidaRepository.findById(comid).orElse(null);
                if (comida != null) {
                    comidas.add(comida);
                }
            }
            dieta.setComidas(comidas);

            this.dietaRepository.saveAndFlush(dieta);
        }

        return "redirect:/dietista/info?id=" + id;
    }

    @GetMapping("/modificar")
    public String modificarDieta(Model model, @RequestParam("id") Integer id) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }

        DietaEntity dieta = this.dietaRepository.findById(id).orElse(null);
        if(dieta != null){
            model.addAttribute("dieta", dieta);
            List<ComidaEntity> comidas = this.comidaRepository.findAll();
            model.addAttribute("comidas", comidas);
        }

        return "modificarDieta";
    }

    //CLIENTES
    @GetMapping("/clientes")
    public String doListarClientes(Model model) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        } else {
            UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
            TrabajadorEntity trabajador = this.trabajadorRepository.findById(user.getId()).orElse(null);

            List<ClienteEntity> clientes = this.clienteRepository.findClientesByDietista(trabajador);
            model.addAttribute("clientes",clientes);

            return "clientesDietista";
        }
    }

    @GetMapping("/asignar")
    public String doAsignarRutina(@RequestParam("id") Integer id, Model model) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }

        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        List<DietaEntity> dietas = this.dietaRepository.buscarPorIdTrabajador(((UsuarioEntity) httpSession.getAttribute("usuario")).getId());

        model.addAttribute("cliente",cliente);
        model.addAttribute("dietas",dietas);

        return "asignarDieta";
    }

    @PostMapping("/asignada")
    public String doAsignada(@RequestParam("id") Integer id, @RequestParam("dietaId") Integer dietaId) {
        DietaEntity dieta = this.dietaRepository.findById(dietaId).orElse(null);
        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);

        cliente.setDietaCodigo(dieta);
        Collection<ClienteEntity> clientes = dieta.getTrabajador().getClientesEntrenador();
        clientes.add(cliente);

        this.dietaRepository.saveAndFlush(dieta);
        this.clienteRepository.saveAndFlush(cliente);

        return "redirect:/dietista/clientes";
    }

    // Hecho por Miguel Sánchez Hontoria y corregido por Pablo Astudillo Fraga
    @GetMapping("/desasignar")
    public String doDesAsignar(@RequestParam("id") Integer id){
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }
        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        cliente.setDietaCodigo(null);

        FeedbackdietaEntity feedback = this.feedbackdietaRepository.findByCliente(cliente);

        if(feedback != null)
            this.feedbackdietaRepository.delete(feedback);

        cliente.setFeedbacks(null);

        this.clienteRepository.saveAndFlush(cliente);
        this.feedbackdietaRepository.flush();

        return "redirect:/dietista/clientes";
    }


    // Hecho por Miguel Sánchez Hontoria y corregido por Pablo Astudillo Fraga
    @GetMapping("/seguimiento")
    public String doSeguimiento(@RequestParam("id") Integer id, @RequestParam("idDieta") Integer idDieta, Model model) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }

        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        model.addAttribute("cliente",cliente);

        FeedbackdietaEntity feedback = this.feedbackdietaRepository.findByCliente(cliente);
        model.addAttribute("feedback", feedback);

        DietaEntity dieta = this.dietaRepository.findById(idDieta).orElse(null);
        model.addAttribute("dieta", dieta);

        return "seguimientoDietas";
    }
*/
}

