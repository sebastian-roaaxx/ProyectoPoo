package modelo;

public class Especialista extends Tecnico {

    public Especialista(String nombre, int experiencia, double tarifa) {
        super(nombre, experiencia, tarifa);
    }

    @Override
    public void reparar() {
        System.out.println(nombre + " (especialista) reparando...");
    }
}