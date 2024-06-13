package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class ComidaDTO {

    private Integer id;
    private String nombre;
    private Integer kilocaloriasTotales;
    private Integer orden;
    private List<DietaDTO> dietas;
    private List<MenuDTO> menus;

}
