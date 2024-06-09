package es.uma.proyectogrupo18.ui;

public class ObtenerIdYT {
    public static String obtenerIdYT(String url) {
        String[] urlSplit = url.split("=");
        return urlSplit[urlSplit.length-1];
    }
}
