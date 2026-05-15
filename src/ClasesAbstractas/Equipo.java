package ClasesAbstractas;

import Interfaces.Mantenible;

public abstract class Equipo implements Mantenible { // ESTA CLASE ES PADRE
    protected String nombre;
    protected String estado;
    protected String tipo; // Nueva: tipo de equipo

    public Equipo(String nombre, String tipo) {
        this.nombre = nombre;
        this.estado = "Registrado";
        this.tipo = tipo;
    }

    public abstract void tipo();

   
    public void iniciarMantenimiento() { // ESTA ES HEREDADA DE LA INTERFAZ MANTENIBLE 
                                        // TODOS LOS EQUIPOS PUEDEN INICIAR MANTENIMIENTO, ASI QUE SE IMPLEMENTA AQUI
        estado = "En mantenimiento";
    }

    public void finalizarMantenimiento() { // ESTA ES HEREDADA DE LA INTERFAZ MANTENIBLE
                                           // TODOS LOS EQUIPOS PUEDEN FINALIZAR MANTENIMIENTO, ASI QUE SE IMPLEMENTA AQUI
   estado = "Listo";
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

}