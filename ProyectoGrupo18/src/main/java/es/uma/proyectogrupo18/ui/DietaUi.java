package es.uma.proyectogrupo18.ui;

import es.uma.proyectogrupo18.entity.MenuEntity;

public class DietaUi {

    private Integer codigo;
    private Integer orden;
    private String nombre;
    private Integer kcal;
    private MenuEntity menu;

    private int dietaId;
    private int comidaId;

    public DietaUi() {}

    public DietaUi(Integer codigo, Integer orden, String nombre, Integer kcal, MenuEntity menu, int dietaId, int comidaId) {
        this.codigo = codigo;
        this.orden = orden;
        this.nombre = nombre;
        this.kcal = kcal;
        this.menu = menu;
        this.dietaId = dietaId;
        this.comidaId = comidaId;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    public int getDietaId() {
        return dietaId;
    }

    public void setDietaId(int dietaId) {
        this.dietaId = dietaId;
    }

    public int getComidaId() {
        return comidaId;
    }

    public void setComidaId(int comidaId) {
        this.comidaId = comidaId;
    }
}
