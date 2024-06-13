package es.uma.proyectogrupo18.dto;
import lombok.*;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class MenuDTO {

    private Integer id;
    private ComidaDTO comida;
    private String ingredientes;
    private String preparacion;

}
