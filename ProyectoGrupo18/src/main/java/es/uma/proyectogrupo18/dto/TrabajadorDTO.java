package es.uma.proyectogrupo18.dto;

import lombok.*;

import java.util.Set;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class TrabajadorDTO {
    private Integer id;
    private UsuarioDTO usuario;
    private RolTrabajadorDTO rol;
    private Set<ClienteDTO> clientesDietista;
    private Set<ClienteDTO> clientesEntrenador;
    private Set<DietaDTO> dietas;
    private Set<FeedbackDTO> feedbacks;
    private Set<RutinaSemanalDTO> rutinaSemanals;
    private Set<SesionDeEjercicioDTO> sesionDeEjercicios;
}
