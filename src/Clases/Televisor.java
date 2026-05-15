package Clases;

import ClasesAbstractas.Equipo;

public class Televisor extends Equipo {    // TELEVISOR ES UNA SUBCLASE DE EQUIPO, POR ESO SE USA EXTENDS

    public Televisor(String nombre) {
        super(nombre, "Televisor");
    }

    public void tipo() {
        System.out.println("Televisor");
    }
}
