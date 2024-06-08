package es.uma.proyectogrupo18.ui;

public class FiltroCRUD {
    private Integer id;
    private Integer ifComida;
    private Integer ifEj;
    private Integer ifSe;
    private String comidaNombre;
    private Integer comidaCalorias;
    private String ejTipo;
    private String ejNombre;
    private String seRepeticiones;
    private String seCantidad;
    private String seEjercicio;
    private String seTrabajo;

    public FiltroCRUD(){

    }

    //Comida
    public FiltroCRUD(Integer id, String nombre, Integer calorias) {
        this.id = id;
        this.comidaNombre = nombre;
        this.comidaCalorias = calorias;
    }

    //ejercicio
    public FiltroCRUD(Integer id, String tipo, String nombre) {
        this.id = id;
        this.ejTipo = tipo;
        this.ejNombre = nombre;
    }

    //sesion
    public FiltroCRUD(Integer id, String repeticiones, String cantidad, String ejercicio, String trabajo) {
        this.id = id;
        this.seRepeticiones = repeticiones;
        this.seCantidad = cantidad;
        this.seEjercicio = ejercicio;
        this.seTrabajo = trabajo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getters y Setters para ifComida
    public Integer getIfComida() {
        return ifComida;
    }

    public void setIfComida(Integer ifComida) {
        this.ifComida = ifComida;
    }

    // Getters y Setters para ifEj
    public Integer getIfEj() {
        return ifEj;
    }

    public void setIfEj(Integer ifEj) {
        this.ifEj = ifEj;
    }

    // Getters y Setters para ifSe
    public Integer getIfSe() {
        return ifSe;
    }

    public void setIfSe(Integer ifSe) {
        this.ifSe = ifSe;
    }

    public String getComidaNombre() {
        return comidaNombre;
    }

    public void setComidaNombre(String comidaNombre) {
        this.comidaNombre = comidaNombre;
    }

    public Integer getComidaCalorias() {
        return comidaCalorias;
    }

    public void setComidaCalorias(Integer comidaCalorias) {
        this.comidaCalorias = comidaCalorias;
    }

    public String getejTipo() {
        return ejTipo;
    }

    public void setejTipo(String tipo) {
        this.ejTipo = tipo;
    }

    public String getejNombre() {
        return ejNombre;
    }

    public void setejNombre(String nombre) {
        this.ejNombre = nombre;
    }

    public String getseRepeticiones() {
        return seRepeticiones;
    }
    public void setseRepeticiones(String repeticiones) {
        this.seRepeticiones = repeticiones;
    }

    public String getseCantidad() {
        return seCantidad;
    }

    public void setseCantidad(String cantidad) {
        this.seCantidad = cantidad;
    }

    public String getseEjercicio() {
        return seEjercicio;
    }

    public void setseEjercicio(String ejercicio) {
        this.seEjercicio = ejercicio;
    }
    public String getseTrabajo() {
        return seTrabajo;
    }

    public void setseTrabajo(String trabajo) {
        this.seTrabajo = trabajo;
    }
}

