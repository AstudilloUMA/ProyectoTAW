package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.Cliente;
import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.Trabajador;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Entity
@Table(name = "cliente")
public class ClienteEntity implements Serializable, DTO<Cliente> {
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
    private List<FeedbackEntity> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<FeedbackdietaEntity> feedbackdietas = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<SesionDeEjercicioEntity> sesionDeEjercicios = new ArrayList<>();

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

    public List<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<FeedbackdietaEntity> getFeedbackdietas() {
        return feedbackdietas;
    }

    public void setFeedbackdietas(List<FeedbackdietaEntity> feedbackdietas) {
        this.feedbackdietas = feedbackdietas;
    }

    public List<SesionDeEjercicioEntity> getSesionDeEjercicios() {
        return sesionDeEjercicios;
    }

    public void setSesionDeEjercicios(List<SesionDeEjercicioEntity> sesionDeEjercicios) {
        this.sesionDeEjercicios = sesionDeEjercicios;
    }

    public Cliente toDTO() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setUsuario(this.usuario.simpletoDTO());
        cliente.setPeso(this.peso);
        cliente.setAltura(this.altura);
        cliente.setEdad(this.edad);
        cliente.setRutinaSemanal(this.rutina != null ? this.rutina.toDTO() : null);
        cliente.setDieta(this.dietaCodigo.toDTO());
        cliente.setDietista(this.dietista.toDTO());
        cliente.setEntrenador(this.entrenador.toDTO());

        List<Integer> feedbackIds = new ArrayList<>();
        this.feedbacks.forEach(feedback -> feedbackIds.add(feedback.getId()));
        cliente.setFeedbacks(feedbackIds);

        List<Integer> feedbackDietaIds = new ArrayList<>();
        this.feedbackdietas.forEach(feedbackDieta -> feedbackDietaIds.add(feedbackDieta.getId()));
        cliente.setFeedbackDietas(feedbackDietaIds);

        List<Integer> sesionIds = new ArrayList<>();
        this.sesionDeEjercicios.forEach(sesion -> sesionIds.add(sesion.getId()));
        cliente.setSesionDeEjercicios(sesionIds);

        return cliente;
    }

}