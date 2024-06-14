package es.uma.proyectogrupo18.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Data
public class Dieta {

    private Integer id;
    private String nombre;
    private Integer numComidas;
    private String tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Trabajador trabajador;
    private List<Cliente> clientes;
    private List<Comida> comidas;
    private List<FeedbackDieta> feedbackdietas;

}
