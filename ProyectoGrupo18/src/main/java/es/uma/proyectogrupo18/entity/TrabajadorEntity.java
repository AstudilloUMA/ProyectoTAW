package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "trabajador")
public class TrabajadorEntity {
    @Id
    @Column(name = "Usuario_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Rol_Id")
    private RolTrabajadorEntity rol;

    @OneToMany(mappedBy = "dietista")
    private Set<ClienteEntity> clientesDietista = new LinkedHashSet<>();

    @OneToMany(mappedBy = "entrenador")
    private Set<ClienteEntity> clientesEntrenador = new LinkedHashSet<>();

    @OneToMany(mappedBy = "trabajador")
    private Set<DietaEntity> dietas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "trabajador")
    private Set<FeedbackEntity> feedbacks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "trabajador")
    private Set<RutinaSemanalEntity> rutinaSemanals = new LinkedHashSet<>();

    @OneToMany(mappedBy = "trabajador")
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

    public RolTrabajadorEntity getRol() {
        return rol;
    }

    public void setRol(RolTrabajadorEntity rol) {
        this.rol = rol;
    }

    public Set<ClienteEntity> getClientesDietista() {
        return clientesDietista;
    }

    public void setClientesDietista(Set<ClienteEntity> clientesDietista) {
        this.clientesDietista = clientesDietista;
    }

    public Set<ClienteEntity> getClientesEntrenador() {
        return clientesEntrenador;
    }

    public void setClientesEntrenador(Set<ClienteEntity> clientesEntrenador) {
        this.clientesEntrenador = clientesEntrenador;
    }

    public Set<DietaEntity> getDietas() {
        return dietas;
    }

    public void setDietas(Set<DietaEntity> dietas) {
        this.dietas = dietas;
    }

    public Set<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }

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

}