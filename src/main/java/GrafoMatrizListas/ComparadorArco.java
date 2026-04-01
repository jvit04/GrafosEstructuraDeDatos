package GrafoMatrizListas;

import java.util.Comparator;

// Reemplaza "Arco" por el nombre real de la clase que representa tus aristas/conexiones
public class ComparadorArco<E,V> implements Comparator<Arco<E,V>> {

    @Override
    public int compare(Arco arco1, Arco arco2) {
        // Integer.compare devuelve:
        // -1 si arco1 es menor
        //  0 si son iguales
        //  1 si arco1 es mayor
        return Integer.compare(arco1.getFpeso(), arco2.getFpeso());
    }
}