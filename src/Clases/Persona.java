package Clases;

import ClasesAbstractas.Usuario;  // LOCALIZA LA CLASE USUARIO

public class Persona extends Usuario {                  // La clase Persona HEREDA de Usuario, lo que significa que tiene
                                                                // todos los atributos y métodos de Usuario.
    public Persona(String nombre, String id) {
        super(nombre, id);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Persona: " + nombre);
    }
}