import Clases.*;
import ClasesAbstractas.*;
import Vista.VentanaPrincipal;


public class Main {

    public static void main(String[] args) {

        // Limpiar datos previos en memoria antes de inicializar 
        DatosArray.ordenes.clear();
        DatosArray.tecnicos.clear();

        // TECNICOS CREADOS EN LA INTERFAZ GRAFICA, CON DIFERENTES ESPECIALIDADES Y TARIFAS PARA PROBAR EL ASIGNADOR DE TECNICOS
        DatosArray.tecnicos.add(new Tecnico("Cristiano Ronaldo", 5, 50000, "Computador"));
        DatosArray.tecnicos.add(new Tecnico("Leonel Messi", 10, 90000, "Impresora"));
        DatosArray.tecnicos.add(new Tecnico("Tony Stark", 3, 30000, "General"));
        DatosArray.tecnicos.add(new Tecnico("Reed Richards", 15, 120000, "Televisor"));
        DatosArray.tecnicos.add(new Tecnico("Bruce Wayne", 7, 70000, "General"));

        // CLIENTES PUESTOS EN LA UI PARA PROBAR EL POLIMORFISMO DE USUARIO EN ORDENMANTENIMIENTO
        Usuario c1 = new Persona("Joan Rodriguez", "101");
        Usuario c2 = new Persona("Juan Roa", "102");
        Usuario c4 = new Persona("Bruce Wayne", "104");
  

        // EQUIPOS PUESTOS EN LA UI PARA PROBAR EL POLIMORFISMO DE EQUIPO EN ORDENMANTENIMIENTO
        Equipo e1 = new Computador("Laptop HP");
        Equipo e2 = new Televisor("LG 65\"");
        Equipo e3 = new Impresora("Epson L3210");
        Equipo e4 = new Computador("MacBook Pro");
        Equipo e5 = new Televisor("Samsung QLED");
        Equipo e6 = new Impresora("Canon TS8320");

        // Mostrar información de todos los usuarios
        c1.mostrarInfo();
        c2.mostrarInfo();
        c4.mostrarInfo();

        // ÓRDENES (con diferentes presupuestos y problemas)
        OrdenMantenimiento o1 = new OrdenMantenimiento(c1, e1, 60000, "Pantalla no enciende");
        OrdenMantenimiento o2 = new OrdenMantenimiento(c2, e2, 40000, "No tiene sonido");
        OrdenMantenimiento o4 = new OrdenMantenimiento(c4, e4, 120000, "Batería no carga");
        OrdenMantenimiento o6 = new OrdenMantenimiento(c1, e6, 45000, "Error de impresión constante");

        // Usar asignador de técnicos y métodos del proyecto
        o1.asignarTecnico(AsignadorTecnico.asignar(DatosArray.tecnicos, o1.calcularCosto(), o1.getEquipo().getTipo()));
        o2.asignarTecnico(AsignadorTecnico.asignar(DatosArray.tecnicos, o2.calcularCosto(), o2.getEquipo().getTipo()));
        o4.asignarTecnico(AsignadorTecnico.asignar(DatosArray.tecnicos, o4.calcularCosto(), o4.getEquipo().getTipo()));
        o6.asignarTecnico(AsignadorTecnico.asignar(DatosArray.tecnicos, o6.calcularCosto(), o6.getEquipo().getTipo()));

   // o1.asignarTecnico(null); // PRUEBA DE EXCEPCIÓN DE EL THROW EN ORDENMANTENIMIENTO CUANDO
   // SE ASIGNA UN TECNICO NULO PARA VER EL MENSAJE EN CONSOLA


       o1.setPrioridad("Alta");  
        o2.setPrioridad("Baja");
        o1.iniciar("Urgente"); 
        o2.iniciar();

// FINALIZAR Y GENERAR REPORTES DE LAS ÓRDENES
        o1.finalizar();
        o2.finalizar();
        o1.generarReporte();
        o2.generarReporte();
       
// FACTURAS 
        Factura factura1 = new Factura(o1.calcularCosto());
        Factura factura2 = new Factura(90000);
        factura1.generarReporte();
        System.out.println("Costo real o1: " + factura1.calcularCosto());
        System.out.println("Costo con descuento: " + factura2.calcularCosto(15000));
  // MENSAJES DE NOTIFICACIÓN
       Notificacion notificacion = new Notificacion();
        notificacion.enviar();
        notificacion.enviar("Email");
        notificacion.generarReporte();

        // GUARDAR EN LISTA GLOBAL
        DatosArray.ordenes.add(o1);
        DatosArray.ordenes.add(o2);

        DatosArray.ordenes.add(o4);
        DatosArray.ordenes.add(o6);

        // ABRIR LA APP
        new VentanaPrincipal();
    }
}