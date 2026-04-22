package TiposdeEquipo;

import ClasesAbstractas.Equipo;

public class Impresora extends Equipo {
    public Impresora(String nombre) {
        super(nombre);
    }

    public void tipo() {
        System.out.println("Impresora");
    }
}