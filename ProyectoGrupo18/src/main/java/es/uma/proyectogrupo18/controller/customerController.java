/* Autores:
Andrés Santaella González: 30%
Pablo Astudillo Fraga: 60%
-Miguel Sánchez Hontoria:10%
 */

package es.uma.proyectogrupo18.controller;


import es.uma.proyectogrupo18.dto.*;

import es.uma.proyectogrupo18.service.*;
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
    protected ClienteService clienteService;

    @Autowired
    protected RutinaSemanalService rutinaSemanalService;

    @Autowired
    protected SesionDeEjercicioService sesionDeEjercicioService;

    @Autowired
    protected DietaService dietaService;

    @Autowired
    protected FeedbackService feedbackService;

    @Autowired
    protected FeedbackDietaService feedbackDietaService;
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

        Cliente cliente = this.clienteService.getClienteById(usuarioId);
        RutinaSemanal rutina = cliente.getRutinaSemanal();

        List<SesionDeEjercicio> sesiones = this.sesionDeEjercicioService.getSesionDeEjercicioByClienteId(cliente.getId());

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

        Cliente cliente = this.clienteService.getClienteById(clienteId);
        SesionDeEjercicio sesion = this.sesionDeEjercicioService.getSesionDeEjercicioById(sesionId);

        Feedback feedback = null;
        if (this.feedbackService.getFeedbackByClienteId(clienteId) == null)
             feedback = new Feedback();
                     else
            feedbackService.getFeedbackByClienteId(clienteId);

        model.addAttribute("cliente", cliente);
        model.addAttribute("sesion", sesion);
        model.addAttribute("feedback", feedback);

        return "formFeedback";
    }

    //Iniciado por Andrés Santaella González y  corregido por Pablo Astudillo Fraga
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

        SesionDeEjercicio sesion = this.sesionDeEjercicioService.getSesionDeEjercicioById(sesionId);
        Cliente cliente = this.clienteService.getClienteById(clienteId);

        Feedback feedback;

        if(feedbackId == -1)
            feedback = new Feedback();
        else
            feedback = this.feedbackService.getFeedbackById(feedbackId);

        feedback.setCliente(cliente);
        feedback.setRepeticiones(repeticiones);
        feedback.setSeries(series);
        feedback.setPeso(peso);
        feedback.setCalificacion(calificacion);
        feedback.setEstadoDelCliente(estado);
        feedback.setComentarios(comentarios);
        feedback.setSesion(sesion);

        List<Integer> feedbacks = cliente.getFeedbacks();
        feedbacks.add(feedback.getId());

        cliente.setFeedbacks(feedbacks);

        this.feedbackService.guardarFeedback(feedback);
        this.clienteService.guardarCliente(cliente);

        return "redirect:/customer/rutina?id=" + clienteId;
    }

    //Pablo Astudillo Fraga
    @GetMapping("/dieta")
    public String verDieta(@RequestParam("id") Integer usuarioId, Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        Cliente cliente = this.clienteService.getClienteById(usuarioId);

        Dieta dieta = cliente.getDieta();
        model.addAttribute("cliente", cliente);
        model.addAttribute("dieta", dieta);

       /* List<ComidaEntity> comidas = dieta.getComidas();

        Quicksort.quickSortDietas(comidas);

        model.addAttribute("comidas", comidas);
*/
        return "verDietaCustomer";
    }

    //Iniciado por Andrés Santaella González, modificado y corregido por Miguel Sánchez Hontoria y terminar de corregir y modificar por Pablo Astudillo Fraga
    @GetMapping("/actualizarProgresoDieta")
    public String actualizarProgresoDieta(Model model) {
        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        Cliente cliente = this.clienteService.getClienteById(usuario.getId());
        Dieta dieta = cliente.getDieta();

        FeedbackDieta feedback;
        if(this.feedbackDietaService.getFeedbackDietaByCliente(cliente,dieta) != null)
            feedback = this.feedbackDietaService.getFeedbackDietaById(dieta.getId());
        else
            feedback = new FeedbackDieta();

        model.addAttribute("feedback", feedback);
        model.addAttribute("cliente", cliente);
        model.addAttribute("dieta", dieta);

        return "actualizarPDieta";
    }

    //iniciado por Andrés Santaella González, corregido por Miguel Sánchez Hontoria y terminado y corregido por Pablo Astudillo Fraga
    @PostMapping("/guardarProgresoDieta")
    public String guardarProgresoDieta(
            @RequestParam("calificacion") Integer calificacion,
            @RequestParam("comentarios") String comentarios,
            Model model) {

        if (!"customer".equals(httpSession.getAttribute("tipo")))
            return "sinPermiso";

        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        Cliente cliente = this.clienteService.getClienteById(usuario.getId());

        Dieta dieta = cliente.getDieta();

        FeedbackDieta feedback;

        if(this.feedbackDietaService.getFeedbackDietaByCliente(cliente,dieta) != null)
            feedback = this.feedbackDietaService.getFeedbackDietaById(dieta.getId());
        else
        {
            feedback = new FeedbackDieta();
            feedback.setDietaCodigo(dieta);
            feedback.setCliente(cliente);
        }

        feedback.setCliente(cliente);
        feedback.setCalificacion(calificacion);
        feedback.setComentarios(comentarios);

        List<Integer> feedbacks = cliente.getFeedbackDietas();
        feedbacks.add(feedback.getId());

        cliente.setFeedbackDietas(feedbacks);

        this.feedbackDietaService.guardarFeedbackDieta(feedback);
        this.clienteService.guardarCliente(cliente);

        model.addAttribute("cliente", cliente);
        model.addAttribute("dieta", dieta);

       /* List<Integer> comidas = dieta.getComidas();

        Quicksort.quickSortDietas(comidas);

        model.addAttribute("comidas", comidas);
*/
        return "verDietaCustomer";
    }

}