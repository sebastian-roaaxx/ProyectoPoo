package modelo;

public class Tecnico {

    protected String nombre;
    protected int experiencia;
    protected double tarifa;

    public Tecnico(String nombre, int experiencia, double tarifa) {
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.tarifa = tarifa;
    }

    // SOBRECARGA CONSTRUCTOR
    public Tecnico(String nombre) {
        this(nombre, 1, 30000);
    }

    public double getTarifa() { return tarifa; }
    public int getExperiencia() { return experiencia; }
    public String getNombre() { return nombre; }

    public void reparar() {
        System.out.println(nombre + " reparando...");
    }
}