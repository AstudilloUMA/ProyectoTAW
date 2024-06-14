package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.util.List;

@Data
public class Comida {

    private Integer id;
    private String nombre;
    private Integer kilocaloriasTotales;
    private Integer orden;
    private List<Dieta> dietas;
    private List<Menu> menus;

}
