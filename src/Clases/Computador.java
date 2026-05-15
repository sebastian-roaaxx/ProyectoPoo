package Clases;

import ClasesAbstractas.Equipo;

public class Computador extends Equipo {  // ESTA CLASE ES UNA SUBCLASE DE EQUIPO, POR ESO USA EXTENDS

    public Computador(String nombre) {      // EL CONSTRUCTOR DE COMPUTADOR LLAMA AL CONSTRUCTOR DE EQUIPO USANDO SUPER
        super(nombre, "Computador");
    }

    public void tipo() { 
        System.out.println("Computador");
    }
}