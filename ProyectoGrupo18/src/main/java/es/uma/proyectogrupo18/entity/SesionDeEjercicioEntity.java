package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "sesion_de_ejercicio")
public class SesionDeEjercicioEntity {
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

    @Column(name = "SeriesCompletadas")
    private Integer seriesCompletadas = 0;

    @Column(name = "RepeticionesCompletadas")
    private Integer repeticionesCompletadas = 0;

    @Column(name = "Cantidad")
    private String cantidad;

    @Column(name = "Orden")
    private Integer orden;

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
    @JoinColumn(name = "Cliente_Id")
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Rutina_Id")
    private RutinaSemanalEntity rutina;

    /*
    @Column(name = "SeriesCompletadas")
    private Integer seriesCompletadas;

    @Column(name = "RepeticionesCompletadas")
    private Integer repeticionesCompletadas;
*/
    @Column(name = "Calificacion")
    private Integer calificacion;

    @Column(name = "Comentario")
    private String comentario;

    /*
    public Integer getSeriesCompletadas() {
        return seriesCompletadas;
    }

    public void setSeriesCompletadas(Integer seriesCompletadas) {
        this.seriesCompletadas = seriesCompletadas;
    }

    public void setSeriesCompletadas(Integer seriesCompletadas) {
        this.seriesCompletadas = seriesCompletadas != null ? seriesCompletadas : 0;
    }

    public void setRepeticionesCompletadas(Integer repeticionesCompletadas) {
        this.repeticionesCompletadas = repeticionesCompletadas != null ? repeticionesCompletadas : 0;
    }
*/
    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

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
}
