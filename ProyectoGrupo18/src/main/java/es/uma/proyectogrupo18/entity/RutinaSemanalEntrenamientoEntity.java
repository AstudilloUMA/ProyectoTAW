package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rutina_semanal_entrenamiento", schema = "taw", catalog = "")
@IdClass(RutinaSemanalEntrenamientoEntityPK.class)
public class RutinaSemanalEntrenamientoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Rutina_Semanal_Id")
    private int rutinaSemanalId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Sesion_de_Entrenamiento_Id")
    private int sesionDeEntrenamientoId;
    @ManyToOne
    @JoinColumn(name = "Rutina_Semanal_Id", referencedColumnName = "Id", nullable = false)
    private RutinaSemanalEntity rutinaSemanalByRutinaSemanalId;
    @ManyToOne
    @JoinColumn(name = "Sesion_de_Entrenamiento_Id", referencedColumnName = "Id", nullable = false)
    private SesionDeEntrenamientoEntity sesionDeEntrenamientoBySesionDeEntrenamientoId;

    public int getRutinaSemanalId() {
        return rutinaSemanalId;
    }

    public void setRutinaSemanalId(int rutinaSemanalId) {
        this.rutinaSemanalId = rutinaSemanalId;
    }

    public int getSesionDeEntrenamientoId() {
        return sesionDeEntrenamientoId;
    }

    public void setSesionDeEntrenamientoId(int sesionDeEntrenamientoId) {
        this.sesionDeEntrenamientoId = sesionDeEntrenamientoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RutinaSemanalEntrenamientoEntity that = (RutinaSemanalEntrenamientoEntity) o;

        if (rutinaSemanalId != that.rutinaSemanalId) return false;
        if (sesionDeEntrenamientoId != that.sesionDeEntrenamientoId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rutinaSemanalId;
        result = 31 * result + sesionDeEntrenamientoId;
        return result;
    }

    public RutinaSemanalEntity getRutinaSemanalByRutinaSemanalId() {
        return rutinaSemanalByRutinaSemanalId;
    }

    public void setRutinaSemanalByRutinaSemanalId(RutinaSemanalEntity rutinaSemanalByRutinaSemanalId) {
        this.rutinaSemanalByRutinaSemanalId = rutinaSemanalByRutinaSemanalId;
    }

    public SesionDeEntrenamientoEntity getSesionDeEntrenamientoBySesionDeEntrenamientoId() {
        return sesionDeEntrenamientoBySesionDeEntrenamientoId;
    }

    public void setSesionDeEntrenamientoBySesionDeEntrenamientoId(SesionDeEntrenamientoEntity sesionDeEntrenamientoBySesionDeEntrenamientoId) {
        this.sesionDeEntrenamientoBySesionDeEntrenamientoId = sesionDeEntrenamientoBySesionDeEntrenamientoId;
    }
}
