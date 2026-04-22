package modelo;

import ClasesAbstractas.*;
import Interfaces.*;

public class OrdenMantenimiento extends OrdenBase
implements Asignable, Calculable, Priorizable, Reportable {

    private Usuario cliente;
    private Equipo equipo;
    private Tecnico tecnico;
    private double presupuesto;
    private String prioridad = "Media";

    public OrdenMantenimiento(Usuario c, Equipo e, double p) {
        cliente = c;
        equipo = e;
        presupuesto = p;
    }

    // SOBRECARGA
    public OrdenMantenimiento(Usuario c, Equipo e) {
        this(c, e, 0);
    }

    public void asignarTecnico(Tecnico t) {
        if (t == null) throw new IllegalArgumentException("Sin técnico");
        tecnico = t;
    }

    public void iniciar() {
        if (tecnico == null) {
            System.out.println("No hay técnico");
            return;
        }
        estado = "En proceso";
        equipo.iniciarMantenimiento();
        tecnico.reparar();
    }

    // SOBRECARGA
    public void iniciar(String prioridad) {
        this.prioridad = prioridad;
        iniciar();
    }

    public void finalizar() {
        estado = "Finalizado";
        equipo.finalizarMantenimiento();
    }

    public double calcularCosto() {
        return presupuesto;
    }

    public void setPrioridad(String p) {
        prioridad = p;
    }

    public void generarReporte() {
        System.out.println("Orden de " + cliente.getNombre() + " estado: " + estado);
    }

    public String getEstado() {
        return estado;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Equipo getEquipo() {
        return equipo;
    }
}