package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.Dieta;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "dieta_comida")
public class DietaComidaEntity {
    @EmbeddedId
    private DietaComidaEntityId id;

    @MapsId("dietaCodigo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Dieta_Codigo", nullable = false)
    private DietaEntity dietaCodigo;

    @MapsId("comidaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Comida_Id", nullable = false)
    private ComidaEntity comida;

    public DietaComidaEntityId getId() {
        return id;
    }

    public void setId(DietaComidaEntityId id) {
        this.id = id;
    }

    public DietaEntity getDietaCodigo() {
        return dietaCodigo;
    }

    public void setDietaCodigo(DietaEntity dietaCodigo) {
        this.dietaCodigo = dietaCodigo;
    }

    public ComidaEntity getComida() {
        return comida;
    }

    public void setComida(ComidaEntity comida) {
        this.comida = comida;
    }


}