package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.util.List;

@Data
public class Comida {

    private Integer id = -1;
    private String nombre;
    private Integer kilocaloriasTotales;
    private Integer orden;
    private List<Integer> dietas;
    private List<Integer> menus;

}
