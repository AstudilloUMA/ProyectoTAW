package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "comida")
public class ComidaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Kilocalorias_Totales")
    private Integer kilocaloriasTotales;

    @Column(name = "Orden")
    private Integer orden;

    @ManyToMany(mappedBy = "comidas")
    private Set<DietaEntity> dietas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "comida")
    private Set<MenuEntity> menus = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Set<DietaEntity> getDietas() {
        return dietas;
    }

    public void setDietas(Set<DietaEntity> dietas) {
        this.dietas = dietas;
    }

    public Set<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(Set<MenuEntity> menus) {
        this.menus = menus;
    }

}