package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "cliente", schema = "taw", catalog = "")
public class ClienteEntity {
    @Id
    @Column(name = "Usuario_id")
    private int usuarioId;
    @Basic
    @Column(name = "Peso")
    private BigDecimal peso;
    @Basic
    @Column(name = "Altura")
    private BigDecimal altura;
    @Basic
    @Column(name = "Edad")
    private Integer edad;
    @OneToOne
    @JoinColumn(name = "Usuario_id", referencedColumnName = "Id", nullable = false)
    private UsuarioEntity usuarioByUsuarioId;
    @ManyToOne
    @JoinColumn(name = "Rutina_Id", referencedColumnName = "Id")
    private RutinaSemanalEntity rutinaSemanalByRutinaId;
    @OneToMany(mappedBy = "clienteByClienteId")
    private Collection<FeedbackEntity> feedbacksByUsuarioId;
    @OneToMany(mappedBy = "clienteByClienteId")
    private Collection<FeedbackdietaEntity> feedbackdietasByUsuarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Dieta_Codigo")
    private DietaEntity dietaCodigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Dietista_Id")
    private TrabajadorEntity dietista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Entrenador_Id")
    private TrabajadorEntity entrenador;

    public TrabajadorEntity getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(TrabajadorEntity entrenador) {
        this.entrenador = entrenador;
    }

    public TrabajadorEntity getDietista() {
        return dietista;
    }

    public void setDietista(TrabajadorEntity dietista) {
        this.dietista = dietista;
    }

    public DietaEntity getDietaCodigo() {
        return dietaCodigo;
    }

    public void setDietaCodigo(DietaEntity dietaCodigo) {
        this.dietaCodigo = dietaCodigo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteEntity that = (ClienteEntity) o;

        if (usuarioId != that.usuarioId) return false;
        if (peso != null ? !peso.equals(that.peso) : that.peso != null) return false;
        if (altura != null ? !altura.equals(that.altura) : that.altura != null) return false;
        if (edad != null ? !edad.equals(that.edad) : that.edad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = usuarioId;
        result = 31 * result + (peso != null ? peso.hashCode() : 0);
        result = 31 * result + (altura != null ? altura.hashCode() : 0);
        result = 31 * result + (edad != null ? edad.hashCode() : 0);
        return result;
    }

    public UsuarioEntity getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(UsuarioEntity usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public RutinaSemanalEntity getRutinaSemanalByRutinaId() {
        return rutinaSemanalByRutinaId;
    }

    public void setRutinaSemanalByRutinaId(RutinaSemanalEntity rutinaSemanalByRutinaId) {
        this.rutinaSemanalByRutinaId = rutinaSemanalByRutinaId;
    }

    public Collection<FeedbackEntity> getFeedbacksByUsuarioId() {
        return feedbacksByUsuarioId;
    }

    public void setFeedbacksByUsuarioId(Collection<FeedbackEntity> feedbacksByUsuarioId) {
        this.feedbacksByUsuarioId = feedbacksByUsuarioId;
    }

    public Collection<FeedbackdietaEntity> getFeedbackdietasByUsuarioId() {
        return feedbackdietasByUsuarioId;
    }

    public void setFeedbackdietasByUsuarioId(Collection<FeedbackdietaEntity> feedbackdietasByUsuarioId) {
        this.feedbackdietasByUsuarioId = feedbackdietasByUsuarioId;
    }
}
