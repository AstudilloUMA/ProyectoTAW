package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback", schema = "taw", catalog = "")
public class FeedbackEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Calificacion")
    private Integer calificacion;
    @Basic
    @Column(name = "Estado_Del_Cliente")
    private String estadoDelCliente;
    @Basic
    @Column(name = "Comentarios")
    private String comentarios;
    @Basic
    @Column(name = "Ejercicio_Id", insertable = false, updatable = false)
    private Integer ejercicioId;
    @Basic
    @Column(name = "Cliente_Id", insertable = false, updatable = false)
    private Integer clienteId;
    @Basic
    @Column(name = "Trabajador_Id")
    private Integer trabajadorId;
    @ManyToOne
    @JoinColumn(name = "Ejercicio_Id", referencedColumnName = "Id", insertable = false, updatable = false)
    private EjercicioEntity ejercicioByEjercicioId;
    @ManyToOne
    @JoinColumn(name = "Cliente_Id", referencedColumnName = "Usuario_id", insertable = false, updatable = false)
    private ClienteEntity clienteByClienteId;
    @ManyToOne
    @JoinColumn(name = "Trabajador_Id", referencedColumnName = "Usuario_id", insertable = false, updatable = false)
    private TrabajadorEntity trabajadorByTrabajadorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Integer getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(Integer ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(Integer trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedbackEntity that = (FeedbackEntity) o;

        if (id != that.id) return false;
        if (calificacion != null ? !calificacion.equals(that.calificacion) : that.calificacion != null) return false;
        if (estadoDelCliente != null ? !estadoDelCliente.equals(that.estadoDelCliente) : that.estadoDelCliente != null)
            return false;
        if (comentarios != null ? !comentarios.equals(that.comentarios) : that.comentarios != null) return false;
        if (ejercicioId != null ? !ejercicioId.equals(that.ejercicioId) : that.ejercicioId != null) return false;
        if (clienteId != null ? !clienteId.equals(that.clienteId) : that.clienteId != null) return false;
        if (trabajadorId != null ? !trabajadorId.equals(that.trabajadorId) : that.trabajadorId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (calificacion != null ? calificacion.hashCode() : 0);
        result = 31 * result + (estadoDelCliente != null ? estadoDelCliente.hashCode() : 0);
        result = 31 * result + (comentarios != null ? comentarios.hashCode() : 0);
        result = 31 * result + (ejercicioId != null ? ejercicioId.hashCode() : 0);
        result = 31 * result + (clienteId != null ? clienteId.hashCode() : 0);
        result = 31 * result + (trabajadorId != null ? trabajadorId.hashCode() : 0);
        return result;
    }

    public EjercicioEntity getEjercicioByEjercicioId() {
        return ejercicioByEjercicioId;
    }

    public void setEjercicioByEjercicioId(EjercicioEntity ejercicioByEjercicioId) {
        this.ejercicioByEjercicioId = ejercicioByEjercicioId;
    }

    public ClienteEntity getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(ClienteEntity clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }

    public TrabajadorEntity getTrabajadorByTrabajadorId() {
        return trabajadorByTrabajadorId;
    }

    public void setTrabajadorByTrabajadorId(TrabajadorEntity trabajadorByTrabajadorId) {
        this.trabajadorByTrabajadorId = trabajadorByTrabajadorId;
    }
}
