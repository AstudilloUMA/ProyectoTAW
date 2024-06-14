package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.RolTrabajador;
import es.uma.proyectogrupo18.dto.SesionDeEjercicio;
import es.uma.proyectogrupo18.dto.Trabajador;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sesion_de_ejercicio")
public class SesionDeEjercicioEntity implements Serializable, DTO<SesionDeEjercicio> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Fecha")
    private LocalDate fecha;

    @Column(name = "Dia")
    private String dia;

    @Column(name = "Repeticiones")
    private String repeticiones;

    @Column(name = "Cantidad")
    private String cantidad;

    @Column(name = "Orden")
    private Integer orden;

    @Column(name = "Peso")
    private String peso;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Ejercicio_Id")
    private EjercicioEntity ejercicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Trabajador_Id")
    private TrabajadorEntity trabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Cliente_Id", nullable = true)
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Rutina_Id")
    private RutinaSemanalEntity rutina;

    // Getters y Setters existentes
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getPeso() {return peso;}

    public void setPeso(String peso) {this.peso = peso;}

    public EjercicioEntity getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(EjercicioEntity ejercicio) {
        this.ejercicio = ejercicio;
    }

    public TrabajadorEntity getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(TrabajadorEntity trabajador) {
        this.trabajador = trabajador;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public RutinaSemanalEntity getRutina() {
        return rutina;
    }

    public void setRutina(RutinaSemanalEntity rutina) {
        this.rutina = rutina;
    }

    public SesionDeEjercicio toDTO() {
        SesionDeEjercicio sesionDeEjercicio = new SesionDeEjercicio();
        sesionDeEjercicio.setId(this.id);
        sesionDeEjercicio.setFecha(this.fecha);
        sesionDeEjercicio.setDia(this.dia);
        sesionDeEjercicio.setRepeticiones(this.repeticiones);
        sesionDeEjercicio.setCantidad(this.cantidad);
        sesionDeEjercicio.setOrden(this.orden);
        sesionDeEjercicio.setPeso(this.peso);
        sesionDeEjercicio.setEjercicio(this.ejercicio.toDTO());
        sesionDeEjercicio.setTrabajador(this.trabajador.toDTO());

        if (this.cliente != null)
            sesionDeEjercicio.setCliente(this.cliente.toDTO());

        sesionDeEjercicio.setRutina(this.rutina.toDTO());

        return sesionDeEjercicio;
    }
}
