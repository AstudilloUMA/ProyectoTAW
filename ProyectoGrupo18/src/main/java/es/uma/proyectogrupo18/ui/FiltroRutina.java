package es.uma.proyectogrupo18.ui;

import java.sql.Date;

public class FiltroRutina {
    Date fechaInicio;
    Date fechaFin;
    String nombre;

    public FiltroRutina(Date fechaInicio, Date fechaFin, String nombre) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombre = nombre;
    }

    public FiltroRutina() {
        this.fechaInicio = null;
        this.fechaFin = null;
        this.nombre = null;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
