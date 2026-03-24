package GrafoMatrizListas;

public class Amistad {
    private int años;
    private int nivel;

    public Amistad(int años, int nivel) {
        this.años = años;
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getAños() {
        return años;
    }

    public void setAños(int años) {
        this.años = años;
    }

    @Override
    public String toString() {
        return "Años: " + años + "Nivel: " + nivel;
    }
}
