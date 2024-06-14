package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.Trabajador;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trabajador")
public class TrabajadorEntity implements Serializable, DTO<Trabajador> {
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
    private List<ClienteEntity> clientesDietista = new ArrayList<>();

    @OneToMany(mappedBy = "entrenador")
    private List<ClienteEntity> clientesEntrenador = new ArrayList<>();

    @OneToMany(mappedBy = "trabajador")
    private List<DietaEntity> dietas = new ArrayList<>();

    @OneToMany(mappedBy = "trabajador")
    private List<FeedbackEntity> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "trabajador")
    private List<RutinaSemanalEntity> rutinaSemanals = new ArrayList<>();

    @OneToMany(mappedBy = "trabajador")
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

    public RolTrabajadorEntity getRol() {
        return rol;
    }

    public void setRol(RolTrabajadorEntity rol) {
        this.rol = rol;
    }

    public List<ClienteEntity> getClientesDietista() {
        return clientesDietista;
    }

    public void setClientesDietista(List<ClienteEntity> clientesDietista) {
        this.clientesDietista = clientesDietista;
    }

    public List<ClienteEntity> getClientesEntrenador() {
        return clientesEntrenador;
    }

    public void setClientesEntrenador(List<ClienteEntity> clientesEntrenador) {
        this.clientesEntrenador = clientesEntrenador;
    }

    public List<DietaEntity> getDietas() {
        return dietas;
    }

    public void setDietas(List<DietaEntity> dietas) {
        this.dietas = dietas;
    }

    public List<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<RutinaSemanalEntity> getRutinaSemanals() {
        return rutinaSemanals;
    }

    public void setRutinaSemanals(List<RutinaSemanalEntity> rutinaSemanals) {
        this.rutinaSemanals = rutinaSemanals;
    }

    public List<SesionDeEjercicioEntity> getSesionDeEjercicios() {
        return sesionDeEjercicios;
    }

    public void setSesionDeEjercicios(List<SesionDeEjercicioEntity> sesionDeEjercicios) {
        this.sesionDeEjercicios = sesionDeEjercicios;
    }

    public Trabajador toDTO() {
        Trabajador trabajador = new Trabajador();
        trabajador.setId(this.id);
        trabajador.setUsuario(this.usuario.toDTO());
        trabajador.setRol(this.rol.toDTO());

        List<Integer> listaClientesDietista = new ArrayList<>();
        this.clientesDietista.forEach(cliente -> listaClientesDietista.add(cliente.getId()));
        trabajador.setClientesDietista(listaClientesDietista);

        List<Integer> listaClientesEntrenador = new ArrayList<>();
        this.clientesEntrenador.forEach(cliente -> listaClientesEntrenador.add(cliente.getId()));
        trabajador.setClientesEntrenador(listaClientesEntrenador);

        List<Integer> listaDietas = new ArrayList<>();
        this.dietas.forEach(dieta -> listaDietas.add(dieta.getId()));
        trabajador.setDietas(listaDietas);

        List<Integer> listaFeedbacks = new ArrayList<>();
        this.feedbacks.forEach(feedback -> listaFeedbacks.add(feedback.getId()));
        trabajador.setFeedbacks(listaFeedbacks);

        List<Integer> listaRutinas = new ArrayList<>();
        this.rutinaSemanals.forEach(rutina -> listaRutinas.add(rutina.getId()));
        trabajador.setRutinaSemanal(listaRutinas);

        List<Integer> listaSesiones = new ArrayList<>();
        this.sesionDeEjercicios.forEach(sesion -> listaSesiones.add(sesion.getId()));
        trabajador.setSesionDeEjercicios(listaSesiones);

        return trabajador;
    }

}