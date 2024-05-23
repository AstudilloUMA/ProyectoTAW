package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "rol_trabajador", schema = "taw", catalog = "")
public class RolTrabajadorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Rol")
    private String rol;
    @OneToMany(mappedBy = "rolTrabajadorByRolId")
    private Collection<TrabajadorEntity> trabajadorsById;

    @ManyToMany
    @JoinTable(name = "trabajador",
            joinColumns = @JoinColumn(name = "Rol_Id"),
            inverseJoinColumns = @JoinColumn(name = "Usuario_id"))
    private Set<UsuarioEntity> usuarios = new LinkedHashSet<>();

    public Set<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolTrabajadorEntity that = (RolTrabajadorEntity) o;

        if (id != that.id) return false;
        if (rol != null ? !rol.equals(that.rol) : that.rol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rol != null ? rol.hashCode() : 0);
        return result;
    }

    public Collection<TrabajadorEntity> getTrabajadorsById() {
        return trabajadorsById;
    }

    public void setTrabajadorsById(Collection<TrabajadorEntity> trabajadorsById) {
        this.trabajadorsById = trabajadorsById;
    }
}
