package es.uma.proyectogrupo18.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class DietaComidaEntityPK implements Serializable {
    @Column(name = "Dieta_Codigo")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dietaCodigo;
    @Column(name = "Comida_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comidaId;

    public int getDietaCodigo() {
        return dietaCodigo;
    }

    public void setDietaCodigo(int dietaCodigo) {
        this.dietaCodigo = dietaCodigo;
    }

    public int getComidaId() {
        return comidaId;
    }

    public void setComidaId(int comidaId) {
        this.comidaId = comidaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DietaComidaEntityPK that = (DietaComidaEntityPK) o;

        if (dietaCodigo != that.dietaCodigo) return false;
        if (comidaId != that.comidaId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dietaCodigo;
        result = 31 * result + comidaId;
        return result;
    }
}
