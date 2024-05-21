package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu", schema = "taw", catalog = "")
public class MenuEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Comida_Id", insertable = false, updatable = false)
    private Integer comidaId;
    @Basic
    @Column(name = "Ingredientes")
    private String ingredientes;
    @Basic
    @Column(name = "Preparacion")
    private String preparacion;
    @ManyToOne
    @JoinColumn(name = "Comida_Id", referencedColumnName = "Id", insertable = false, updatable = false)
    private ComidaEntity comidaByComidaId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getComidaId() {
        return comidaId;
    }

    public void setComidaId(Integer comidaId) {
        this.comidaId = comidaId;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuEntity that = (MenuEntity) o;

        if (id != that.id) return false;
        if (comidaId != null ? !comidaId.equals(that.comidaId) : that.comidaId != null) return false;
        if (ingredientes != null ? !ingredientes.equals(that.ingredientes) : that.ingredientes != null) return false;
        if (preparacion != null ? !preparacion.equals(that.preparacion) : that.preparacion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (comidaId != null ? comidaId.hashCode() : 0);
        result = 31 * result + (ingredientes != null ? ingredientes.hashCode() : 0);
        result = 31 * result + (preparacion != null ? preparacion.hashCode() : 0);
        return result;
    }

    public ComidaEntity getComidaByComidaId() {
        return comidaByComidaId;
    }

    public void setComidaByComidaId(ComidaEntity comidaByComidaId) {
        this.comidaByComidaId = comidaByComidaId;
    }
}
