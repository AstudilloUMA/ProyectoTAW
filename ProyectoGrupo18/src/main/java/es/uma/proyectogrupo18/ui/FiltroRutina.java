package es.uma.proyectogrupo18.ui;

import java.sql.Date;

public class FiltroRutina {
    String fechaInicio;
    String fechaFin;
    String nombre;

    public FiltroRutina(String fechaInicio, String fechaFin, String nombre) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombre = nombre;
    }

    public FiltroRutina() {
        this.fechaInicio = null;
        this.fechaFin = null;
        this.nombre = null;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
