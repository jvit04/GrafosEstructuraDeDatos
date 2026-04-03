package GrafoMatrizListas;

import java.util.*;

public class Grafo <V,E> {
    LinkedList<Vertice<V, E>> LVertices;
    boolean esDirigido;

    public Grafo(boolean esDirigido) {
        this.esDirigido = esDirigido;
        this.LVertices = new LinkedList<>();
    }

    //agregar vertice
    public void addVertice(Vertice<V, E> vertice) {
        LVertices.add(vertice);
    }

    //agregar arco
    public void addArco(E data, Vertice<V, E> verticeOrigen, Vertice<V, E> verticeDestino, int fpeso) {
        Arco<E, V> arco = new Arco<>(data, verticeOrigen, verticeDestino, fpeso);

        verticeOrigen.getListaArcos().add(arco);
        if (!this.esDirigido) {
            arco = new Arco<>(data, verticeDestino, verticeOrigen, fpeso);
            verticeDestino.getListaArcos().add(arco);
        }
    }

    //agregar arco
    public void addArco(Arco<E, V> arcoO) {
        Arco<E, V> arco = new Arco<>(arcoO.data, arcoO.getOrigen(), arcoO.getDestino(), arcoO.fpeso);

        arcoO.getOrigen().getListaArcos().add(arco);
        if (!this.esDirigido) {
            arco = new Arco<>(arcoO.data, arcoO.getDestino(), arcoO.getOrigen(), arcoO.fpeso);
            arcoO.getDestino().getListaArcos().add(arco);
        }
    }

    public void imprimirGrafo() {
        System.out.println("Vertices: ");
        for (Vertice<V, E> v : LVertices) {
            System.out.println(v.getContent());
            for (Arco<E, V> a : v.getListaArcos()) {
                System.out.println("Arco entre " + a.getOrigen().getContent() + " y " + a.getDestino().getContent() + "con caracteristicas " + a.getData());
            }
        }


    }

    public LinkedList<Vertice<V, E>> recorridoProfundo(Vertice<V, E> Vo) {
        //Lista de los vertices a agregar
        LinkedList<Vertice<V, E>> vertices = new LinkedList<>();
        //pila donde se guardaran los vertices que no han sido visitados.
        Stack<Vertice<V, E>> pila = new Stack<>();
        //Empezamos por el vertice que fue insertado por el usuario
        pila.push(Vo);
        //Repetimos hasta que la pila este vacía
        while (!pila.isEmpty()) {
            //asignamos a un nuevo vertice el último elemento de la pila.
            Vertice<V, E> v = pila.pop();
            //primero se verifica que no haya sido procesado el vertice
            //Porque debemos verificar? Porque terminaríamos en lazo infinito si no.
            if (!v.isVistado()) {
                //decimos que ya fue visitado el nodo
                v.setVistado(true);
                //lo agregamos a la lista de vertices que retornaremos
                vertices.add(v);
            }
            //2. Solo se agrega en la pila los nodos que no han sido visitados
            for (Arco<E, V> a : v.getListaArcos()) {
                //Volvemos a verificar que no hayan sido visitados
                if (!a.getDestino().isVistado()) {
                    pila.push(a.getDestino());
                }
            }
        }
        return vertices;
    }

    public LinkedList<Vertice<V, E>> recorridoEnAnchura(Vertice<V, E> vo) {
        //Lista de los vertices a agregar
        LinkedList<Vertice<V, E>> vertices = new LinkedList<>();
        //Cola donde se encolaran los vertices que recorreremos
        Queue<Vertice<V, E>> cola = new ArrayDeque<>();
        //Encolamos el vertice de partida
        cola.add(vo);
        //lo marcamos como visitado
        vo.setVistado(true);
        //Empezamos el while
        //se realizará hasta que la cola esté vacía
        while (!cola.isEmpty()) {
            //desencolar vertice
            Vertice<V, E> vertice = cola.poll();
            //agregarlo a la lista de vertices
            //también se verifica que no ha sido visitado
            vertices.add(vertice);
            //Recorrer los adyacentes del vertice para obtener los no visitados.
            for (Arco<E, V> arco : vertice.getListaArcos()) {
                //Verificamos que no hayan sido visitados
                //Usamos arco.getDestino
                //¿Por qué? Porque necesito saber todos los vertices a los que
                // se unen cada uno.
                if (!arco.getDestino().isVistado()) {
                    arco.getDestino().setVistado(true);
                    cola.add(arco.destino);
                }

            }

        }
        return vertices;
    }

    // Método para resetear el estado de todos los vértices
    public void reiniciarVisitados() {
        for (Vertice<V, E> v : LVertices) {
            v.setVistado(false);
        }
    }

