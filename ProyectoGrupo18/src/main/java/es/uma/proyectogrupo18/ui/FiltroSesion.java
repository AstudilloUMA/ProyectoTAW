package es.uma.proyectogrupo18.ui;

public class FiltroSesion {
    private Integer id;
    private String repeticiones;
    private String cantidad;
    private String ejercicio;
    private String trabajo;
    private Boolean session;

    public FiltroSesion() {
    }

    public FiltroSesion(Integer id, String repeticiones, String cantidad, String ejercicio, String trabajo) {
        this.id = id;
        this.repeticiones = repeticiones;
        this.cantidad = cantidad;
        this.ejercicio = ejercicio;
        this.trabajo = trabajo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public Boolean getSession() {
        return session;
    }

    public void setSession(Boolean session) {
        this.session = session;
    }
}
