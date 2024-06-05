package es.uma.proyectogrupo18.ui;

import es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity;

import java.util.List;

public class Quicksort {
    public static void quickSort(List<SesionDeEjercicioEntity> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private static void quickSort(List<SesionDeEjercicioEntity> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static int partition(List<SesionDeEjercicioEntity> list, int low, int high) {
        SesionDeEjercicioEntity pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getOrden() <= pivot.getOrden()) {
                i++;
                // swap list[i] and list[j]
                SesionDeEjercicioEntity temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        SesionDeEjercicioEntity temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }
}
