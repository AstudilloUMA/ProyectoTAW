package es.uma.proyectogrupo18.ui;

import es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity;
import es.uma.proyectogrupo18.entity.SesionDeEntrenamientoEntity;

public class SesionEjercicio {
    private SesionDeEjercicioEntity sesion;
    private String dia;
    private SesionDeEntrenamientoEntity sesionEntrenamiento;

    public SesionEjercicio(SesionDeEjercicioEntity sesion, String dia, SesionDeEntrenamientoEntity sesionEntrenamiento) {
        this.sesion = sesion;
        this.dia = dia;
        this.sesionEntrenamiento = sesionEntrenamiento;
    }

    public SesionDeEntrenamientoEntity getSesionEntrenamiento() {
        return sesionEntrenamiento;
    }

    public void setSesionEntrenamiento(SesionDeEntrenamientoEntity sesionEntrenamiento) {
        this.sesionEntrenamiento = sesionEntrenamiento;
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