    //Método buscar y retornar primer no visitado
    //retorna null si al buscar en la lista de vertices no encuentra ninguno
    //que no haya sido visitado
    public Vertice<V, E> retornarNoVisitado() {
        for (Vertice<V, E> v : LVertices) {
            if (!v.isVistado()) {
                return v;
            }
        }
        return null;
    }

    //función que se encontraba dentro de las slides
    //los componentes forman una lista de listas
    //Luego de recorrer, obtendremos un conjunto de vertices
    //LRecorrido
    public boolean getConnectedComponents(LinkedList<LinkedList<Vertice<V, E>>> componentes) {
        Vertice<V, E> vertice;
        LinkedList<Vertice<V, E>> recorrido;
        while (true) {
            vertice = this.retornarNoVisitado();
            if (vertice == null) break;
            recorrido = this.recorridoEnAnchura(vertice);
            componentes.add(recorrido);
        }
        return componentes.size() == 1;
    }

    public Vertice<V, E> buscarVertice(V data) {
        for (Vertice<V, E> v : this.LVertices) {
            // Usamos .equals() porque 'data' es un objeto genérico (como un String)
            if (v.getContent().equals(data)) {
                return v;
            }
        }
        return null; // Si no lo encuentra
    }

    //todo : prim mal hecho jeje
//Tiene problemas de dirección de memoria, no funciona porque omití el primer paso
    //donde debo copiar todos los vertices del grafo
    public Grafo<V, E> prim(Vertice<V, E> verticeOrigen) {
        //Creo el grafo que voy a devolver
        Grafo<V, E> grafoPrim = new Grafo<>(this.esDirigido);
        //marco el vertice origen como visitado
        verticeOrigen.setVistado(true);
        //añadimos el vertice de origen al grafo a devolver
        grafoPrim.addVertice(verticeOrigen);
        //comparador de arcos
        ComparadorArco<E, V> cmp = new ComparadorArco<>();
        //heap para tomar siempre la menor ruta
        Heap<Arco<E, V>> cola = new Heap<>(true, cmp);
        //recorro todos los arcos del verticeOrigen y los encolo si no fueron visitados
        for (Arco<E, V> arco : verticeOrigen.getListaArcos()) {
            if (!arco.getDestino().isVistado()) {
                cola.encolar(arco);
            }
        }
        //bucle que se repite hasta que la cola este vacia
        while (!cola.datos.isEmpty()) {
            Arco<E, V> menor = cola.desencolar();
            // Si se nos acaban los caminos (ej. grafo desconexo), salimos.
            if (menor == null) {
                break;
            }
            //obtenemos el vertice destino
            Vertice<V, E> destino = menor.getDestino();
            //si el vertice no ha sido visitado entonces:
            if (!destino.isVistado()) {
                //la marco visitado
                destino.setVistado(true);
                //agrego el vertice al grafo que retorno
                grafoPrim.addVertice(destino);
                //agrego el arco
                grafoPrim.addArco(menor.data, menor.origen, menor.destino, menor.fpeso);
                //recorro la lista de arcos del vertice destino.
                for (Arco<E, V> arco : destino.getListaArcos()) {
                    if (!arco.getDestino().isVistado()) {
                        cola.encolar(arco);
                    }
                }
            }

        }
        //regreso el grafo
        return grafoPrim;
    }

    //todo : Prim IA
    public Grafo<V, E> primIA(Vertice<V, E> verticeOrigen) {
        // 1. Creamos el grafo nuevo
        Grafo<V, E> grafoPrim = new Grafo<>(this.esDirigido);

        // 2. CLONAMOS todos los vértices limpios (sin arcos) al nuevo grafo
        for (Vertice<V, E> vOriginal : this.LVertices) {
            grafoPrim.addVertice(new Vertice<>(vOriginal.getContent()));
        }

        // 3. Preparamos el terreno
        verticeOrigen.setVistado(true);
        ComparadorArco<E, V> cmp = new ComparadorArco<>();
        Heap<Arco<E, V>> cola = new Heap<>(true, cmp);

        // Encolamos los primeros arcos
        for (Arco<E, V> arco : verticeOrigen.getListaArcos()) {
            if (!arco.getDestino().isVistado()) {
                cola.encolar(arco);
            }
        }

        // 4. Variables para controlar el bucle
        int verticesVisitados = 1; // Ya visitamos el origen
        int totalVertices = this.LVertices.size();

        // 5. El motor de Prim
        while (verticesVisitados < totalVertices) {
            Arco<E, V> menor = cola.desencolar();

            // Protección contra grafos desconexos
            if (menor == null) break;

            Vertice<V, E> destinoOriginal = menor.getDestino();

            // Si NO ha sido visitado, lo conquistamos
            if (!destinoOriginal.isVistado()) {
                destinoOriginal.setVistado(true);
                verticesVisitados++; // Aumentamos el contador

                // --- LA MAGIA ESTÁ AQUÍ ---
                // Buscamos los vértices equivalentes (limpios) en el nuevo grafo
                Vertice<V, E> origenClon = grafoPrim.buscarVertice(menor.origen.getContent());
                Vertice<V, E> destinoClon = grafoPrim.buscarVertice(menor.destino.getContent());

                // Conectamos los clones en el nuevo grafo
                grafoPrim.addArco(menor.data, origenClon, destinoClon, menor.fpeso);

                // --------------------------

                // Buscamos nuevos caminos desde el recién conquistado
                for (Arco<E, V> arco : destinoOriginal.getListaArcos()) {
                    if (!arco.getDestino().isVistado()) {
                        cola.encolar(arco);
                    }
                }
            }
        }

        return grafoPrim;
    }

