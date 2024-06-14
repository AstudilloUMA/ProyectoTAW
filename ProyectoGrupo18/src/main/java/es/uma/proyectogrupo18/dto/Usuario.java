package es.uma.proyectogrupo18.dto;

import lombok.*;

@Data
public class Usuario {
    private Integer id;
    private String usuario;
    private String nombre;
    private String apellidos;
    private String dni;
    private Integer edad;
    private String sexo;
    private Administrador administrador;
    private Cliente cliente;
    private Trabajador trabajador;
}
