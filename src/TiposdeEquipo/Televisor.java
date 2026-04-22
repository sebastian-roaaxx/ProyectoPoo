package TiposdeEquipo;

import ClasesAbstractas.Equipo;

public class Televisor extends Equipo {
    public Televisor(String nombre) {
        super(nombre);
    }

    public void tipo() {
        System.out.println("Televisor");
    }
}