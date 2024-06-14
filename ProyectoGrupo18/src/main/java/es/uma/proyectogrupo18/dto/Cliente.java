package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cliente {
    private Integer id;
    private Usuario usuario;
    private BigDecimal peso;
    private BigDecimal altura;
    private Integer edad;
    private RutinaSemanal rutinaSemanal;
    private Dieta dieta;
    private Trabajador dietista;
    private Trabajador entrenador;
    private List<Feedback> feedbacks;
    private List<FeedbackDieta> feedbackDietas;
    private List<SesionDeEjercicio> sesionDeEjercicios;
}