    @Override
    public String toString() {
        // Usamos StringBuilder por eficiencia al concatenar texto en bucles
        StringBuilder sb = new StringBuilder();

        // 1. Recorremos TODOS los vértices del grafo
        for (Vertice<V, E> v : this.LVertices) {
            // Asumiendo que tu Vértice tiene un método como getData() o getContenido()
            // para mostrar el valor del nodo (A, B, C, etc.)
            sb.append("Vértice ").append(v.getContent()).append(" -> ");

            // 2. Verificamos si tiene conexiones
            if (v.getListaArcos().isEmpty()) {
                sb.append("Sin conexiones");
            } else {
                // 3. Recorremos todos los arcos de este vértice en particular
                for (Arco<E, V> arco : v.getListaArcos()) {
                    sb.append("[")
                            .append(arco.getDestino().getContent()) // Mostramos hacia dónde va
                            .append(" (peso: ").append(arco.fpeso).append(")] ");
                }
            }
            sb.append("\n"); // Salto de línea para empezar el siguiente vértice en una fila nueva
        }

        return sb.toString();
    }


    //todo:siguiente reto Kruskal
    //Kruskal no lleva parámetros ¿por qué?
    //debido a que este utiliza los arcos no los vertices
    public Grafo<V, E> kruskal() {
        //Primero se añaden todos los vertices al arbol de expansión
        Grafo<V, E> grafoKruskal = new Grafo<>(this.esDirigido);
        //Se necesitará arco de menor peso, por lo que uso un Heap
        ComparadorArco<E, V> cmpArco = new ComparadorArco<>();
        Heap<Arco<E, V>> cola = new Heap<>(true, cmpArco);
        //Kruskal añade todos los arcos sin excepción al Heap
        //como tengo que si o si usar un for para hacer la copia del grafo
        //aprovecho y agrego otro para guardar todos los arcos de los vertices
        for (Vertice<V, E> vertice : this.LVertices) {
            grafoKruskal.addVertice(new Vertice<>(vertice.getContent()));
            for (Arco<E, V> arco : vertice.getListaArcos()) {
                if (!cola.datos.contains(arco)) {
                    cola.encolar(arco);
                }
            }
        }
        //Explicación: el algoritmo dice que el proceso de hacer puentes se repite n-1 veces.
        //¿Por qué tiene sentido?
        // Si quiero unir 4 islas necesito 3 puentes para hacerlo
        // 🏝️➡️🏝️
        //⬆️
        //🏝️⬅️🏝️
        int numeroVertices = grafoKruskal.LVertices.size();
        int puentes = 0;

        //Proceso iterativo
        while (puentes != numeroVertices - 1) {
            if (cola.datos.isEmpty()) break;
            //se elige el arco de menor peso
            Arco<E, V> menor = cola.desencolar();
            //Me toco aprender que es necesario hacer clones de vertices, para evitar los problemas de memoria ocurridos en prim
            Vertice<V, E> origenClon = grafoKruskal.buscarVertice(menor.origen.getContent());
            Vertice<V, E> destinoClon = grafoKruskal.buscarVertice(menor.destino.getContent());
            //que no haya sido elegido y no una dos vertices de una misma componente
            if (!grafoKruskal.existeCamino(origenClon, destinoClon)) {
                grafoKruskal.addArco(menor.getData(), origenClon, destinoClon, menor.getFpeso());
                puentes++;
            }
        }
        return grafoKruskal;
    }

    //este metodo sirve para kruskal para evitarme hacer un grafo cerrado
    //tengo que saber si existe camino entre un vertice de origen y otro de destino
    public boolean existeCamino(Vertice<V, E> origen, Vertice<V, E> destino) {
        //seguridad: reinicio visitados
        this.reiniciarVisitados();
        //hago un recorrido por anchura
        LinkedList<Vertice<V, E>> recorridoAnchura = this.recorridoEnAnchura(origen);
        //como devuelve una lista
        for (Vertice<V, E> vertice : recorridoAnchura) {
            if (vertice.getContent().equals(destino.getContent())) {
                return true;
            }
        }
        return false;
    }

