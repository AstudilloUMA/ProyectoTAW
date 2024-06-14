package es.uma.proyectogrupo18.dto;
import lombok.*;


@Data
public class Feedback {

    private Integer id = -1;
    private Integer calificacion;
    private String estadoDelCliente;
    private String comentarios;
    private String series;
    private String peso;
    private String repeticiones;
    private SesionDeEjercicio sesion;
    private Cliente cliente;
    private Trabajador trabajador;


}
