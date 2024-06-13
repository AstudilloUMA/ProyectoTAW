package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class ClienteDTO {
    private Integer id;
    private UsuarioDTO usuario;
    private BigDecimal peso;
    private BigDecimal altura;
    private Integer edad;
    private RutinaSemanalDTO rutinaSemanal;
    private DietaDTO dieta;
    private TrabajadorDTO dietista;
    private TrabajadorDTO entrenador;
    private Set<FeedbackDTO> feedbacks;
    private Set<FeedbackDietaDTO> feedbackDietas;
    private Set<SesionDeEjercicioDTO> sesionDeEjercicios;
}
