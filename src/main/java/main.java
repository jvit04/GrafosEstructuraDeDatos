import GrafoMatrizListas.Contacto;

public class main {
    public static void main(String[] args) {
        GrafoMatriz<Contacto> grafo = new GrafoMatriz<>(5,true);
        grafo.addVertice(new Contacto("Juan", "Perez","Caballo","0987456321"));
        grafo.addVertice(new Contacto("Luis", "Perez","lucho","0987456321"));
        grafo.addVertice(new Contacto("Carlos", "Perez","char","0987456321"));
        grafo.addVertice(new Contacto("Javier", "Perez","calvo","0987456321"));
        grafo.addVertice(new Contacto("Hugo", "Perez","buho","0987456321"));
        grafo.addArco(0,1);
        grafo.addArco(0,4);
        grafo.addArco(4,2);
        grafo.addArco(4,1);
        grafo.imprimirGrafo();
    }
}
