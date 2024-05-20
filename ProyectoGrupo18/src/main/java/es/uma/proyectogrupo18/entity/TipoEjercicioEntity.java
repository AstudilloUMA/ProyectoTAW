package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "tipo_ejercicio", schema = "taw", catalog = "")
public class TipoEjercicioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Tipo")
    private String tipo;
    @OneToMany(mappedBy = "tipoEjercicioByTipoId")
    private Collection<EjercicioEntity> ejerciciosById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoEjercicioEntity that = (TipoEjercicioEntity) o;

        if (id != that.id) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }

    public Collection<EjercicioEntity> getEjerciciosById() {
        return ejerciciosById;
    }

    public void setEjerciciosById(Collection<EjercicioEntity> ejerciciosById) {
        this.ejerciciosById = ejerciciosById;
    }
}
