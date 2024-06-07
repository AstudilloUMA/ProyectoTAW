package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
    private List<DietaEntity> dietas = new ArrayList<>();

    @OneToMany(mappedBy = "comida")
    private List<MenuEntity> menus = new ArrayList<>();

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

    public List<DietaEntity> getDietas() {
        return dietas;
    }

    public void setDietas(List<DietaEntity> dietas) {
        this.dietas = dietas;
    }

    public List<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuEntity> menus) {
        this.menus = menus;
    }

}