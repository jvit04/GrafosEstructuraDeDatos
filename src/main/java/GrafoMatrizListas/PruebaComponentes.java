package GrafoMatrizListas;
import java.util.LinkedList;

public class PruebaComponentes {
    public static void main(String[] args) {
        // 1. Creamos un grafo NO dirigido (false)
        Grafo<String, Integer> grafo = new Grafo<>(false);

        // 2. Creamos 5 vértices (A, B, C, D, E)
        Vertice<String, Integer> vA = new Vertice<>("A");
        Vertice<String, Integer> vB = new Vertice<>("B");
        Vertice<String, Integer> vC = new Vertice<>("C");
        Vertice<String, Integer> vD = new Vertice<>("D");
        Vertice<String, Integer> vE = new Vertice<>("E");

        grafo.addVertice(vA); grafo.addVertice(vB); grafo.addVertice(vC);
        grafo.addVertice(vD); grafo.addVertice(vE);

        // 3. Unimos los vértices creando dos "islas" separadas
        // Componente 1: A unido con B, y B unido con C
        grafo.addArco(0, vA, vB, 0);
        grafo.addArco(0, vB, vC, 0);

        // Componente 2: D unido solo con E
        grafo.addArco(0, vD, vE, 0);

        // 4. Asegurarnos de que todos los nodos estén limpios antes de recorrer
        grafo.reiniciarVisitados(); // El método que creamos en el paso anterior

        // 5. Instanciar la lista de listas que almacenará las componentes
        LinkedList<LinkedList<Vertice<String, Integer>>> componentes = new LinkedList<>();

        // 6. Ejecutar tu función
        boolean esConexo = grafo.getConnectedComponents(componentes);

        // 7. Imprimir los resultados
        System.out.println("--- Resultados de Componentes Conexas ---");
        System.out.println("¿El grafo completo es conexo?: " + esConexo);
        System.out.println("Cantidad de componentes (islas) encontradas: " + componentes.size());

        int contador = 1;
        for (LinkedList<Vertice<String, Integer>> componente : componentes) {
            System.out.println("Componente " + contador + ": " + componente);
            contador++;
        }
    }
}