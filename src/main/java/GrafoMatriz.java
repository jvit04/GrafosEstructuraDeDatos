import java.lang.reflect.Array;
import java.util.ArrayList;

public class GrafoMatriz<E> {
    private ArrayList<E> vertices;
    private int max;
    private int n; //número de vertices
    private boolean[][] arcos;
    private boolean esDirigido;


    public GrafoMatriz(int max, boolean esDirigido) {
        this.max = max;
        this.esDirigido = esDirigido;
        this.n = 0;
        arcos = new boolean[max][max];
        vertices = new ArrayList<>(max);
    }
        public void addVertice(E contenido){
        if(n!= max){
            vertices.add(contenido);
            n++;
        }
        }
        public void addArco(int contVOrigen, int contVDestino) {
            int iO = contVOrigen;
            int iD = contVDestino;
            arcos[iO][iD] = true;
            if (!this.esDirigido) {
                this.arcos[iD][iO] = true;
            }
        }

        public void imprimirGrafo(){
                System.out.println(vertices);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j <n ; j++) {
                        if(arcos[i][j]){
                            System.out.println("Hay Arco entre " + vertices.get(i) + " " + vertices.get(j));
                        }

                    }

                }
            }
        }




