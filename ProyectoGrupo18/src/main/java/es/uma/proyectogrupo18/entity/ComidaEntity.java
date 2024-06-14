package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.Comida;
import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.Trabajador;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "comida")
public class ComidaEntity implements Serializable, DTO<Comida> {
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

    public Comida toDTO() {
        Comida comida = new Comida();
        comida.setId(this.id);
        comida.setNombre(this.nombre);
        comida.setKilocaloriasTotales(this.kilocaloriasTotales);
        comida.setOrden(this.orden);

        List<Integer> dietaIds = new ArrayList<>();
        this.dietas.forEach(dieta -> dietaIds.add(dieta.getId()));
        comida.setDietas(dietaIds);

        List<Integer> menuIds = new ArrayList<>();
        this.menus.forEach(menu -> menuIds.add(menu.getId()));
        comida.setMenus(menuIds);

        return comida;
    }
}