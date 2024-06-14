package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.Menu;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "menu")
public class MenuEntity implements Serializable, DTO<Menu> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Comida_Id")
    private ComidaEntity comida;

    @Lob
    @Column(name = "Ingredientes")
    private String ingredientes;

    @Lob
    @Column(name = "Preparacion")
    private String preparacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ComidaEntity getComida() {
        return comida;
    }

    public void setComida(ComidaEntity comida) {
        this.comida = comida;
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

    public Menu toDTO() {
        Menu menu = new Menu();
        menu.setId(this.id);
        menu.setComida(this.comida.toDTO());
        menu.setIngredientes(this.ingredientes);
        menu.setPreparacion(this.preparacion);
        return menu;
    }
}