package es.uma.proyectogrupo18.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class RutinaSemanalEntrenamientoEntityPK implements Serializable {
    @Column(name = "Rutina_Semanal_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rutinaSemanalId;
    @Column(name = "Sesion_de_Entrenamiento_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sesionDeEntrenamientoId;

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

        RutinaSemanalEntrenamientoEntityPK that = (RutinaSemanalEntrenamientoEntityPK) o;

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
}
