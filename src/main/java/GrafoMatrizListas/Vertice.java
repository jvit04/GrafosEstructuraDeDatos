package GrafoMatrizListas;

import java.util.LinkedList;
import java.util.List;

public class Vertice<V,E> {
    private V content;
    private LinkedList<Arco<E,V>> listaArcos;
    private boolean vistado;

    public Vertice(V content) {
        this.content = content;
        this.listaArcos = new LinkedList<>();
        this.vistado=false;
    }

    public Vertice(V content, LinkedList<Arco<E, V>> listaArcos, boolean vistado) {
        this.content = content;
        this.listaArcos = listaArcos;
        this.vistado = vistado;
    }

    public boolean isVistado() {
        return vistado;
    }

    public void setVistado(boolean vistado) {
        this.vistado = vistado;
    }

    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    public LinkedList<Arco<E, V>> getListaArcos() {
        return listaArcos;
    }

    public void setListaArcos(LinkedList<Arco<E, V>> listaArcos) {
        this.listaArcos = listaArcos;
    }

    @Override
    public String toString() {
        return ""+this.content;
    }
}


//devuelvame una lista de un grafo de un vertice que pueda llegar a todos los dem