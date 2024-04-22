package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comida", schema = "taw", catalog = "")
public class ComidaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Nombre")
    private String nombre;
    @Basic
    @Column(name = "KilocaloriasTotales")
    private Integer kilocaloriasTotales;
    @Basic
    @Column(name = "Orden")
    private Integer orden;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getKilocaloriasTotales() {
        return kilocaloriasTotales;
    }

    public void setKilocaloriasTotales(Integer kilocaloriasTotales) {
        this.kilocaloriasTotales = kilocaloriasTotales;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComidaEntity that = (ComidaEntity) o;

        if (id != that.id) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (kilocaloriasTotales != null ? !kilocaloriasTotales.equals(that.kilocaloriasTotales) : that.kilocaloriasTotales != null)
            return false;
        if (orden != null ? !orden.equals(that.orden) : that.orden != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (kilocaloriasTotales != null ? kilocaloriasTotales.hashCode() : 0);
        result = 31 * result + (orden != null ? orden.hashCode() : 0);
        return result;
    }
}
