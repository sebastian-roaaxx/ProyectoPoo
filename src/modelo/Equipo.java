
package modelo;
public class Equipo implements Mantenible {

    private String nombre;

    private String estado;

    // Constructor de la clase, recibe el nombre del equipo
    public Equipo(String nombre) {
        this.nombre = nombre;
        // Inicializamos el estado como "Disponible" por defecto
        this.estado = "Disponible";
    }

    // Método getter para obtener el nombre del equipo
    public String getNombre() {
        return nombre;
    }

    // Método getter para obtener el estado actual del equipo
    public String getEstado() {
        return estado;
    }

    // Sobrescribimos el método iniciarMantenimiento de la interfaz Mantenible
    @Override
    public void iniciarMantenimiento() {
        estado = "En mantenimiento";
        System.out.println(nombre + " ahora está en mantenimiento");
    }

    // Sobrescribimos el método finalizarMantenimiento de la interfaz Mantenible
    @Override
    public void finalizarMantenimiento() {
        // Cambiamos el estado nuevamente a "Disponible"
        estado = "Disponible";
        System.out.println(nombre + " mantenimiento finalizado");
    }
}