package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class EjercicioDTO {
    private Integer id;
    private TipoEjercicioDTO tipo;
    private String nombre;
    private String video;
    private List<SesionDeEjercicioDTO> sesionDeEjercicios;

}
