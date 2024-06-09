package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class ClienteEntity {
    @Id
    @Column(name = "Usuario_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @Column(name = "Peso", precision = 5, scale = 2)
    private BigDecimal peso;

    @Column(name = "Altura", precision = 5, scale = 2)
    private BigDecimal altura;

    @Column(name = "Edad")
    private Integer edad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Rutina_Id")
    private RutinaSemanalEntity rutina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Dieta_Codigo")
    private DietaEntity dietaCodigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Dietista_Id")
    private TrabajadorEntity dietista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Entrenador_Id")
    private TrabajadorEntity entrenador;

    @OneToMany(mappedBy = "cliente")
    private Set<FeedbackEntity> feedbacks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<FeedbackdietaEntity> feedbackdietas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<SesionDeEjercicioEntity> sesionDeEjercicios = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public RutinaSemanalEntity getRutina() {
        return rutina;
    }

    public void setRutina(RutinaSemanalEntity rutina) {
        this.rutina = rutina;
    }

    public DietaEntity getDietaCodigo() {
        return dietaCodigo;
    }

    public void setDietaCodigo(DietaEntity dietaCodigo) {
        this.dietaCodigo = dietaCodigo;
    }

    public TrabajadorEntity getDietista() {
        return dietista;
    }

    public void setDietista(TrabajadorEntity dietista) {
        this.dietista = dietista;
    }

    public TrabajadorEntity getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(TrabajadorEntity entrenador) {
        this.entrenador = entrenador;
    }

    public Set<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Set<FeedbackdietaEntity> getFeedbackdietas() {
        return feedbackdietas;
    }

    public void setFeedbackdietas(Set<FeedbackdietaEntity> feedbackdietas) {
        this.feedbackdietas = feedbackdietas;
    }

    public Set<SesionDeEjercicioEntity> getSesionDeEjercicios() {
        return sesionDeEjercicios;
    }

    public void setSesionDeEjercicios(Set<SesionDeEjercicioEntity> sesionDeEjercicios) {
        this.sesionDeEjercicios = sesionDeEjercicios;
    }

}