package modelo;

import java.util.ArrayList;

public class Datos {

    public static ArrayList<OrdenMantenimiento> ordenes = new ArrayList<>();
    public static ArrayList<Tecnico> tecnicos = new ArrayList<>();

    // Técnicos precargados
    static {
        tecnicos.add(new Tecnico("Carlos", 5, 50000, "Computador"));
        tecnicos.add(new Tecnico("Ana", 10, 90000, "Impresora"));
        tecnicos.add(new Tecnico("Luis", 3, 30000, "General"));
    }
}