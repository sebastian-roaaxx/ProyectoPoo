import Vista.VentanaPrincipal;
import modelo.*;
import ClasesAbstractas.*;
import Interfaces.*;
import TiposdeEquipo.*;

public class Main {

    public static void main(String[] args) {

        // Limpiar datos previos en memoria antes de inicializar
        Datos.ordenes.clear();
        Datos.tecnicos.clear();

        // TECNICOS
        Datos.tecnicos.add(new Tecnico("Cristiano Ronaldo", 5, 50000, "Computador"));
        Datos.tecnicos.add(new Tecnico("Messi", 10, 90000, "Impresora"));
        Datos.tecnicos.add(new Tecnico("Tony Stark", 3, 30000, "General"));
        Datos.tecnicos.add(new Tecnico("Homelander", 15, 120000, "Televisor"));
        Datos.tecnicos.add(new Especialista("Goku", 12, 110000, "Computador"));
        Datos.tecnicos.add(new Tecnico("Neymar", 7, 70000, "General"));

        // CLIENTES
        Usuario c1 = new Persona("Joan Rodriguez", "101");
        Usuario c2 = new Persona("Juan Roa", "102");
        Usuario c3 = new Empresa("Microsoft", "103", "900123");
        Usuario c4 = new Persona("Bruce Wayne", "104");
        Usuario c5 = new Empresa("Shield", "105", "800456");

        // EQUIPOS
        Equipo e1 = new Computador("Laptop HP");
        Equipo e2 = new Televisor("LG 65\"");
        Equipo e3 = new Impresora("Epson L3210");
        Equipo e4 = new Computador("MacBook Pro");
        Equipo e5 = new Televisor("Samsung QLED");
        Equipo e6 = new Impresora("Canon TS8320");

        // Mostrar información de todos los usuarios
        c1.mostrarInfo();
        c2.mostrarInfo();
        c3.mostrarInfo();
        c4.mostrarInfo();
        c5.mostrarInfo();

        // Mostrar tipo de cada equipo
        e1.tipo();
        e2.tipo();
        e3.tipo();
        e4.tipo();
        e5.tipo();
        e6.tipo();

        // ÓRDENES (con diferentes presupuestos y problemas)
        OrdenMantenimiento o1 = new OrdenMantenimiento(c1, e1, 60000, "Pantalla no enciende");
        OrdenMantenimiento o2 = new OrdenMantenimiento(c2, e2, 40000, "No tiene sonido");
        OrdenMantenimiento o3 = new OrdenMantenimiento(c3, e3, 80000, "Atasco de papel frecuente");
        OrdenMantenimiento o4 = new OrdenMantenimiento(c4, e4, 120000, "Batería no carga");
        OrdenMantenimiento o5 = new OrdenMantenimiento(c5, e5, 95000, "Imagen con rayas");
        OrdenMantenimiento o6 = new OrdenMantenimiento(c1, e6, 45000, "Error de impresión constante");

        // Usar asignador de técnicos y métodos del proyecto
        o1.asignarTecnico(AsignadorTecnico.asignar(Datos.tecnicos, o1.calcularCosto(), o1.getEquipo().getTipo()));
        o2.asignarTecnico(AsignadorTecnico.asignar(Datos.tecnicos, o2.calcularCosto(), o2.getEquipo().getTipo()));
        o3.asignarTecnico(AsignadorTecnico.asignar(Datos.tecnicos, o3.calcularCosto(), o3.getEquipo().getTipo()));
        o4.asignarTecnico(AsignadorTecnico.asignar(Datos.tecnicos, o4.calcularCosto(), o4.getEquipo().getTipo()));
        o5.asignarTecnico(AsignadorTecnico.asignar(Datos.tecnicos, o5.calcularCosto(), o5.getEquipo().getTipo()));
        o6.asignarTecnico(AsignadorTecnico.asignar(Datos.tecnicos, o6.calcularCosto(), o6.getEquipo().getTipo()));

        o1.setPrioridad("Alta");
        o2.setPrioridad("Baja");
        o3.setPrioridad("Media");

        o1.iniciar("Urgente");
        o2.iniciar();
        o3.iniciar();

        o1.finalizar();
        o2.finalizar();

        o1.generarReporte();
        o2.generarReporte();
        o3.generarReporte();

        Factura factura1 = new Factura(o1.calcularCosto());
        Factura factura2 = new Factura(90000);
        factura1.generarReporte();
        System.out.println("Costo real o1: " + factura1.calcularCosto());
        System.out.println("Costo con descuento: " + factura2.calcularCosto(15000));

        Notificacion notificacion = new Notificacion();
        notificacion.enviar();
        notificacion.enviar("Email");
        notificacion.generarReporte();

        // GUARDAR EN LISTA GLOBAL
        Datos.ordenes.add(o1);
        Datos.ordenes.add(o2);
        Datos.ordenes.add(o3);
        Datos.ordenes.add(o4);
        Datos.ordenes.add(o5);
        Datos.ordenes.add(o6);

        // ABRIR LA APP
        new VentanaPrincipal();
    }
}