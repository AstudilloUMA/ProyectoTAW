package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.util.Set;

@Data
public class Ejercicio {
    private Integer id;
    private TipoEjercicio tipo;
    private String nombre;
    private String video;
    private Set<SesionDeEjercicio> sesionDeEjercicios;

}
