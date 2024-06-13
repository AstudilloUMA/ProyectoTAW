package es.uma.proyectogrupo18.dto;
import lombok.*;


@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class FeedbackdietaDTO {

    private Integer id;
    private Integer calificacion;
    private String comentarios;
    private DietaDTO dietaCodigo;
    private ClienteDTO cliente;

}
