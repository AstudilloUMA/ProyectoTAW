package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "dieta", schema = "taw", catalog = "")
public class DietaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Codigo")
    private int codigo;
    @Basic
    @Column(name = "Num_Comidas")
    private Integer numComidas;
    @Basic
    @Column(name = "Tipo")
    private String tipo;
    @Basic
    @Column(name = "Fecha_Inicio")
    private Date fechaInicio;
    @Basic
    @Column(name = "Fecha_Fin")
    private Date fechaFin;
    @Basic
    @Column(name = "Trabajador_Id", insertable = false, updatable = false)
    private Integer trabajadorId;
    @ManyToOne
    @JoinColumn(name = "Trabajador_Id", referencedColumnName = "Usuario_id")
    private TrabajadorEntity trabajadorByTrabajadorId;
    @OneToMany(mappedBy = "dietaByDietaCodigo")
    private Collection<FeedbackdietaEntity> feedbackdietasByCodigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Integer getNumComidas() {
        return numComidas;
    }

    public void setNumComidas(Integer numComidas) {
        this.numComidas = numComidas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

        DietaEntity that = (DietaEntity) o;

        if (codigo != that.codigo) return false;
        if (numComidas != null ? !numComidas.equals(that.numComidas) : that.numComidas != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;
        if (fechaFin != null ? !fechaFin.equals(that.fechaFin) : that.fechaFin != null) return false;
        if (trabajadorId != null ? !trabajadorId.equals(that.trabajadorId) : that.trabajadorId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + (numComidas != null ? numComidas.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaFin != null ? fechaFin.hashCode() : 0);
        result = 31 * result + (trabajadorId != null ? trabajadorId.hashCode() : 0);
        return result;
    }

    public TrabajadorEntity getTrabajadorByTrabajadorId() {
        return trabajadorByTrabajadorId;
    }

    public void setTrabajadorByTrabajadorId(TrabajadorEntity trabajadorByTrabajadorId) {
        this.trabajadorByTrabajadorId = trabajadorByTrabajadorId;
    }

    public Collection<FeedbackdietaEntity> getFeedbackdietasByCodigo() {
        return feedbackdietasByCodigo;
    }

    public void setFeedbackdietasByCodigo(Collection<FeedbackdietaEntity> feedbackdietasByCodigo) {
        this.feedbackdietasByCodigo = feedbackdietasByCodigo;
    }
}
