package modelo;

import ClasesAbstractas.Usuario;

public class Empresa extends Usuario {

    private String nit;

    public Empresa(String nombre, String id, String nit) {
        super(nombre, id);
        this.nit = nit;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Empresa: " + nombre + " NIT: " + nit);
    }
}