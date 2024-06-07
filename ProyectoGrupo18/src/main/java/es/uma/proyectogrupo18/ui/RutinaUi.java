package es.uma.proyectogrupo18.ui;

public class RutinaUi {
    private Integer orden;
    private String dia;
    private String nombre;
    private String tipo;
    private String repeticiones;
    private String cantidad;
    private String video;
    private int sesionId;
    private int ejercicioId;
    private int rutinaId;
    private int sesionEntrenamientoId;

    public RutinaUi() {}

    public RutinaUi(Integer orden, String dia, String nombre, String tipo, String repeticiones, String cantidad, String video, int sesionId, int ejercicioId, int rutinaId, int sesionEntrenamientoId) {
        this.orden = orden;
        this.dia = dia;
        this.nombre = nombre;
        this.tipo = tipo;
        this.repeticiones = repeticiones;
        this.cantidad = cantidad;
        this.video = video;
        this.sesionId = sesionId;
        this.ejercicioId = ejercicioId;
        this.rutinaId = rutinaId;
        this.sesionEntrenamientoId = sesionEntrenamientoId;
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

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getSesionEntrenamientoId() {
        return sesionEntrenamientoId;
    }

    public void setSesionEntrenamientoId(int sesionEntrenamientoId) {
        this.sesionEntrenamientoId = sesionEntrenamientoId;
    }
}
