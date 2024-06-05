package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.entity.*;
import es.uma.proyectogrupo18.dao.DietaRepository;
import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.ui.FiltroDieta;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/dietista")
public class dietistaController {

    @Autowired
    protected TrabajadorRepository trabajadorRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected DietaRepository dietaRepository;

    @Autowired
    protected ComidaRepository comidaRepository;

    @Autowired
    protected DietaComidaRepository dietaComidaRepository;

    @Autowired
    protected MenuRepository menuRepository;

    @GetMapping("/")
    public String doCustomerHome(HttpSession httpSession) {
        if(httpSession.getAttribute("tipo") != "dietista")
            return "sinPermiso";
        else
            return "dietistaHome";
    }

    @GetMapping("/info")
    public String getDietistaInfo(HttpSession httpSession, Model model, @RequestParam("id") Integer id) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }

        model.addAttribute("filtro", new FiltroDieta());
        TrabajadorEntity dietista = this.trabajadorRepository.findById(id).orElse(null);
        Integer dietistaId = dietista.getUsuarioId();
        httpSession.setAttribute("usuarioid", dietistaId);

        List<DietaEntity> lista = this.dietaRepository.buscarPorIdTrabajador(dietistaId);
        model.addAttribute("dietas", lista);
        return "dietasInfo";
    }

    @GetMapping("/ver")
    public String verDieta(HttpSession httpSession, Model model, @RequestParam("id") Integer id) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }
        DietaEntity dieta = this.dietaRepository.findById(id).orElse(null);
        model.addAttribute("dieta", dieta);

        Collection<DietaComidaEntity> dietaComidas = dieta.getDietaComidasByCodigo();
        List<ComidaEntity> comidas = new ArrayList<>();

        for (DietaComidaEntity dc : dietaComidas) {
            comidas.add(dc.getComidaByComidaId());
        }

        model.addAttribute("comidas", comidas);

        return "verDieta";
    }

    @GetMapping("/menu")
    public String doMenu(HttpSession httpSession, @RequestParam("id")Integer id, @RequestParam("dietaid") Integer id2, Model model){
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }
        ComidaEntity comida = this.comidaRepository.findById(id).orElse(null);
        int menuId = comida.getId();
        MenuEntity menu = this.menuRepository.seleccionarMenu(menuId);
        model.addAttribute("menuA", menu);
        DietaEntity dieta = this.dietaRepository.findById(id2).orElse(null);
        model.addAttribute("dieta", dieta);
        return "menu";
    }

    @PostMapping("/filtrar")
    public String filtrarDietas(HttpSession httpSession, @ModelAttribute("filtro") FiltroDieta filtro, Model model, HttpSession session, @RequestParam("id") Integer id) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }
        String strTo = "dietasInfo";
        if (filtro.estaVacio()) {
            strTo = "redirect:/dietista/info?id=" + id;
        } else if(filtro.getFiltro1().equals("") && !filtro.getFiltro2().equals("")) {
            List<DietaEntity> lista = this.dietaRepository.filtrarDietasPorTipo(filtro.getFiltro2(), id);
            model.addAttribute("dietas", lista);
            model.addAttribute("filtro", filtro);
        }else{
            List<DietaEntity> lista = this.dietaRepository.filtrarDietas(filtro.getIntegerComidas() ,filtro.getFiltro2(), id);
            model.addAttribute("dietas", lista);
            model.addAttribute("filtro", filtro);
        }

        return strTo;
    }

    @GetMapping("/eliminar")
    public String borrarDieta(@RequestParam("id") Integer id, HttpSession session){
        if(session.getAttribute("tipo") != "dietista")
            return "sinPermiso";

        UsuarioEntity user = (UsuarioEntity) session.getAttribute("usuario");
        TrabajadorEntity dietista = this.trabajadorRepository.findById(user.getId()).orElse(null);

        Collection<DietaEntity> dietas = dietista.getDietasByUsuarioId();

        dietas.remove(this.dietaRepository.findById(id).orElse(null));

        dietista.setDietasByUsuarioId(dietas);
        this.trabajadorRepository.saveAndFlush(dietista);

        DietaEntity dieta = this.dietaRepository.findById(id).orElse(null);
        Collection<ClienteEntity> clientes = dieta.getTrabajadorByTrabajadorId().getClientesDietista();

        for(ClienteEntity c : clientes)
        {
            c.setDietaCodigo(null);
            this.clienteRepository.saveAndFlush(c);
        }

        this.dietaRepository.delete(dieta);

        return "redirect:/dietista/info?id=" + dietista.getUsuarioId();
    }

    @GetMapping("/crear")
    public String crearDieta(Model model){
        DietaEntity dieta = new DietaEntity();
        model.addAttribute("dieta", dieta);
        List<ComidaEntity> comidas = this.comidaRepository.findAll();
        model.addAttribute("comidas", comidas);
        return "crearDieta";
    }

    @GetMapping("/modificar")
    public String modificarDieta(HttpSession httpSession, Model model, @RequestParam("id") Integer id) {
        if (!"dietista".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }
        DietaEntity dieta = this.dietaRepository.findById(id).orElse(null);
        model.addAttribute("dieta", dieta);

        Collection<DietaComidaEntity> dietaComidas = dieta.getDietaComidasByCodigo();
        List<ComidaEntity> comidas = new ArrayList<>();

        for (DietaComidaEntity dc : dietaComidas) {
            comidas.add(dc.getComidaByComidaId());
        }

        model.addAttribute("comidas", comidas);

        return "crearDieta";
    }

    @GetMapping("/clientes")
    public String doListarClientes(HttpSession httpSession, Model model) {
        if(httpSession.getAttribute("tipo") != "dietista")
            return "sinPermiso";
        else
        {
            UsuarioEntity user = (UsuarioEntity) httpSession.getAttribute("usuario");
            TrabajadorEntity trabajador = this.trabajadorRepository.findById(user.getId()).orElse(null);

            List<ClienteEntity> clientes = this.clienteRepository.findClientesByDietista(trabajador);
            model.addAttribute("clientes",clientes);

            return "clientesDietista";
        }
    }

    @GetMapping("/asignar")
    public String doAsignarRutina(HttpSession httpSession, @RequestParam("id") Integer id, Model model) {
        if(httpSession.getAttribute("tipo") != "dietista")
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        List<DietaEntity> dietas = this.dietaRepository.buscarPorIdTrabajador(((UsuarioEntity) httpSession.getAttribute("usuario")).getId());

        model.addAttribute("cliente",cliente);
        model.addAttribute("dietas",dietas);

        return "asignarDieta";
    }


}
