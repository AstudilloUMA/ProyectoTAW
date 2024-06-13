package es.uma.proyectogrupo18.dto;

import es.uma.proyectogrupo18.entity.UsuarioEntity;
import lombok.*;


@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class AdministradorDTO {
    private Integer id;
    private UsuarioDTO usuario;


}
