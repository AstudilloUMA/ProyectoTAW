package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.util.List;


@Data
public class TipoEjercicio {
    private Integer id;
    private String tipo;
    private List<Integer> ejercicios;
}
