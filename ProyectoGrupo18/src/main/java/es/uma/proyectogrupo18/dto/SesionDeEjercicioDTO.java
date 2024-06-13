package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.time.LocalDate;


@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class SesionDeEjercicioDTO {
    private Integer id;
    private LocalDate fecha;
    private String dia;
    private String repeticiones;
    private String cantidad;
    private Integer orden;
    private String peso;
    private EjercicioDTO ejercicio;
    private TrabajadorDTO trabajador;
    private ClienteDTO cliente;
    private RutinaSemanalDTO rutina;

}
