package es.uma.proyectogrupo18.ui;

import es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity;

public class SesionEjercicio {
    private SesionDeEjercicioEntity sesion;
    private String dia;

    public SesionEjercicio(SesionDeEjercicioEntity sesion, String dia) {
        this.sesion = sesion;
        this.dia = dia;
    }

    public SesionDeEjercicioEntity getSesion() {
        return sesion;
    }

    public void setSesion(SesionDeEjercicioEntity sesion) {
        this.sesion = sesion;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
