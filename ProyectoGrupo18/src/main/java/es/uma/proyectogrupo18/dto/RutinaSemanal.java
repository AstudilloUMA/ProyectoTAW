package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
public class RutinaSemanal {

    private Integer id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Trabajador trabajador;
    private List<Integer> clientes;
    private List<Integer> sesionesDeEjercicio;
}
