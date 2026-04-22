package modelo;

import ClasesAbstractas.Usuario;

public class Persona extends Usuario {

    public Persona(String nombre, String id) {
        super(nombre, id);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Persona: " + nombre);
    }
}