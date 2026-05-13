package modelo;

public class Especialista extends Tecnico {

    public Especialista(String nombre, int experiencia, double tarifa, String especialidad) {
        super(nombre, experiencia, tarifa, especialidad);
    }

    // SOBRECARGA
    public Especialista(String nombre, String especialidad) {
        super(nombre, 5, 50000, especialidad); // Mayor experiencia y tarifa
    }

    @Override
    public void reparar() {
        System.out.println(nombre + " (especialista) reparando...");
    }
}