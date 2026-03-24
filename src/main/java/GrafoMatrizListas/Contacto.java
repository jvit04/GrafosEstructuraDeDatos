package GrafoMatrizListas;

public class Contacto {

    private String nombre;
    private String apellido;
    private String apodo;
    private String telefono;
    private int frecuencia;

    public Contacto(String nombre, String apellido, String apodo, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.telefono = telefono;
        this.frecuencia = 0;
    }


    public boolean equals(Contacto c) {
        if(this.nombre.equals(c.nombre)&&this.telefono.equals(c.telefono)){
            return true;
        }
        return false;
    }

    public String getNombre(){
        return nombre;
    }

    public String getTelefono(){
        return telefono;
    }
    public int getFrecuencia() {return frecuencia;}
    public void setFrecuencia(int f){ frecuencia=f;}
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return nombre + " - " + telefono;
    }
}