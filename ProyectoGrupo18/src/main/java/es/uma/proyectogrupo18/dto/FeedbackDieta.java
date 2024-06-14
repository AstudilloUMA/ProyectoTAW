package es.uma.proyectogrupo18.dto;
import lombok.*;


@Data
public class FeedbackDieta {

    private Integer id;
    private Integer calificacion;
    private String comentarios;
    private Dieta dietaCodigo;
    private Cliente cliente;

}
