package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador", schema = "taw", catalog = "")
public class AdministradorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Usuario_id")
    private int usuarioId;
    @OneToOne
    @JoinColumn(name = "Usuario_id", referencedColumnName = "Id", nullable = false)
    private UsuarioEntity usuarioByUsuarioId;

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministradorEntity that = (AdministradorEntity) o;

        if (usuarioId != that.usuarioId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return usuarioId;
    }

    public UsuarioEntity getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(UsuarioEntity usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }
}
