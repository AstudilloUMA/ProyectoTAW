package es.uma.proyectogrupo18.ui;

public class RutinaUi {
    private Integer orden;
    private String nombre;
    private String tipo;
    private int repeticiones;
    private int cantidad;
    private String video;
    private int sesionId;
    private int ejercicioId;
    private int rutinaId;

    public RutinaUi() {}

    public RutinaUi(Integer orden, String nombre, String tipo, int repeticiones, int cantidad, String video, int sesionId, int ejercicioId, int rutinaId) {
        this.orden = orden;
        this.nombre = nombre;
        this.tipo = tipo;
        this.repeticiones = repeticiones;
        this.cantidad = cantidad;
        this.video = video;
        this.sesionId = sesionId;
        this.ejercicioId = ejercicioId;
        this.rutinaId = rutinaId;
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

    public int getSesionId() {
        return sesionId;
    }

    public void setSesionId(int sesionId) {
        this.sesionId = sesionId;
    }

    public int getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(int ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    public int getRutinaId() {
        return rutinaId;
    }

    public void setRutinaId(int rutinaId) {
        this.rutinaId = rutinaId;
    }
}
