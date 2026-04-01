package GrafoMatrizListas;

import org.w3c.dom.Node;

public class Arco<E,V> {
    E data;
    Vertice<V,E> origen;
    Vertice<V,E> destino;
    int fpeso;

    public Arco(E data, Vertice<V, E> origen, Vertice<V, E> destino, int fpeso) {
        this.data = data;
        this.origen = origen;
        this.destino = destino;
        this.fpeso = fpeso;
    }

    public Arco(Arco<E,V> arco){
        this.data = arco.data;
        this.origen =arco.origen;
        this.destino = arco.destino;
        this.fpeso = arco.fpeso;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Vertice<V, E> getOrigen() {
        return origen;
    }

    public void setOrigen(Vertice<V, E> origen) {
        this.origen = origen;
    }

    public Vertice<V, E> getDestino() {
        return destino;
    }

    public void setDestino(Vertice<V, E> destino) {
        this.destino = destino;
    }

    public int getFpeso() {
        return fpeso;
    }

    public void setFpeso(int fpeso) {
        this.fpeso = fpeso;
    }

    @Override
    public String toString() {
        return "Arco{" +
                "data=" + data +
                '}';
    }
}
