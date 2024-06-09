/*
AUTOR --> Pablo Astudillo Fraga


 */

package es.uma.proyectogrupo18.ui;

import es.uma.proyectogrupo18.entity.ComidaEntity;
import java.util.List;

public class Quicksort {
    public static void quickSort(List<SesionEjercicio> list) {
        quickSort(list, 0, list.size() - 1);
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
            if (compareSesionEjercicio(list.get(j), pivot) <= 0) {
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

    private static int compareSesionEjercicio(SesionEjercicio se1, SesionEjercicio se2) {
        int diaComparison = se1.getDia().compareTo(se2.getDia());
        if (diaComparison == 0) {
            return Integer.compare(se1.getSesion().getOrden(), se2.getSesion().getOrden());
        }
        return diaComparison;
    }
}
