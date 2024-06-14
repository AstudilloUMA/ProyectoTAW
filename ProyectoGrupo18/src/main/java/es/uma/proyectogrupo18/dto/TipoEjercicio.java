package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.util.Set;


@Data
public class TipoEjercicio {
    private Integer id;
    private String tipo;
    private Set<Ejercicio> ejercicios;

}
