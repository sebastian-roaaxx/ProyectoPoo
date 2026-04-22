package modelo;

public class Tecnico {

    protected String nombre;
    protected int experiencia;
    protected double tarifa;
    protected String especialidad; // Nueva: especialidad del técnico

    public Tecnico(String nombre, int experiencia, double tarifa, String especialidad) {
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.tarifa = tarifa;
        this.especialidad = especialidad;
    }

    // SOBRECARGA CONSTRUCTOR
    public Tecnico(String nombre) {
        this(nombre, 1, 30000, "General");
    }

    public double getTarifa() { return tarifa; }
    public int getExperiencia() { return experiencia; }
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }

    public void reparar() {
        System.out.println(nombre + " reparando...");
    }
}