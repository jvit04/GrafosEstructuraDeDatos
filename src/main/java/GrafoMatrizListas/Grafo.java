package GrafoMatrizListas;

import java.util.*;

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
        //Lista de los vertices a agregar
        LinkedList<Vertice<V,E>> vertices = new LinkedList<>();
        //pila donde se guardaran los vertices que no han sido visitados.
        Stack<Vertice<V,E>> pila = new Stack<>();
        //Empezamos por el vertice que fue insertado por el usuario
        pila.push(Vo);
        //Repetimos hasta que la pila este vacía
        while(!pila.isEmpty()){
            //asignamos a un nuevo vertice el último elemento de la pila.
            Vertice<V,E> v =pila.pop();
            //primero se verifica que no haya sido procesado el vertice
            //Porque debemos verificar? Porque terminaríamos en lazo infinito si no.
            if(!v.isVistado()){
                //decimos que ya fue visitado el nodo
                v.setVistado(true);
                //lo agregamos a la lista de vertices que retornaremos
                vertices.add(v);
            }
            //2. Solo se agrega en la pila los nodos que no han sido visitados
            for(Arco<E,V> a:v.getListaArcos()){
                //Volvemos a verificar que no hayan sido visitados
                if(!a.getDestino().isVistado()){
                    pila.push(a.getDestino());
                }
            }
        }
        return vertices;
    }

public LinkedList<Vertice<V,E>> recorridoEnAnchura(Vertice<V,E> vo){
    //Lista de los vertices a agregar
    LinkedList<Vertice<V,E>> vertices = new LinkedList<>();
    //Cola donde se encolaran los vertices que recorreremos
    Queue<Vertice<V,E>> cola =  new ArrayDeque<>();
    //Encolamos el vertice de partida
    cola.add(vo);
    //lo marcamos como visitado
    vo.setVistado(true);
    //Empezamos el while
    //se realizará hasta que la cola esté vacía
    while(!cola.isEmpty()){
        //desencolar vertice
        Vertice<V,E> vertice = cola.poll();
        //agregarlo a la lista de vertices
        //también se verifica que no ha sido visitado
        vertices.add(vertice);
        //Recorrer los adyacentes del vertice para obtener los no visitados.
        for (Arco<E,V> arco:vertice.getListaArcos()){
            //Verificamos que no hayan sido visitados
            //Usamos arco.getDestino
            //¿Por qué? Porque necesito saber todos los vertices a los que
            // se unen cada uno.
            if(!arco.getDestino().isVistado()){
                arco.getDestino().setVistado(true);
                cola.add(arco.destino);
            }

        }

    }
    return vertices;
}
    // Método para resetear el estado de todos los vértices
    public void reiniciarVisitados() {
        for(Vertice<V,E> v : LVertices) {
            v.setVistado(false);
        }
    }

    //Método buscar y retornar primer no visitado
    //retorna null si al buscar en la lista de vertices no encuentra ninguno
    //que no haya sido visitado
    public Vertice<V,E> retornarNoVisitado(){
        for(Vertice<V,E> v : LVertices) {
            if(!v.isVistado()){
                return v;
            }
        }
        return null;
    }

   //función que se encontraba dentro de las slides
    //los componentes forman una lista de listas
    //Luego de recorrer, obtendremos un conjunto de vertices
    //LRecorrido
    public boolean getConnectedComponents(LinkedList<LinkedList<Vertice<V,E>>> componentes){
        Vertice<V,E> vertice;
        LinkedList<Vertice<V,E>> recorrido;
        while (true){
            vertice= this.retornarNoVisitado();
            if(vertice==null) break;
            recorrido = this.recorridoEnAnchura(vertice);
            componentes.add(recorrido);
        }
        return componentes.size()==1;
    }
}


