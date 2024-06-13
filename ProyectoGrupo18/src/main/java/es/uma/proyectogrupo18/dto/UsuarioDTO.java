package es.uma.proyectogrupo18.dto;

import lombok.*;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class UsuarioDTO {
    private Integer id;
    private String usuario;
    private String nombre;
    private String apellidos;
    private String dni;
    private Integer edad;
    private String sexo;
    private AdministradorDTO administrador;
    private ClienteDTO cliente;
    private TrabajadorDTO trabajador;
}
