package ClasesAbstractas;

import Interfaces.Mantenible;

public abstract class Equipo implements Mantenible {
    protected String nombre;
    protected String estado;
    protected String tipo; // Nueva: tipo de equipo

    public Equipo(String nombre, String tipo) {
        this.nombre = nombre;
        this.estado = "Registrado";
        this.tipo = tipo;
    }

    public abstract void tipo();

    public String getTipo() {
        return tipo;
    }

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