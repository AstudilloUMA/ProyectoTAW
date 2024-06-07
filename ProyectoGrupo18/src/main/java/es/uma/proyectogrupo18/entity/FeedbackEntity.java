package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "feedback")
public class FeedbackEntity {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Ejercicio_Id")
    private EjercicioEntity ejercicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Cliente_Id")
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Trabajador_Id")
    private TrabajadorEntity trabajador;

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

    public EjercicioEntity getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(EjercicioEntity ejercicio) {
        this.ejercicio = ejercicio;
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

}