package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "sesion_de_ejercicio", schema = "taw")
public class SesionDeEjercicioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;

    @Basic
    @Column(name = "Repeticiones")
    private Integer repeticiones;

    @Basic
    @Column(name = "Cantidad")
    private Integer cantidad;

    @Basic
    @Column(name = "Orden")
    private Integer orden;

    @Basic
    @Column(name = "Ejercicio_Id", insertable = false, updatable = false)
    private Integer ejercicioId;

    @OneToMany(mappedBy = "sesionDeEjercicioBySesionDeEjercicioId")
    private Collection<EntrenamientoEjercicioEntity> entrenamientoEjerciciosById;

    @ManyToOne
    @JoinColumn(name = "Ejercicio_Id", referencedColumnName = "Id")
    private EjercicioEntity ejercicioByEjercicioId;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(Integer ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SesionDeEjercicioEntity that = (SesionDeEjercicioEntity) o;

        if (id != that.id) return false;
        if (repeticiones != null ? !repeticiones.equals(that.repeticiones) : that.repeticiones != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (orden != null ? !orden.equals(that.orden) : that.orden != null) return false;
        if (ejercicioId != null ? !ejercicioId.equals(that.ejercicioId) : that.ejercicioId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (repeticiones != null ? repeticiones.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (orden != null ? orden.hashCode() : 0);
        result = 31 * result + (ejercicioId != null ? ejercicioId.hashCode() : 0);
        return result;
    }

    public Collection<EntrenamientoEjercicioEntity> getEntrenamientoEjerciciosById() {
        return entrenamientoEjerciciosById;
    }

    public void setEntrenamientoEjerciciosById(Collection<EntrenamientoEjercicioEntity> entrenamientoEjerciciosById) {
        this.entrenamientoEjerciciosById = entrenamientoEjerciciosById;
    }

    public EjercicioEntity getEjercicioByEjercicioId() {
        return ejercicioByEjercicioId;
    }

    public void setEjercicioByEjercicioId(EjercicioEntity ejercicioByEjercicioId) {
        this.ejercicioByEjercicioId = ejercicioByEjercicioId;
    }
}
