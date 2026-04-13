package modelo;
public class OrdenMantenimiento {
    private Equipo equipo;
    private Tecnico tecnico;
    private String estado;

    public OrdenMantenimiento(Equipo equipo, Tecnico tecnico) {
        this.equipo = equipo;
        this.tecnico = tecnico;
        this.estado = "Pendiente";
    }
    public String getEstado() {
        return estado;
    }

    public void iniciar() {
        estado = "En proceso";
        equipo.iniciarMantenimiento();
        tecnico.reparar();
    }

    public void finalizar() {
        estado = "Finalizado";
        equipo.finalizarMantenimiento();
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }
}
