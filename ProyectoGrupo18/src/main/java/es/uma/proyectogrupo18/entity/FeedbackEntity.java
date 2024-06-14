package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.Feedback;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "feedback")
public class FeedbackEntity implements Serializable, DTO<Feedback> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Calificacion")
    private Integer calificacion;

    @Column(name = "Estado_Del_Cliente")
    private String estadoDelCliente;

    @Lob
    @Column(name = "Comentarios")
    private String comentarios;

    @Column(name = "Series")
    private String series;

    @Column(name = "Peso")
    private String peso;

    @Column(name = "Repeticiones")
    private String repeticiones;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Sesion_Id")
    private SesionDeEjercicioEntity sesion;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Cliente_Id")
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Trabajador_Id")
    private TrabajadorEntity trabajador;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getEstadoDelCliente() {
        return estadoDelCliente;
    }

    public void setEstadoDelCliente(String estadoDelCliente) {
        this.estadoDelCliente = estadoDelCliente;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
    }

    public SesionDeEjercicioEntity getSesion() {
        return sesion;
    }

    public void setSesion(SesionDeEjercicioEntity sesion) {
        this.sesion = sesion;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public TrabajadorEntity getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(TrabajadorEntity trabajador) {
        this.trabajador = trabajador;
    }

    public Feedback toDTO(){
        Feedback feedback = new Feedback();
        feedback.setId(this.id);
        feedback.setCalificacion(this.calificacion);
        feedback.setEstadoDelCliente(this.estadoDelCliente);
        feedback.setComentarios(this.comentarios);
        feedback.setSeries(this.series);
        feedback.setPeso(this.peso);
        feedback.setRepeticiones(this.repeticiones);
        feedback.setSesion(this.sesion.toDTO());
        feedback.setCliente(this.cliente.toDTO());
        feedback.setTrabajador(this.trabajador.toDTO());
        return feedback;
    }
}