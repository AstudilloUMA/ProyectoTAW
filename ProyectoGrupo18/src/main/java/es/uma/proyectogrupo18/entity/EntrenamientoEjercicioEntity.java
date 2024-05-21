package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "entrenamiento_ejercicio", schema = "taw", catalog = "")
@IdClass(EntrenamientoEjercicioEntityPK.class)
public class EntrenamientoEjercicioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Sesion_de_Entrenamiento_Id")
    private int sesionDeEntrenamientoId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Sesion_de_Ejercicio_Id")
    private int sesionDeEjercicioId;
    @ManyToOne
    @JoinColumn(name = "Sesion_de_Entrenamiento_Id", referencedColumnName = "Id", nullable = false)
    private SesionDeEntrenamientoEntity sesionDeEntrenamientoBySesionDeEntrenamientoId;
    @ManyToOne
    @JoinColumn(name = "Sesion_de_Ejercicio_Id", referencedColumnName = "Id", nullable = false)
    private SesionDeEjercicioEntity sesionDeEjercicioBySesionDeEjercicioId;

    public int getSesionDeEntrenamientoId() {
        return sesionDeEntrenamientoId;
    }

    public void setSesionDeEntrenamientoId(int sesionDeEntrenamientoId) {
        this.sesionDeEntrenamientoId = sesionDeEntrenamientoId;
    }

    public int getSesionDeEjercicioId() {
        return sesionDeEjercicioId;
    }

    public void setSesionDeEjercicioId(int sesionDeEjercicioId) {
        this.sesionDeEjercicioId = sesionDeEjercicioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntrenamientoEjercicioEntity that = (EntrenamientoEjercicioEntity) o;

        if (sesionDeEntrenamientoId != that.sesionDeEntrenamientoId) return false;
        if (sesionDeEjercicioId != that.sesionDeEjercicioId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sesionDeEntrenamientoId;
        result = 31 * result + sesionDeEjercicioId;
        return result;
    }

    public SesionDeEntrenamientoEntity getSesionDeEntrenamientoBySesionDeEntrenamientoId() {
        return sesionDeEntrenamientoBySesionDeEntrenamientoId;
    }

    public void setSesionDeEntrenamientoBySesionDeEntrenamientoId(SesionDeEntrenamientoEntity sesionDeEntrenamientoBySesionDeEntrenamientoId) {
        this.sesionDeEntrenamientoBySesionDeEntrenamientoId = sesionDeEntrenamientoBySesionDeEntrenamientoId;
    }

    public SesionDeEjercicioEntity getSesionDeEjercicioBySesionDeEjercicioId() {
        return sesionDeEjercicioBySesionDeEjercicioId;
    }

    public void setSesionDeEjercicioBySesionDeEjercicioId(SesionDeEjercicioEntity sesionDeEjercicioBySesionDeEjercicioId) {
        this.sesionDeEjercicioBySesionDeEjercicioId = sesionDeEjercicioBySesionDeEjercicioId;
    }
}
