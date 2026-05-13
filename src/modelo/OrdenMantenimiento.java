
package modelo;

public class OrdenMantenimiento {


    private Equipo equipo;

    private Tecnico tecnico;

    
    private String estado;

    // Constructor de la clase, recibe un equipo y un técnico
    public OrdenMantenimiento(Equipo equipo, Tecnico tecnico) {
        this.equipo = equipo;
        this.tecnico = tecnico;
        // Inicializamos el estado de la orden como "Pendiente"
        this.estado = "Pendiente";
    }

    // Método getter para obtener el estado actual de la orden
    public String getEstado() {
        return estado;
    }

    // Método para iniciar el mantenimiento
    public void iniciar() {
        // Cambiamos el estado de la orden a "En proceso"
        estado = "En proceso";
        equipo.iniciarMantenimiento();

        // Llamamos al método del técnico para que realice la reparación
        tecnico.reparar();
    }

    // Método para finalizar el mantenimiento
    public void finalizar() {
        // Cambiamos el estado de la orden a "Finalizado"
        estado = "Finalizado";
        // Llamamos al método del equipo para finalizar su mantenimiento
        equipo.finalizarMantenimiento();
    }

    // Método getter para obtener el equipo asociado a la orden
    public Equipo getEquipo() {
        return equipo;
    }

    // Método getter para obtener el técnico asociado a la orden
    public Tecnico getTecnico() {
        return tecnico;
    }
}