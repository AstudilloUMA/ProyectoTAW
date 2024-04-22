package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedbackdieta", schema = "taw", catalog = "")
public class FeedbackdietaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Calificacion")
    private Integer calificacion;
    @Basic
    @Column(name = "Comentarios")
    private String comentarios;
    @Basic
    @Column(name = "DietaCodigo")
    private Integer dietaCodigo;
    @Basic
    @Column(name = "ClienteId")
    private Integer clienteId;

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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getDietaCodigo() {
        return dietaCodigo;
    }

    public void setDietaCodigo(Integer dietaCodigo) {
        this.dietaCodigo = dietaCodigo;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedbackdietaEntity that = (FeedbackdietaEntity) o;

        if (id != that.id) return false;
        if (calificacion != null ? !calificacion.equals(that.calificacion) : that.calificacion != null) return false;
        if (comentarios != null ? !comentarios.equals(that.comentarios) : that.comentarios != null) return false;
        if (dietaCodigo != null ? !dietaCodigo.equals(that.dietaCodigo) : that.dietaCodigo != null) return false;
        if (clienteId != null ? !clienteId.equals(that.clienteId) : that.clienteId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (calificacion != null ? calificacion.hashCode() : 0);
        result = 31 * result + (comentarios != null ? comentarios.hashCode() : 0);
        result = 31 * result + (dietaCodigo != null ? dietaCodigo.hashCode() : 0);
        result = 31 * result + (clienteId != null ? clienteId.hashCode() : 0);
        return result;
    }
}
