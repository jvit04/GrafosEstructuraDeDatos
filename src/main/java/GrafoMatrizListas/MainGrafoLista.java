package GrafoMatrizListas;

public class MainGrafoLista {
    public static void main(String[] args) {
        // Grafo dirigido como se muestra en las flechas de la imagen
        Grafo<String, Integer> G = new Grafo<>(true);

        // Creamos los vértices con las letras de la imagen
        Vertice<String, Integer> vA = new Vertice<>("A");
        Vertice<String, Integer> vB = new Vertice<>("B");
        Vertice<String, Integer> vC = new Vertice<>("C");
        Vertice<String, Integer> vD = new Vertice<>("D");
        Vertice<String, Integer> vH = new Vertice<>("H");
        Vertice<String, Integer> vR = new Vertice<>("R");
        Vertice<String, Integer> vT = new Vertice<>("T");

        // Agregamos todos los vértices
        G.addVertice(vA); G.addVertice(vB); G.addVertice(vC);
        G.addVertice(vD); G.addVertice(vH); G.addVertice(vR);
        G.addVertice(vT);

        // Agregamos las aristas respetando la dirección de las flechas:
        G.addArco(1, vD, vB, 0); // D -> B
        G.addArco(1, vD, vC, 0); // D -> C
        G.addArco(1, vC, vR, 0); // C -> R
        G.addArco(1, vR, vH, 0); // R -> H
        G.addArco(1, vH, vD, 0); // H -> D
        G.addArco(1, vH, vA, 0); // H -> A
        G.addArco(1, vH, vT, 0); // H -> T
        G.addArco(1, vB, vH, 0); // B -> H

        // Imprimir estructura
        System.out.println("--- Estructura del Grafo ---");
        G.imprimirGrafo();

        System.out.println("\n--- Recorrido en Anchura (DFS) desde D ---");
        System.out.println(G.recorridoEnAnchura(vD));

        G.reiniciarVisitados();

        // Según tu imagen, el recorrido empieza en D
        System.out.println("\n--- Recorrido en Profundidad (DFS) desde D ---");
        System.out.println("Resultado: " + G.recorridoProfundo(vD));

    }
}