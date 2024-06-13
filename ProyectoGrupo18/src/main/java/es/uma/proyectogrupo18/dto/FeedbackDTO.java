package es.uma.proyectogrupo18.dto;
import lombok.*;

import java.util.List;


@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class FeedbackDTO {

    private Integer id;
    private Integer calificacion;
    private String estadoDelCliente;
    private String comentarios;
    private String series;
    private String peso;
    private String repeticiones;
    private SesionDeEjercicioDTO sesionDeEjercicios;
    private ClienteDTO cliente;
    private TrabajadorDTO trabajador;


}
