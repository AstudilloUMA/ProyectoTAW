package es.uma.proyectogrupo18.ui;

public class FiltroEjercicio {
    private Integer id;
    private String tipo;
    private String nombre;
    private Boolean ejercicio;

    public FiltroEjercicio() {
    }

    public FiltroEjercicio(Integer id, String tipo, String nombre) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Boolean ejercicio) {
        this.ejercicio = ejercicio;
    }
}
