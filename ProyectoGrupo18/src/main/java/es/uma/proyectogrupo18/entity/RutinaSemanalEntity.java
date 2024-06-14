package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.RolTrabajador;
import es.uma.proyectogrupo18.dto.RutinaSemanal;
import es.uma.proyectogrupo18.dto.Trabajador;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rutina_semanal")
public class RutinaSemanalEntity implements Serializable, DTO<RutinaSemanal> {
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
    private List<ClienteEntity> clientes = new ArrayList<>();

    @OneToMany(mappedBy = "rutina")
    private List<SesionDeEjercicioEntity> sesionDeEjercicios = new ArrayList<>();

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

    public List<ClienteEntity> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteEntity> clientes) {
        this.clientes = clientes;
    }

    public List<SesionDeEjercicioEntity> getSesionDeEjercicios() {
        return sesionDeEjercicios;
    }

    public void setSesionDeEjercicios(List<SesionDeEjercicioEntity> sesionDeEjercicios) {
        this.sesionDeEjercicios = sesionDeEjercicios;
    }

    public RutinaSemanal toDTO() {
        RutinaSemanal rutinaSemanal = new RutinaSemanal();

        rutinaSemanal.setId(this.id);
        rutinaSemanal.setNombre(this.nombre);
        rutinaSemanal.setFechaInicio(this.fechaInicio);
        rutinaSemanal.setFechaFin(this.fechaFin);
        rutinaSemanal.setTrabajador(this.trabajador.toDTO());

        List<Integer> listaClientes = new ArrayList<>();
        this.clientes.forEach(cliente -> listaClientes.add(cliente.getId()));
        rutinaSemanal.setClientes(listaClientes);

        List<Integer> listaSesiones = new ArrayList<>();
        this.sesionDeEjercicios.forEach(sesion -> listaSesiones.add(sesion.getId()));
        rutinaSemanal.setSesionesDeEjercicio(listaSesiones);

        return rutinaSemanal;
    }
}