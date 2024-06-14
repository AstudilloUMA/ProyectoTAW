package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.time.LocalDate;


@Data
public class SesionDeEjercicio {
    private Integer id = -1;
    private LocalDate fecha;
    private String dia;
    private String repeticiones;
    private String cantidad;
    private Integer orden;
    private String peso;
    private Ejercicio ejercicio;
    private Trabajador trabajador;
    private Cliente cliente;
    private RutinaSemanal rutina;

}
