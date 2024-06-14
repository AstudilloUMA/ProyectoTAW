package es.uma.proyectogrupo18.dto;
import lombok.*;

@Data
public class Menu {

    private Integer id;
    private Comida comida;
    private String ingredientes;
    private String preparacion;

}
