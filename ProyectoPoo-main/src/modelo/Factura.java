package modelo;

import Interfaces.Calculable;
import Interfaces.Reportable;

public class Factura implements Calculable, Reportable {

    private double costo;

    public Factura(double costo) {
        this.costo = costo;
    }

    // SOBRECARGA
    public Factura() {
        this(0);
    }

    public double calcularCosto() {
        return costo;
    }

    // SOBRECARGA
    public double calcularCosto(double descuento) {
        return costo - descuento;
    }

    public void generarReporte() {
        System.out.println("Factura: $" + costo);
    }
}
