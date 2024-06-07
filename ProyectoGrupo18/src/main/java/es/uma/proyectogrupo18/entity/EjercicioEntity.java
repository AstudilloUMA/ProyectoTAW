package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ejercicio")
public class EjercicioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Tipo_Id")
    private TipoEjercicioEntity tipo;

    @Column(name = "Nombre")
    private String nombre;

    @Lob
    @Column(name = "Video")
    private String video;

    @OneToMany(mappedBy = "ejercicio")
    private Set<FeedbackEntity> feedbacks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "ejercicio")
    private Set<SesionDeEjercicioEntity> sesionDeEjercicios = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoEjercicioEntity getTipo() {
        return tipo;
    }

    public void setTipo(TipoEjercicioEntity tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Set<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Set<SesionDeEjercicioEntity> getSesionDeEjercicios() {
        return sesionDeEjercicios;
    }

    public void setSesionDeEjercicios(Set<SesionDeEjercicioEntity> sesionDeEjercicios) {
        this.sesionDeEjercicios = sesionDeEjercicios;
    }

}