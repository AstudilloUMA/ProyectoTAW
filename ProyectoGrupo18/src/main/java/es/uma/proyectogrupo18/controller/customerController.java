/* Autores:
Andrés Santaella González: 40%
Pablo Astudillo Fraga: 60%
 */

package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.entity.*;
import es.uma.proyectogrupo18.ui.Quicksort;
import es.uma.proyectogrupo18.ui.SesionEjercicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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


    //Pablo Astudillo Fraga
    @GetMapping("/")
    public String doCustomerHome() {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        return "costumerHome";
    }

    //Pablo Astudillo Fraga
    @GetMapping("/rutina")
    public String doCustomerRutinas(@RequestParam("id") Integer usuarioId, Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(usuarioId).orElse(null);
        RutinaSemanalEntity rutina = cliente.getRutina();

        List<SesionDeEjercicioEntity> sesiones = this.sesionDeEjercicioRepository.findSesionesByCliente(cliente);

        model.addAttribute("rutina", rutina);
        model.addAttribute("sesiones", sesiones);

        return "mostrarRutinaCliente";
    }

    //Iniciado por Andres Santaella y corregido por Pablo Astudillo Fraga
    @PostMapping("/feedback")
    public String actualizarProgreso(@RequestParam("sesionId") int sesionId,
                                     @RequestParam("clienteId") int clienteId,
                                     Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        ClienteEntity cliente = this.clienteRepository.findById(clienteId).orElse(null);
        SesionDeEjercicioEntity sesion = this.sesionDeEjercicioRepository.findById(sesionId).orElse(null);

        FeedbackEntity feedback = (this.feedbackRepository.findBySesion(sesion,cliente) == null)
                                        ? new FeedbackEntity()
                                        : this.feedbackRepository.findBySesion(sesion,cliente);

        model.addAttribute("cliente", cliente);
        model.addAttribute("sesion", sesion);
        model.addAttribute("feedback", feedback);

        return "formFeedback";
    }

    //Andrés Santaella González y Pablo Astudillo Fraga
    @PostMapping("/guardarFeedback")
    public String guardarProgreso(
            @RequestParam("clienteId") int clienteId,
            @RequestParam("feedbackId") int feedbackId,
            @RequestParam("sesionId") int sesionId,
            @RequestParam("repeticiones") String repeticiones,
            @RequestParam("series") String series,
            @RequestParam("peso") String peso,
            @RequestParam("calificacion") int calificacion,
            @RequestParam("estado") String estado,
            @RequestParam("comentarios") String comentarios,
            Model model) {

        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        SesionDeEjercicioEntity sesion = this.sesionDeEjercicioRepository.findById(sesionId).orElse(null);
        ClienteEntity cliente = this.clienteRepository.findById(clienteId).orElse(null);

        FeedbackEntity feedback;

        if(feedbackId == -1)
            feedback = new FeedbackEntity();
        else
            feedback = this.feedbackRepository.findById(feedbackId).orElse(null);

        feedback.setCliente(cliente);
        feedback.setRepeticiones(repeticiones);
        feedback.setSeries(series);
        feedback.setPeso(peso);
        feedback.setCalificacion(calificacion);
        feedback.setEstadoDelCliente(estado);
        feedback.setComentarios(comentarios);
        feedback.setSesion(sesion);

        Set<FeedbackEntity> feedbacks = cliente.getFeedbacks();
        feedbacks.add(feedback);

        cliente.setFeedbacks(feedbacks);

        this.feedbackRepository.saveAndFlush(feedback);
        this.clienteRepository.saveAndFlush(cliente);

        return "redirect:/customer/rutina?id=" + clienteId;
    }

    //Pablo Astudillo Fraga
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

    //Andrés Santaella González
    @GetMapping("/actualizarProgresoDieta")
    public String actualizarProgresoDieta(Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        UsuarioEntity usuario = (UsuarioEntity) httpSession.getAttribute("usuario");
        ClienteEntity cliente = this.clienteRepository.findById(usuario.getId()).orElse(null);
        DietaEntity dieta = cliente.getDietaCodigo();

        FeedbackdietaEntity feedback;
        if(this.feedbackdietaRepository.findByCliente(cliente) != null)
            feedback = this.feedbackdietaRepository.findByCliente(cliente);
        else
            feedback = new FeedbackdietaEntity();

        model.addAttribute("feedback", feedback);
        model.addAttribute("cliente", cliente);
        model.addAttribute("dieta", dieta);

        return "actualizarPDieta";
    }

    //Andrés Santaella González
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

        FeedbackdietaEntity feedback;

        if(this.feedbackdietaRepository.findByCliente(cliente) != null)
            feedback = this.feedbackdietaRepository.findByCliente(cliente);
        else
        {
            feedback = new FeedbackdietaEntity();
            feedback.setDietaCodigo(dieta);
            feedback.setCliente(cliente);
        }

        feedback.setCliente(cliente);
        feedback.setCalificacion(calificacion);
        feedback.setComentarios(comentarios);

        Set<FeedbackdietaEntity> feedbacks = cliente.getFeedbackdietas();
        feedbacks.add(feedback);

        cliente.setFeedbackdietas(feedbacks);

        this.feedbackdietaRepository.saveAndFlush(feedback);
        this.clienteRepository.saveAndFlush(cliente);

        model.addAttribute("cliente", cliente);
        model.addAttribute("dieta", dieta);

        List<ComidaEntity> comidas = dieta.getComidas();

        Quicksort.quickSortDietas(comidas);

        model.addAttribute("comidas", comidas);

        return "verDietaCustomer";
    }


}