    //todo Algoritmo de dijkstra: me permite encontrar el camino más corto hacia todos los nodos del grafo.
    //Fue necesario cambiar la estructura de la clase vertice, añadiendo 2 atributos.
    public HashMap<String, Integer> dijkstra(Vertice<V, E> origen) {

        //---------------------------------------
        //Esto no es parte del algoritmo, solo es para limpiar los vertices
        //vuelve a poner a todos los vertices como no visitados
        this.reiniciarVisitados();
        //Aqui limpiamos la distancia acumulada y el predecesor
        for (Vertice<V, E> v : this.LVertices) {
            v.cumulativeDistance = Integer.MAX_VALUE;
            v.predecessor = null;
        }
        //-----------------------------------------

        //clase comparador de vertices, usará la distancia acumulativa que es int
        ComparadorVertice<V, E> comparadorVertice = new ComparadorVertice<>();
        //Heap para los vertices, ordenados por los menores
        Heap<Vertice<V, E>> colaVertices = new Heap<>(true, comparadorVertice);
        HashMap<String, Integer> map = new HashMap<>();
        //Preparar el terreno, actualmente los vertices tienen la distancia infinita
        //El vertice de orige será el primero en cambiar su distancia acumulada
        //ya que es el punto de partida, por lo tanto, es 0.
        origen.cumulativeDistance = 0;

        //encolamos el nodo de partida
        colaVertices.encolar(origen);

        //inicia el proceso iterativo
        while (!colaVertices.datos.isEmpty()) {
            //desencolamos el vertie que esté menos lejos
            Vertice<V, E> vertice = colaVertices.desencolar();
            //se lo marca como visitado
            vertice.setVistado(true);

            map.put((String) vertice.getContent(), vertice.cumulativeDistance);
            //Paso 3
            //recorremos sus vertices adyacentes que no han sido visitados
            //Para hacer eso tengo que hacerlo desde los arcos.
            for (Arco<E, V> arco : vertice.getListaArcos()) {
                //Guardo la variable de destino del arco
                Vertice<V, E> vDestino = arco.destino;
                //Pregunto si no ha sido visitado
                if (!vDestino.isVistado()) {
                    //Importante: esta parte no lo explica la diapositiva bien.

                    //Necesito calcular una nueva distancia.
                    //Actualmente, todos los vertices menos origen tienen una distancia infinita
                    //entonces, debo calcular si esta nueva distancia es menor que la distancia acumulada de los vertices

                    //La calculo de la siguiente manera, la distancia acumulada del vertice
                    //en la primera iteración es Origen, por lo tanto, es 0, sumado a el fpeso que es entero también
                    //Ejemplo con el vertice Origen: nuevaDistancia = 0+2 = 2
                    int nuevaDistancia = vertice.cumulativeDistance + arco.fpeso;

                    //Ahora para asegurarme que encuentro una mejor ruta debo preguntar
                    //Si la nueva distancia es menor que la distancia acumulada.
                    //Siguiente el ejemplo anterior: if(2<∞)
                    if (nuevaDistancia < vDestino.cumulativeDistance) {
                        //si se cumple entonces, le asigno esta nueva distancia al vDestino
                        vDestino.cumulativeDistance = nuevaDistancia;
                        //le asigno su predecessor el vertice desencolado
                        vDestino.predecessor = vertice;
                        //encolo el vertice destino
                        colaVertices.encolar(vDestino);
                    }
                }
            }
            //El while se repite hasta que no haya nada en cola.
        }
        return map;
    }

    //Ejercicio tipo examen: matriz de adyacencia de un grafo lista
    public GraphMatrix<V, E> aGrafoMatriz() {
        GraphMatrix<V, E> grafoMatriz = new GraphMatrix<>(this.LVertices.size());
        grafoMatriz.setNnodes(this.LVertices.size());
        //llenar V Nodes[]
        int i = 0;
        for (Vertice<V, E> vertice : this.LVertices) {
            grafoMatriz.getNodes()[i] = vertice.getContent();
            i++;
        }
        i = 0;
        for (Vertice<V, E> vertice : this.LVertices) {
            for (Arco<E, V> arco : vertice.getListaArcos()) {
                //índice destino, uso una función search para conseguirlo
                int iDestino = grafoMatriz.search(arco.destino);
              //devuelve -1 si no encuentra el vertices, entonces pongo una condición de seguridad
                if (iDestino!=-1){
                    //llenar pesos Adyacencia[][]
                    grafoMatriz.getPesosAdyacencia()[i][iDestino] = arco.fpeso;
                    //llenar contenido de Adyacencia[][]
                    grafoMatriz.getContAdyacencia()[i][iDestino] = arco.data;
                }

            }
            i++;
        }
        return grafoMatriz;
    }
}



