package modelo;
public class Persona {
    protected String nombre;
    protected String id;

    public Persona(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }    

    public String getId() {
        return id;
    }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre + " | ID: " + id);
    }
}
