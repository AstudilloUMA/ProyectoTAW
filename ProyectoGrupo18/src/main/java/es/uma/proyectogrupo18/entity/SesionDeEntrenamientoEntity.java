package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "entrenamiento_ejercicio",
            joinColumns = @JoinColumn(name = "Sesion_de_Entrenamiento_Id"),
            inverseJoinColumns = @JoinColumn(name = "Sesion_de_Ejercicio_Id"))
    private Set<SesionDeEjercicioEntity> sesionDeEjercicios = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "rutina_semanal_entrenamiento",
            joinColumns = @JoinColumn(name = "Sesion_de_Entrenamiento_Id"),
            inverseJoinColumns = @JoinColumn(name = "Rutina_Semanal_Id"))
    private Set<RutinaSemanalEntity> rutinaSemanals = new LinkedHashSet<>();

    public Set<RutinaSemanalEntity> getRutinaSemanals() {
        return rutinaSemanals;
    }

    public void setRutinaSemanals(Set<RutinaSemanalEntity> rutinaSemanals) {
        this.rutinaSemanals = rutinaSemanals;
    }

    public Set<SesionDeEjercicioEntity> getSesionDeEjercicios() {
        return sesionDeEjercicios;
    }

    public void setSesionDeEjercicios(Set<SesionDeEjercicioEntity> sesionDeEjercicios) {
        this.sesionDeEjercicios = sesionDeEjercicios;
    }

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

    public int diaToInt(){
        return switch (this.dia) {
            case "Lunes" -> 1;
            case "Martes" -> 2;
            case "Miércoles" -> 3;
            case "Jueves" -> 4;
            case "Viernes" -> 5;
            case "Sábado" -> 6;
            case "Domingo" -> 7;
            default -> 0;
        };
    }
}
