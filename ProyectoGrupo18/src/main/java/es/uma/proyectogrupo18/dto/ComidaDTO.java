package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.util.Set;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class ComidaDTO {

    private Integer id;
    private String nombre;
    private Integer kilocaloriasTotales;
    private Integer orden;
    private Set<DietaDTO> dietas;
    private Set<MenuDTO> menus;

}
