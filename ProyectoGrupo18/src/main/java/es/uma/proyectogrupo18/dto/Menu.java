package es.uma.proyectogrupo18.dto;
import lombok.*;

@Data
public class Menu {

    private Integer id = -1;
    private Comida comida;
    private String ingredientes;
    private String preparacion;

}
