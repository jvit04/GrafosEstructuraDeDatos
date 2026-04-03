package GrafoMatrizListas;

import java.util.Comparator;

public class ComparadorVertice<V, E> implements Comparator<Vertice<V, E>> {

    @Override
    public int compare(Vertice<V, E> v1, Vertice<V, E> v2) {
        // Integer.compare devuelve:
        // -1 si la distancia de v1 es menor (está "menos lejos")
        //  0 si están a la misma distancia
        //  1 si la distancia de v1 es mayor
        return Integer.compare(v1.cumulativeDistance, v2.cumulativeDistance);
    }
}