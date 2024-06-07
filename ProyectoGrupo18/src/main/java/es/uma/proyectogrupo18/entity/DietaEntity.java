package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "dieta")
public class DietaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Codigo", nullable = false)
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Num_Comidas")
    private Integer numComidas;

    @Column(name = "Tipo")
    private String tipo;

    @Column(name = "Fecha_Inicio")
    private LocalDate fechaInicio;

    @Column(name = "Fecha_Fin")
    private LocalDate fechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Trabajador_Id")
    private TrabajadorEntity trabajador;

    @OneToMany(mappedBy = "dietaCodigo")
    private Set<ClienteEntity> clientes = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "dieta_comida",
            joinColumns = @JoinColumn(name = "Dieta_Codigo"),
            inverseJoinColumns = @JoinColumn(name = "Comida_Id"))
    private Set<ComidaEntity> comidas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "dietaCodigo")
    private Set<FeedbackdietaEntity> feedbackdietas = new LinkedHashSet<>();

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

    public Integer getNumComidas() {
        return numComidas;
    }

    public void setNumComidas(Integer numComidas) {
        this.numComidas = numComidas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Set<ComidaEntity> getComidas() {
        return comidas;
    }

    public void setComidas(Set<ComidaEntity> comidas) {
        this.comidas = comidas;
    }

    public Set<FeedbackdietaEntity> getFeedbackdietas() {
        return feedbackdietas;
    }

    public void setFeedbackdietas(Set<FeedbackdietaEntity> feedbackdietas) {
        this.feedbackdietas = feedbackdietas;
    }

}