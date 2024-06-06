package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.RutinaSemanalRepository;
import es.uma.proyectogrupo18.entity.*;
import es.uma.proyectogrupo18.ui.Quicksort;
import es.uma.proyectogrupo18.ui.SesionEjercicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class customerController {

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected RutinaSemanalRepository rutinaSemanalRepository;

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
        RutinaSemanalEntity rutina = cliente.getRutinaSemanalByRutinaId();

        List<SesionDeEntrenamientoEntity> sesiones = new ArrayList<>();
        List<SesionEjercicio> ses = new ArrayList<>();

        for(RutinaSemanalEntrenamientoEntity r : rutina.getRutinaSemanalEntrenamientosById()) {
            sesiones.add(r.getSesionDeEntrenamientoBySesionDeEntrenamientoId());
        }
        Quicksort.quickSortSesiones(sesiones);

        for (SesionDeEntrenamientoEntity s : sesiones) {
            List<EntrenamientoEjercicioEntity> e = (List<EntrenamientoEjercicioEntity>) s.getEntrenamientoEjerciciosById();
            for (EntrenamientoEjercicioEntity ee : e) {
                ses.add(new SesionEjercicio(ee.getSesionDeEjercicioBySesionDeEjercicioId(), s.getDia()));
            }
        }
        
        model.addAttribute("rutina", rutina);
        model.addAttribute("sesiones", ses);

        return "mostrarRutinaCliente";
    }

    @GetMapping("/dieta")
    public String verDieta(@RequestParam("id") Integer usuarioId, Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(usuarioId).orElse(null);

        DietaEntity dieta = cliente.getDietaCodigo();
        model.addAttribute("dieta", dieta);

        Collection<DietaComidaEntity> dietaComidas = dieta.getDietaComidasByCodigo();
        List<ComidaEntity> comidas = new ArrayList<>();

        for (DietaComidaEntity dc : dietaComidas) {
            comidas.add(dc.getComidaByComidaId());
        }

        Quicksort.quickSortDietas(comidas);

        model.addAttribute("comidas", comidas);

        return "verDietaCustomer";
    }
}