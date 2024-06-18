package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.Administrador;
import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.Trabajador;
import es.uma.proyectogrupo18.dto.Usuario;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "administrador")
public class AdministradorEntity implements Serializable, DTO<Administrador> {
    @Id
    @Column(name = "Usuario_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Usuario_id", nullable = false)
    private UsuarioEntity usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Administrador toDTO() {
        Administrador administrador = new Administrador();
        administrador.setId(this.id);
        Usuario usuario = this.usuario.simpletoDTO();
        usuario.setAdministrador(administrador);
        administrador.setUsuario(usuario);
        return administrador;
    }

}