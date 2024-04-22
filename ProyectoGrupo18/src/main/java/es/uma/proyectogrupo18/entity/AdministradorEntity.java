package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador", schema = "taw", catalog = "")
public class AdministradorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UsuarioId")
    private int usuarioId;

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
}
