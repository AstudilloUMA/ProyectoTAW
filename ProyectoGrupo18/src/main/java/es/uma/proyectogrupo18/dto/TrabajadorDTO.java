package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class TrabajadorDTO {
    private Integer id;
    private UsuarioDTO usuario;
    private RolTrabajadorDTO rol;
    private List<ClienteDTO> clientes;
    private List<DietaDTO> dietas;
    private List<FeedbackDTO> feedbacks;
    private List<RutinaSemanalDTO> rutinaSemanal;
    private List<SesionDeEjercicioDTO> sesionDeEjercicios;
}
