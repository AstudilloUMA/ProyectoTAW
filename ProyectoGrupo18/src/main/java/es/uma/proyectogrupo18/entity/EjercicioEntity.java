package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "ejercicio", schema = "taw", catalog = "")
public class EjercicioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Tipo")
    private String tipo;
    @Basic
    @Column(name = "Nombre")
    private String nombre;
    @Basic
    @Column(name = "Video")
    private String video;
    @Basic
    @Column(name = "Tipo_Id", insertable = false, updatable = false)
    private Integer tipoId;
    @ManyToOne
    @JoinColumn(name = "Tipo_Id", referencedColumnName = "Id")
    private TipoEjercicioEntity tipoEjercicioByTipoId;
    @OneToMany(mappedBy = "ejercicioByEjercicioId")
    private Collection<FeedbackEntity> feedbacksById;
    @OneToMany(mappedBy = "ejercicioByEjercicioId")
    private Collection<SesionDeEjercicioEntity> sesionDeEjerciciosById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EjercicioEntity that = (EjercicioEntity) o;

        if (id != that.id) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (video != null ? !video.equals(that.video) : that.video != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        return result;
    }

    public Integer getTipoId() {
        return tipoId;
    }

    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

    public TipoEjercicioEntity getTipoEjercicioByTipoId() {
        return tipoEjercicioByTipoId;
    }

    public void setTipoEjercicioByTipoId(TipoEjercicioEntity tipoEjercicioByTipoId) {
        this.tipoEjercicioByTipoId = tipoEjercicioByTipoId;
    }

    public Collection<FeedbackEntity> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(Collection<FeedbackEntity> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }

    public Collection<SesionDeEjercicioEntity> getSesionDeEjerciciosById() {
        return sesionDeEjerciciosById;
    }

    public void setSesionDeEjerciciosById(Collection<SesionDeEjercicioEntity> sesionDeEjerciciosById) {
        this.sesionDeEjerciciosById = sesionDeEjerciciosById;
    }
}
