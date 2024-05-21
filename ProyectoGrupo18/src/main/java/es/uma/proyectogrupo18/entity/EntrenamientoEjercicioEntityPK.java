package es.uma.proyectogrupo18.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class EntrenamientoEjercicioEntityPK implements Serializable {
    @Column(name = "Sesion_de_Entrenamiento_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sesionDeEntrenamientoId;
    @Column(name = "Sesion_de_Ejercicio_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sesionDeEjercicioId;

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

        EntrenamientoEjercicioEntityPK that = (EntrenamientoEjercicioEntityPK) o;

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
}
