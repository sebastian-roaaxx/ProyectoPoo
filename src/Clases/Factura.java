package Clases;

import Interfaces.Calculable;
import Interfaces.Reportable;
import java.io.File;
import java.io.FileWriter;

public class Factura implements Calculable, Reportable {

    // Atributo que guarda el costo total
    private double costo;

    // Constructor principal
    public Factura(double costo) {
        this.costo = costo;
    }
// 
    // Constructor vacío
    // this(0) llama al constructor de arriba enviando 0
    public Factura() {
        this(0);
    }

    // IMPLEMENTACIÓN DE INTERFAZ
    // Devuelve el costo de la factura
    public double calcularCosto() {
        return costo;
    }

    // SOBRECARGA DE MÉTODO
    // Mismo método pero con parámetro
    public double calcularCosto(double descuento) {

        // Retorna costo con descuento
        return costo - descuento;
    }

    // IMPLEMENTACIÓN DE INTERFAZ REPORTABLE ESTO ES EN CONSOLA
    public void generarReporte() {

        // Imprime factura en consola
        System.out.println("Factura: $" + costo);
    }

    // GENERAR FACTURA EN ARCHIVO .TXT 
    public void generarFactura(String nombreArchivo, OrdenMantenimiento orden) {

        try {

            // Crear carpeta de facturas si no existe
            File carpetaFacturas = new File(System.getProperty("user.dir"), "facturas");
            if (!carpetaFacturas.exists()) {
                carpetaFacturas.mkdirs();
            }

            File archivoFactura = new File(carpetaFacturas, nombreArchivo);

            // Crear archivo
            try (FileWriter fw = new FileWriter(archivoFactura)) {
                // Escribir contenido dentro del archivo
                fw.write("==============================\n");
                fw.write("       FACTURA DE SERVICIO    \n");
                fw.write("==============================\n");

                // Datos de la orden
                fw.write("Cliente:  " + orden.getCliente().getNombre() + "\n");
                fw.write("Equipo:   " + orden.getEquipo().getNombre() + "\n");
                fw.write("Problema: " + orden.getProblema() + "\n");
                fw.write("Técnico:  " + orden.getTecnico().getNombre() + "\n");

                // Valor final de la factura
                fw.write("Costo:    $" + calcularCosto(0.1) + "\n");


                fw.write("==============================\n");
            }

            System.out.println("Factura guardada en: " + archivoFactura.getAbsolutePath());

        } catch (Exception e) {

            // Captura errores al crear/escribir archivo
            System.out.println("Error al generar factura: " + e.getMessage());
            e.printStackTrace();
        }
    }
}