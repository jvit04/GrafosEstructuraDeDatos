package GrafoMatrizListas;

public class GraphMatrix<V, E> {

    private V[] Nodes; // Contenido de los nodos
    private int[][] pesosAdyacencia; // -1 cuando no hay arco, mayor a 0 cuando hay arco
    private E[][] contAdyacencia; // null cuando no hay arco, contenido cuando hay arco
    private int max; // maximo numero de nodos en el grafo
    private int nnodes; // numero de nodos actual en el grafo

    /**
     * Constructor que inicializa el grafo con un tamaño máximo.
     * @param max El número máximo de nodos que soportará la matriz.
     */
    @SuppressWarnings("unchecked")
    public GraphMatrix(int max) {
        this.max = max;
        this.nnodes = 0;

        // Inicialización de arreglos genéricos mediante casteo
        this.Nodes = (V[]) new Object[max];
        this.contAdyacencia = (E[][]) new Object[max][max];

        // Inicialización de la matriz de enteros
        this.pesosAdyacencia = new int[max][max];

        // Rellenamos la matriz de pesosAdyacencia con -1 por defecto (sin arcos)
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                this.pesosAdyacencia[i][j] = -1;
            }
        }
    }

    // --- Métodos de utilidad básicos sugeridos por el enunciado ---

    /**
     * Agrega un nuevo nodo al arreglo de Nodos y aumenta el contador.
     * Te será útil al recorrer la lista de adyacencia.
     */
    public void addNode(V content) {
        if (this.nnodes < this.max) {
            this.Nodes[this.nnodes] = content;
            this.nnodes++;
        }
    }

    // --- Getters y Setters ---

    public V[] getNodes() {
        return Nodes;
    }

    public void setNodes(V[] nodes) {
        Nodes = nodes;
    }

    public int[][] getPesosAdyacencia() {
        return pesosAdyacencia;
    }

    public void setPesosAdyacencia(int[][] pesosAdyacencia) {
        this.pesosAdyacencia = pesosAdyacencia;
    }

    public E[][] getContAdyacencia() {
        return contAdyacencia;
    }

    public void setContAdyacencia(E[][] contAdyacencia) {
        this.contAdyacencia = contAdyacencia;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getNnodes() {
        return nnodes;
    }

    public void setNnodes(int nnodes) {
        this.nnodes = nnodes;
    }

    @Override
    public String toString() {
        // Usamos StringBuilder por eficiencia al concatenar texto en bucles
        StringBuilder sb = new StringBuilder();

        // Recorremos solo los nodos que realmente existen en el grafo (hasta nnodes)
        for (int i = 0; i < this.nnodes; i++) {
            sb.append("Vértice ").append(this.Nodes[i]).append(" -> ");

            boolean tieneConexiones = false;

            // Revisamos la fila 'i' de la matriz para ver con quién está conectado
            for (int j = 0; j < this.nnodes; j++) {
                // Según el enunciado, -1 significa que no hay arco
                if (this.pesosAdyacencia[i][j] != -1) {
                    sb.append("[")
                            .append(this.Nodes[j]) // Hacia dónde va
                            .append(" (peso: ").append(this.pesosAdyacencia[i][j]).append(")] ");
                    tieneConexiones = true;
                }
            }

            if (!tieneConexiones) {
                sb.append("Sin conexiones");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public int search(Vertice<V,E> vertice){
        for (int i = 0; i < Nodes.length; i++) {
            if(vertice.getContent().equals(this.Nodes[i])) return i;
        }
        return -1;
    }
}