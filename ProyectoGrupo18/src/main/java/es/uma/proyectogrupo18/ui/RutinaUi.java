package es.uma.proyectogrupo18.ui;

public class RutinaUi {
    private Integer orden;
    private String nombre;
    private String tipo;
    private int repeticiones;
    private int cantidad;
    private String video;

    public RutinaUi() {}

    public RutinaUi(int orden, String nombre, String tipo, int repeticiones, int cantidad, String video) {
        this.orden = orden;
        this.nombre = nombre;
        this.tipo = tipo;
        this.repeticiones = repeticiones;
        this.cantidad = cantidad;
        this.video = video;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
