package es.uma.proyectogrupo18.dto;

import es.uma.proyectogrupo18.dto.EjercicioDTO;
import es.uma.proyectogrupo18.entity.EjercicioEntity;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;


@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class TipoEjercicioDTO {
    private Integer id;
    private String tipo;
    private Set<EjercicioDTO> ejercicios;

}
