package TiposdeEquipo;

import ClasesAbstractas.Equipo;

public class Computador extends Equipo {

    public Computador(String nombre) {
        super(nombre, "Computador");
    }

    public void tipo() {
        System.out.println("Computador");
    }
}