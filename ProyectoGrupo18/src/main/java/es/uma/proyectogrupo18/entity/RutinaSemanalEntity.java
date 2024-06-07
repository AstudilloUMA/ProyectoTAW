package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "rutina_semanal")
public class RutinaSemanalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Fecha_Inicio")
    private LocalDate fechaInicio;

    @Column(name = "Fecha_Fin")
    private LocalDate fechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Trabajador_Id")
    private TrabajadorEntity trabajador;

    @OneToMany(mappedBy = "rutina")
    private Set<ClienteEntity> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "rutina")
    private Set<SesionDeEjercicioEntity> sesionDeEjercicios = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public TrabajadorEntity getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(TrabajadorEntity trabajador) {
        this.trabajador = trabajador;
    }

    public Set<ClienteEntity> getClientes() {
        return clientes;
    }

    public void setClientes(Set<ClienteEntity> clientes) {
        this.clientes = clientes;
    }

    public Set<SesionDeEjercicioEntity> getSesionDeEjercicios() {
        return sesionDeEjercicios;
    }

    public void setSesionDeEjercicios(Set<SesionDeEjercicioEntity> sesionDeEjercicios) {
        this.sesionDeEjercicios = sesionDeEjercicios;
    }

}