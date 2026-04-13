package modelo;
public class Equipo implements Mantenible {
    private String nombre;
    private String estado;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.estado = "Disponible";
    }

    public String getNombre() {
    return nombre;
}

    public String getEstado() {
        return estado;
    }

    @Override
    public void iniciarMantenimiento() {
        estado = "En mantenimiento";
        System.out.println(nombre + " ahora está en mantenimiento");
    }

    @Override
    public void finalizarMantenimiento() {
        estado = "Disponible";
        System.out.println(nombre + " mantenimiento finalizado");
    }
}
