package es.uma.proyectogrupo18.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class DietaComidaEntityId implements java.io.Serializable {
    private static final long serialVersionUID = 627008183477142220L;
    @Column(name = "Dieta_Codigo", nullable = false)
    private Integer dietaCodigo;

    @Column(name = "Comida_Id", nullable = false)
    private Integer comidaId;

    public Integer getDietaCodigo() {
        return dietaCodigo;
    }

    public void setDietaCodigo(Integer dietaCodigo) {
        this.dietaCodigo = dietaCodigo;
    }

    public Integer getComidaId() {
        return comidaId;
    }

    public void setComidaId(Integer comidaId) {
        this.comidaId = comidaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DietaComidaEntityId entity = (DietaComidaEntityId) o;
        return Objects.equals(this.comidaId, entity.comidaId) &&
                Objects.equals(this.dietaCodigo, entity.dietaCodigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comidaId, dietaCodigo);
    }


}