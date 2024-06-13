package es.uma.proyectogrupo18.dto;


import es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity;
import es.uma.proyectogrupo18.entity.TipoEjercicioEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class EjercicioDTO {
    private Integer id;
    private TipoEjercicioEntity tipo;
    private String nombre;
    private String video;
    private List<SesionDeEjercicioEntity> sesionDeEjercicios = new ArrayList<>();

}
