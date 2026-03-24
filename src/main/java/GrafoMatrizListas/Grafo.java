package GrafoMatrizListas;

import java.util.LinkedList;
import java.util.Stack;

public class Grafo <V,E>{
    LinkedList<Vertice<V,E>> LVertices;
    boolean esDirigido;

    public Grafo(boolean esDirigido) {
        this.esDirigido = esDirigido;
        this.LVertices = new LinkedList<>();
    }

    //agregar vertice
    public void addVertice(Vertice<V,E> vertice){
        LVertices.add(vertice);
    }

    //agregar arco
    public void addArco(E data, Vertice<V,E> verticeOrigen, Vertice<V,E> verticeDestino, int fpeso){
       Arco<E,V> arco = new Arco<>(data,verticeOrigen,verticeDestino, fpeso);

       verticeOrigen.getListaArcos().add(arco);
       if(!this.esDirigido){
           arco = new Arco<>(data,verticeDestino,verticeOrigen, fpeso);
           verticeDestino.getListaArcos().add(arco);
       }

    }

    public void imprimirGrafo(){
        System.out.println("Vertices: ");
        for(Vertice<V,E> v:LVertices){
            System.out.println(v.getContent());
            for(Arco<E,V> a:v.getListaArcos()){
                System.out.println("Arco entre " + a.getOrigen().getContent() + " y " + a.getDestino().getContent() + "con caracteristicas " + a.getData());
            }
        }


    }
    public LinkedList<Vertice<V,E>> recorridoProfundo(Vertice<V,E> Vo){
        LinkedList<Vertice<V,E>> vertices = new LinkedList<>();
        Stack<Vertice<V,E>> pila = new Stack<>();
        pila.push(Vo);
        while(!pila.isEmpty()){
            Vertice<V,E> v =pila.pop();
            v.setVistado(true);
            vertices.add(v);
            for(Arco<E,V> a:v.getListaArcos()){
                pila.push(a.getDestino());
            }
        }
        return vertices;
    }


}


