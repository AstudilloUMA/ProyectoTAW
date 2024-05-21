package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "sesion_de_entrenamiento", schema = "taw", catalog = "")
public class SesionDeEntrenamientoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Fecha")
    private Date fecha;
    @Basic
    @Column(name = "Dia")
    private String dia;
    @Basic
    @Column(name = "ClienteId")
    private Integer clienteId;
    @Basic
    @Column(name = "Trabajador_Id",insertable = false, updatable = false)
    private Integer trabajadorId;
    @OneToMany(mappedBy = "sesionDeEntrenamientoBySesionDeEntrenamientoId")
    private Collection<EntrenamientoEjercicioEntity> entrenamientoEjerciciosById;
    @OneToMany(mappedBy = "sesionDeEntrenamientoBySesionDeEntrenamientoId")
    private Collection<RutinaSemanalEntrenamientoEntity> rutinaSemanalEntrenamientosById;
    @ManyToOne
    @JoinColumn(name = "Trabajador_Id", referencedColumnName = "Usuario_id", insertable = false, updatable = false)
    private TrabajadorEntity trabajadorByTrabajadorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(Integer trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SesionDeEntrenamientoEntity that = (SesionDeEntrenamientoEntity) o;

        if (id != that.id) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (dia != null ? !dia.equals(that.dia) : that.dia != null) return false;
        if (clienteId != null ? !clienteId.equals(that.clienteId) : that.clienteId != null) return false;
        if (trabajadorId != null ? !trabajadorId.equals(that.trabajadorId) : that.trabajadorId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (dia != null ? dia.hashCode() : 0);
        result = 31 * result + (clienteId != null ? clienteId.hashCode() : 0);
        result = 31 * result + (trabajadorId != null ? trabajadorId.hashCode() : 0);
        return result;
    }

    public Collection<EntrenamientoEjercicioEntity> getEntrenamientoEjerciciosById() {
        return entrenamientoEjerciciosById;
    }

    public void setEntrenamientoEjerciciosById(Collection<EntrenamientoEjercicioEntity> entrenamientoEjerciciosById) {
        this.entrenamientoEjerciciosById = entrenamientoEjerciciosById;
    }

    public Collection<RutinaSemanalEntrenamientoEntity> getRutinaSemanalEntrenamientosById() {
        return rutinaSemanalEntrenamientosById;
    }

    public void setRutinaSemanalEntrenamientosById(Collection<RutinaSemanalEntrenamientoEntity> rutinaSemanalEntrenamientosById) {
        this.rutinaSemanalEntrenamientosById = rutinaSemanalEntrenamientosById;
    }

    public TrabajadorEntity getTrabajadorByTrabajadorId() {
        return trabajadorByTrabajadorId;
    }

    public void setTrabajadorByTrabajadorId(TrabajadorEntity trabajadorByTrabajadorId) {
        this.trabajadorByTrabajadorId = trabajadorByTrabajadorId;
    }
}
