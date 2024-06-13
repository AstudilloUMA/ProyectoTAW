package es.uma.proyectogrupo18.dto;

import java.time.LocalDate;
import java.util.List;

import es.uma.proyectogrupo18.entity.ClienteEntity;
import es.uma.proyectogrupo18.entity.ComidaEntity;
import es.uma.proyectogrupo18.entity.FeedbackdietaEntity;
import lombok.*;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class DietaDTO {

    private Integer id;
    private String nombre;
    private Integer numComidas;
    private String tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private TrabajadorDTO trabajador;
    private List<ClienteDTO> clientes;
    private List<ComidaDTO> comidas;
    private List<FeedbackDietaDTO> feedbackdietas;

}
