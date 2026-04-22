package ClasesAbstractas;

import Interfaces.Mantenible;

public abstract class Equipo implements Mantenible {
    protected String nombre;
    protected String estado;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.estado = "Registrado";
    }

    public abstract void tipo();

    public void iniciarMantenimiento() {
        estado = "En mantenimiento";
    }

    public void finalizarMantenimiento() {
        estado = "Listo";
    }

    public String getNombre() {
        return nombre;
    }
}