package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rutina_semanal", schema = "taw", catalog = "")
public class RutinaSemanalEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Fecha_Inicio")
    private Date fechaInicio;
    @Basic
    @Column(name = "Fecha_Fin")
    private Date fechaFin;
    @Basic
    @Column(name = "ClienteId")
    private Integer clienteId;
    @Basic
    @Column(name = "Trabajador_Id", insertable = false, updatable = false)
    private Integer trabajadorId;
    @OneToMany(mappedBy = "rutinaSemanalByRutinaId")
    private Collection<ClienteEntity> clientesById;
    @ManyToOne
    @JoinColumn(name = "Trabajador_Id", referencedColumnName = "Usuario_id", insertable = false, updatable = false)
    private TrabajadorEntity trabajadorByTrabajadorId;
    @OneToMany(mappedBy = "rutinaSemanalByRutinaSemanalId")
    private Collection<RutinaSemanalEntrenamientoEntity> rutinaSemanalEntrenamientosById;

    @Column(name = "Nombre")
    private String nombre;

    @ManyToMany
    @JoinTable(name = "rutina_semanal_entrenamiento",
            joinColumns = @JoinColumn(name = "Rutina_Semanal_Id"),
            inverseJoinColumns = @JoinColumn(name = "Sesion_de_Entrenamiento_Id"))
    private Set<SesionDeEntrenamientoEntity> sesionDeEntrenamientos = new LinkedHashSet<>();

    public Set<SesionDeEntrenamientoEntity> getSesionDeEntrenamientos() {
        return sesionDeEntrenamientos;
    }

    public void setSesionDeEntrenamientos(Set<SesionDeEntrenamientoEntity> sesionDeEntrenamientos) {
        this.sesionDeEntrenamientos = sesionDeEntrenamientos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

        RutinaSemanalEntity that = (RutinaSemanalEntity) o;

        if (id != that.id) return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;
        if (fechaFin != null ? !fechaFin.equals(that.fechaFin) : that.fechaFin != null) return false;
        if (clienteId != null ? !clienteId.equals(that.clienteId) : that.clienteId != null) return false;
        if (trabajadorId != null ? !trabajadorId.equals(that.trabajadorId) : that.trabajadorId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaFin != null ? fechaFin.hashCode() : 0);
        result = 31 * result + (clienteId != null ? clienteId.hashCode() : 0);
        result = 31 * result + (trabajadorId != null ? trabajadorId.hashCode() : 0);
        return result;
    }

    public Collection<ClienteEntity> getClientesById() {
        return clientesById;
    }

    public void setClientesById(Collection<ClienteEntity> clientesById) {
        this.clientesById = clientesById;
    }

    public TrabajadorEntity getTrabajadorByTrabajadorId() {
        return trabajadorByTrabajadorId;
    }

    public void setTrabajadorByTrabajadorId(TrabajadorEntity trabajadorByTrabajadorId) {
        this.trabajadorByTrabajadorId = trabajadorByTrabajadorId;
    }

    public void anyadirClienteById(ClienteEntity cliente) {
        this.clientesById.add(cliente);
    }

    public Collection<RutinaSemanalEntrenamientoEntity> getRutinaSemanalEntrenamientosById() {
        return rutinaSemanalEntrenamientosById;
    }

    public void setRutinaSemanalEntrenamientosById(Collection<RutinaSemanalEntrenamientoEntity> rutinaSemanalEntrenamientosById) {
        this.rutinaSemanalEntrenamientosById = rutinaSemanalEntrenamientosById;
    }
}
