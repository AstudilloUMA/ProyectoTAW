package es.uma.proyectogrupo18.ui;

import es.uma.proyectogrupo18.entity.ComidaEntity;
import es.uma.proyectogrupo18.entity.DietaComidaEntity;
import es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity;
import es.uma.proyectogrupo18.entity.SesionDeEntrenamientoEntity;

import java.util.List;

public class Quicksort {
    public static void quickSort(List<SesionEjercicio> list) {
        quickSort(list, 0, list.size() - 1);
    }

    public static void quickSortSesiones(List<SesionDeEntrenamientoEntity> list) {
        quickSortSesiones(list, 0, list.size() - 1);
    }

    public static void quickSortDietas(List<ComidaEntity> list) {
        quickSortDietas(list, 0, list.size() - 1);
    }

    private static void quickSort(List<SesionEjercicio> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static void quickSortSesiones(List<SesionDeEntrenamientoEntity> list, int low, int high) {
        if (low < high) {
            int pi = partitionSesiones(list, low, high);
            quickSortSesiones(list, low, pi - 1);
            quickSortSesiones(list, pi + 1, high);
        }
    }

    private static void quickSortDietas(List<ComidaEntity> list, int low, int high) {
        if (low < high) {
            int pi = partitionDietas(list, low, high);
            quickSortDietas(list, low, pi - 1);
            quickSortDietas(list, pi + 1, high);
        }
    }

    private static int partition(List<SesionEjercicio> list, int low, int high) {
        SesionEjercicio pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getSesion().getOrden() <= pivot.getSesion().getOrden()) {
                i++;
                // swap list[i] and list[j]
                SesionEjercicio temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        SesionEjercicio temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    private static int partitionSesiones(List<SesionDeEntrenamientoEntity> list, int low, int high) {
        SesionDeEntrenamientoEntity pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).diaToInt() <= pivot.diaToInt()) {
                i++;
                // swap list[i] and list[j]
                SesionDeEntrenamientoEntity temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        SesionDeEntrenamientoEntity temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    private static int partitionDietas(List<ComidaEntity> list, int low, int high) {
        ComidaEntity pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getOrden() <= pivot.getOrden()) {
                i++;
                // swap list[i] and list[j]
                ComidaEntity temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        ComidaEntity temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }
}
