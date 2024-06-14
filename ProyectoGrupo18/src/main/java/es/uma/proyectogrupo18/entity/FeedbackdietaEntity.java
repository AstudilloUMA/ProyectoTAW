package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.FeedbackDieta;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "feedbackdieta")
public class FeedbackdietaEntity implements Serializable, DTO<FeedbackDieta> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Calificacion")
    private Integer calificacion;

    @Lob
    @Column(name = "Comentarios")
    private String comentarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Dieta_Codigo")
    private DietaEntity dietaCodigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Cliente_Id")
    private ClienteEntity cliente;

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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public DietaEntity getDietaCodigo() {
        return dietaCodigo;
    }

    public void setDietaCodigo(DietaEntity dietaCodigo) {
        this.dietaCodigo = dietaCodigo;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public FeedbackDieta toDTO(){
        FeedbackDieta feedbackDieta = new FeedbackDieta();
        feedbackDieta.setId(this.id);
        feedbackDieta.setCalificacion(this.calificacion);
        feedbackDieta.setComentarios(this.comentarios);
        feedbackDieta.setDietaCodigo(this.dietaCodigo.toDTO());
        feedbackDieta.setCliente(this.cliente.toDTO());
        return feedbackDieta;
    }
}