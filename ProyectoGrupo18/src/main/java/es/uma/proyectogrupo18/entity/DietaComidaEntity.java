package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dieta_comida", schema = "taw", catalog = "")
@IdClass(DietaComidaEntityPK.class)
public class DietaComidaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Dieta_Codigo")
    private int dietaCodigo;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Comida_Id")
    private int comidaId;
    @ManyToOne
    @JoinColumn(name = "Dieta_Codigo", referencedColumnName = "Codigo", nullable = false)
    private DietaEntity dietaByDietaCodigo;
    @ManyToOne
    @JoinColumn(name = "Comida_Id", referencedColumnName = "Id", nullable = false)
    private ComidaEntity comidaByComidaId;

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

        DietaComidaEntity that = (DietaComidaEntity) o;

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

    public DietaEntity getDietaByDietaCodigo() {
        return dietaByDietaCodigo;
    }

    public void setDietaByDietaCodigo(DietaEntity dietaByDietaCodigo) {
        this.dietaByDietaCodigo = dietaByDietaCodigo;
    }

    public ComidaEntity getComidaByComidaId() {
        return comidaByComidaId;
    }

    public void setComidaByComidaId(ComidaEntity comidaByComidaId) {
        this.comidaByComidaId = comidaByComidaId;
    }
}
