package modelo;
public class Tecnico extends Persona {

    private String especialidad;

    public Tecnico(String nombre, String id, String especialidad) {
        super(nombre, id);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void reparar() {
        System.out.println(nombre + " está reparando...");
    }
}