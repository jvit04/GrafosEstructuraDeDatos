package GrafoMatrizListas;

import java.util.HashMap;

public class MainGrafoLista {
    public static void main(String[] args) {
        // 1. IMPORTANTE: Prim trabaja sobre grafos NO dirigidos (false)
        Grafo<String, Integer> G = new Grafo<>(false);

        // Creamos los vértices (usaremos 5 letras para un ejemplo claro)
        Vertice<String, Integer> vA = new Vertice<>("A");
        Vertice<String, Integer> vB = new Vertice<>("B");
        Vertice<String, Integer> vC = new Vertice<>("C");
        Vertice<String, Integer> vD = new Vertice<>("D");
        Vertice<String, Integer> vE = new Vertice<>("E");

        // Agregamos todos los vértices al grafo original
        G.addVertice(vA); G.addVertice(vB); G.addVertice(vC);
        G.addVertice(vD); G.addVertice(vE);

        // 2. Agregamos las aristas con PESOS REALES (fpeso)
        // Estructura: addArco(datosArco, origen, destino, peso)
        G.addArco(1, vA, vB, 2);
        G.addArco(2, vA, vD, 6);
        G.addArco(3, vB, vC, 3);
        G.addArco(4, vB, vD, 8);
        G.addArco(5, vB, vE, 5);
        G.addArco(6, vC, vE, 7);
        G.addArco(7, vD, vE, 9);

        // Imprimir estructura original usando tu toString() o imprimirGrafo()
        System.out.println("--- Estructura del Grafo Original ---");
        System.out.println(G); // o G.imprimirGrafo();

        System.out.println("\n--- Recorrido en Anchura desde A ---");
        System.out.println(G.recorridoEnAnchura(vA));

        // Reiniciamos los visitados para el siguiente recorrido
        G.reiniciarVisitados();

        System.out.println("\n--- Recorrido en Profundidad desde A ---");
        System.out.println("Resultado: " + G.recorridoProfundo(vA));

        // 3. CRÍTICO: Reiniciar los visitados antes de llamar a Prim
        G.reiniciarVisitados();

        System.out.println("\n--- Árbol de Expansión Mínima (PRIM) desde A---");
        Grafo<String, Integer> arbolMinimo = G.primIA(vA);

        // Imprimimos el grafo resultante usando el toString que creamos
        System.out.println(arbolMinimo.toString());

        System.out.println("\n--- Árbol de Expansión Kruskal---");
        Grafo<String,Integer> grafoKruskal = G.kruskal();

        System.out.println(grafoKruskal.toString());

        System.out.println("\n--- Caminos más cortos desde A (DIJKSTRA) ---");
// Llamamos al método y guardamos el mapa resultante
        HashMap<String, Integer> distancias = G.dijkstra(vA);

// Imprimimos el mapa para ver los resultados
        for (String destino : distancias.keySet()) {
            System.out.println("Distancia mínima hacia " + destino + ": " + distancias.get(destino));
        }
    }
}