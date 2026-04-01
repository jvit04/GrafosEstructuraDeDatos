package GrafoMatrizListas;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<E> {
    ArrayList<E> datos;
    int max;
    int n;
    boolean asc;
    Comparator<E> cmp;

    public Heap(int max, boolean asc, Comparator<E> cmp) {
        this.max = max;
        this.asc = asc;
        this.n = 0;
        this.cmp = cmp;
        datos = new ArrayList<>(max);
    }

    public Heap(boolean asc, Comparator<E> cmp) {
        this.max = 0;
        this.asc = asc;
        this.n = 0;
        this.cmp = cmp;
        datos = new ArrayList<>(max);
    }



    /**
     * Calcula el índice del hijo izquierdo de un nodo dado.
     * @param r El índice del nodo padre.
     * @return El índice del hijo izquierdo, o -1 si no existe.
     */
    public int indiceHijoIzq(int r){
        int valor = r*2+1;
        if (valor >= n) return -1;
        return valor;
    }
    /**
     * Calcula el índice del hijo derecho de un nodo dado.
     * @param r El índice del nodo padre.
     * @return El índice del hijo derecho, o -1 si no existe.
     */
    public int indiceHijoDer(int r){
        int valor= 2*r+2;
        if(valor>=n) return -1;
        return valor;
    }

    /**
     * Determina si un nodo específico es una hoja (no tiene hijos).
     * @param i El índice del nodo a evaluar.
     * @return true si es nodo hoja, false en caso contrario.
     */
    public boolean esHoja(int i){
        return (indiceHijoIzq(i)==-1&& indiceHijoDer(i)==-1);
    }

    /**
     * Encuentra el índice del elemento mayor entre un nodo padre y sus dos hijos.
     */
    private int getMayor(int raiz, int izq, int der){
        int mayor = raiz;
        // Compara con el hijo izquierdo si existe (!= -1)
        if(izq != -1 && cmp.compare(datos.get(mayor), datos.get(izq)) < 0){
            mayor = izq;
        }
        //Compara con el hijo derecho si existe (!= -1)
        if(der != -1 && cmp.compare(datos.get(mayor), datos.get(der)) < 0){
            mayor = der;
        }
        return mayor;
    }
    /**
     * Encuentra el índice del elemento menor entre un nodo padre y sus dos hijos.
     */
    private int getMenor(int raiz, int izq, int der){
        int menor = raiz;
        // Compara con el hijo izquierdo si existe (!= -1)
        if(izq != -1 && cmp.compare(datos.get(menor), datos.get(izq)) > 0){
            menor = izq;
        }
        // Compara con el hijo derecho si existe (!= -1)
        if(der != -1 && cmp.compare(datos.get(menor), datos.get(der)) > 0){
            menor = der;
        }
        return menor;
    }

    /**
     * Evalúa un nodo y sus hijos para determinar cuál debe tomar la posición de la raíz
     * basándose en si el Heap es ascendente (Min-Heap) o descendente (Max-Heap).
     */
    private int revisar(int raiz, int izq, int der){
        if(asc){
            // Ascendente = Queremos el más pequeño en la raíz (Min-Heap)
            return getMenor(raiz, izq, der);
        }
        else {
            // Descendente = Queremos el más grande en la raíz (Max-Heap)
            return getMayor(raiz, izq, der);
        }
    }

    /**
     * Ajusta recursivamente el árbol hacia abajo (hundimiento) para mantener
     * las propiedades del Heap desde un índice específico.
     * @param iDanno El índice del nodo que se sospecha que necesita ajuste.
     *               Danno = Daño en italiano.
     */
    private void autoAjuste(int iDanno){
        //revisar raiz, izq y der quien tiene el mayor o el menor
        //dependiendo el criterio
        //¿Qué es iMayorOMenor?
        //Variable que consigue quién deberia ser el mayor o menor del heap
        //recordemos que la raiz debería tener esta característica
        //de ser el mayor o menor.
        int iMayorOMenor = revisar(iDanno, this.indiceHijoIzq(iDanno), this.indiceHijoDer(iDanno));
        //Comparo mis sospechas, si iDanno es igual a quién debe iMayorOMenor
        //no hay problema
        if(iDanno == iMayorOMenor) return;
        //si no, intercambio, hasta que todo este en orden
        intercambio(iDanno, iMayorOMenor);
        //repito todo otra vez pero ahora por el lado del intercambio
        autoAjuste(iMayorOMenor);
    }
    /**
     * Intercambia la posición de dos elementos dentro del arreglo del Heap.
     * @param x Índice del primer elemento.
     * @param y Índice del segundo elemento.
     */
    public void intercambio(int x, int y){
        E tmp;
        tmp =datos.get(x);
        datos.set(x,datos.get(y));
        datos.set(y,tmp);
    }
    /**
     * Este metodo se lo trabajo en clase.
     * Construye un Heap a partir de una lista desordenada de elementos.
     * Utiliza el algoritmo de Floyd para la construcción en tiempo óptimo O(n).
     * @param Amalo Lista inicial de elementos sin orden.
     */
    public void construirHeap(ArrayList<E> Amalo){
        this.datos = new ArrayList<>(Amalo);
        this.n = datos.size();
        // Se ajustan los nodos desde el último padre hasta la raíz
        for (int i = n/2 - 1; i >= 0; i--) {
            this.autoAjuste(i);
        }
    }

    /**
     * Extrae y retorna el elemento en la cima del Heap (máximo o mínimo).
     * Posteriormente, reajusta la estructura para mantener la propiedad del Heap.
     * @return El elemento extraído, o null si el Heap está vacío.
     */
    public E desencolar(){
        //caso base: si este vacío el heap
        if(n==0) return null;
        E eliminado = datos.getFirst();
        //caso base 2: solo hay un elemento, no hay que ajustar
        if (n == 1) {
            datos.removeLast();
            n--;
            return eliminado;
        }
        //eliminado normal
        datos.set(0,datos.getLast());
        datos.removeLast();
        n--;
        // Se reajusta el árbol desde la nueva raíz
        this.autoAjuste(0);
        return eliminado;
    }

    /**
     * Imprime en consola el recorrido en inorden del Heap.
     */
    public void inOrden(){
        System.out.println(this.inOrdenLogica(0));
    }
    /**
     * Genera una lista con los elementos del Heap siguiendo un recorrido en inorden.
     * @param i El índice inicial del recorrido (generalmente 0 para la raíz).
     * @return ArrayList con los elementos en el orden recorrido.
     */
    private ArrayList<E> inOrdenLogica(int i){
        //caso base 1:  si i supera a n o es -1
        if(i>=n || i==-1) return new ArrayList<>();

        ArrayList<E> inOrder = new ArrayList<>();
        inOrder.addAll(this.inOrdenLogica(this.indiceHijoIzq(i)));
        inOrder.add(datos.get(i));
        inOrder.addAll(this.inOrdenLogica(this.indiceHijoDer(i)));

        return inOrder;
    }

    /**
     * Retorna una lista con todos los nodos hoja del Heap actual.
     * @return ArrayList con los elementos que son hojas.
     */
    public ArrayList<E> nodosHoja(){
        ArrayList<E> hojas = new ArrayList<>();
        //Matematicamente los nodos hojas estarán desde la segunda mitad del árbol
        for (int i = n/2; i < n; i++) {
            hojas.add(datos.get(i));
        }
        return hojas;
    }

    /**
     * Calcula el índice del nodo padre de un nodo dado.
     * @param i El índice del nodo hijo.
     * @return El índice del nodo padre.
     */
    public int indicePadre(int i){
        if (i == 0) return -1; // La raíz no tiene padre
        return (i - 1) / 2;
    }

    /**
     * Inserta (encola) un nuevo elemento en el Heap manteniendo las propiedades.
     * @param elemento El elemento a insertar en el Heap.
     */
    public void encolar(E elemento) {
        // 1. Añadimos el elemento al final del ArrayList y actualizamos el tamaño
        datos.add(elemento);
        int indiceActual = n;
        n++;

        // 2. Hacemos que el elemento "flote" hacia su posición correcta
        ajusteHaciaArriba(indiceActual);
    }

    /**
     * Ajusta iterativamente el árbol hacia arriba (flotar) para mantener
     * la propiedad del Heap (Min-Heap o Max-Heap) tras una inserción.
     * @param i El índice del nodo que acaba de ser insertado.
     */
    private void ajusteHaciaArriba(int i) {
        int padre = indicePadre(i);

        // Mientras no lleguemos a la raíz y tengamos un padre válido
        while (i > 0) {
            boolean debeSubir = false;

            if (asc) {
                // Criterio Ascendente (Min-Heap): Sube si el actual es MENOR que su padre
                if (cmp.compare(datos.get(i), datos.get(padre)) < 0) {
                    debeSubir = true;
                }
            } else {
                // Criterio Descendente (Max-Heap): Sube si el actual es MAYOR que su padre
                if (cmp.compare(datos.get(i), datos.get(padre)) > 0) {
                    debeSubir = true;
                }
            }

            // Si cumple la condición para subir, intercambiamos y seguimos evaluando
            if (debeSubir) {
                intercambio(i, padre);
                i = padre; // Ahora nuestro nodo está en la posición del padre
                padre = indicePadre(i); // Calculamos el nuevo padre para la siguiente vuelta
            } else {
                // Si no debe subir, ya encontró su lugar perfecto y rompemos el ciclo
                break;
            }
        }
    }

}
