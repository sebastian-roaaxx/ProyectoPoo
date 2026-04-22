import Vista.VentanaPrincipal;
import modelo.*;
import ClasesAbstractas.*;
import Interfaces.*;
import TiposdeEquipo.*;

public class Main {

    public static void main(String[] args) {

        // TECNICOS (ya puedes tener algunos por defecto)
        Datos.tecnicos.add(new Tecnico("Cristiano Ronaldo", 5, 50000));
        Datos.tecnicos.add(new Tecnico("Messi", 10, 90000));
        Datos.tecnicos.add(new Tecnico("Mbappe", 3, 30000));

        // CLIENTES
        Usuario c1 = new Persona("Joan Rodriguez", "101");
        Usuario c2 = new Persona("Juan Roa", "102");
        Usuario c3 = new Empresa("Microsoft", "103", "900123");

        //  EQUIPOS
        Equipo e1 = new Computador("Laptop HP");
        Equipo e2 = new Televisor("LG 65\"");
        Equipo e3 = new Impresora("Epson L3210");

        // ÓRDENES (con diferentes presupuestos)
        OrdenMantenimiento o1 = new OrdenMantenimiento(c1, e1, 60000);
        OrdenMantenimiento o2 = new OrdenMantenimiento(c2, e2, 40000);
        OrdenMantenimiento o3 = new OrdenMantenimiento(c3, e3, 80000);

        // GUARDAR EN LISTA GLOBAL
        Datos.ordenes.add(o1);
        Datos.ordenes.add(o2);
        Datos.ordenes.add(o3);

        // ABRIR LA APP
        new VentanaPrincipal();
    }
}