package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.util.List;

@Data
public class Trabajador {
    private Integer id;
    //private UsuarioUI usuario;
    private RolTrabajador rol;
    private List<Integer> clientesDietista;
    private List<Integer> clientesEntrenador;
    private List<Integer> dietas;
    private List<Integer> feedbacks;
    private List<Integer> rutinaSemanal;
    private List<Integer> sesionDeEjercicios;
}
