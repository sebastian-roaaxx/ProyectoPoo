package Vista;

import Clases.*;
import java.awt.*;
import java.io.File;
import javax.swing.*;

public class VistaFactura {

    public static void mostrarFactura(OrdenMantenimiento o, JFrame parent) {

        // Costos
        double costoOriginal = o.calcularCosto();
        double costoFinal = o.calcularCostoConDescuento();

        // Nombre factura
        String nombreArchivo = "factura_" + o.getCliente().getNombre().replace(" ","_") + ".txt";

        // Carpeta facturas
        File carpetaFacturas = new File(System.getProperty("user.dir"), "facturas");

        // Ruta factura
        File rutaFactura = new File(carpetaFacturas, nombreArchivo);

        // Crear factura
        Factura factura = new Factura(costoFinal);

        // Generar txt
        factura.generarFactura(rutaFactura.getAbsolutePath(), o);

        // Notificación
        Notificacion notif = new Notificacion();
        notif.enviar("email");

        // Generar reporte
        o.generarReporte();

        // FACTURA VISUAL
        JDialog facturaDialog = new JDialog(parent, "Factura", true);
        facturaDialog.setSize(500,550);
        facturaDialog.setLocationRelativeTo(parent);

        JPanel facturaPanel = new JPanel(new BorderLayout());

        // Título factura
        JLabel tituloFactura = new JLabel("FACTURA DE MANTENIMIENTO", JLabel.CENTER);
        tituloFactura.setFont(new Font("Segoe UI", Font.BOLD, 20));
        tituloFactura.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        // Área texto factura
        JTextArea facturaArea = new JTextArea();
        facturaArea.setEditable(false);
        facturaArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        facturaArea.setBackground(new Color(245,245,245));
        facturaArea.setMargin(new Insets(10,10,10,10));

        // Contenido factura
        facturaArea.setText(
            "------------------------------------\n" +
            "        SISTEMA DE SOPORTE         \n" +
            "------------------------------------\n\n" +
            "CLIENTE\n" +
            "------------------------------------\n" +
            "Nombre: " + o.getCliente().getNombre() + "\n\n" +
            "EQUIPO\n" +
            "------------------------------------\n" +
            "Equipo: " + o.getEquipo().getNombre() + "\n" +
            "Tipo: " + o.getEquipo().getTipo() + "\n\n" +
            "SERVICIO\n" +
            "------------------------------------\n" +
            "Problema: " + o.getProblema() + "\n" +
            "Tecnico: " + o.getTecnico().getNombre() + "\n" +
            "Estado: " + o.getEstado() + "\n\n" +
            "PAGO\n" +
            "------------------------------------\n" +
            "Costo Original: $" + costoOriginal + "\n" +
            "Costo Final: $" + costoFinal + "\n" +
            "Descuento: $" + (costoOriginal - costoFinal) + "\n\n" +
            "------------------------------------\n" +
            "      Siempre a su servicio      \n" +
            "------------------------------------"
        );

        JScrollPane facturaScroll = new JScrollPane(facturaArea);

        // Botón cerrar
        JButton cerrarFactura = new JButton("Cerrar");
        cerrarFactura.setBackground(new Color(220,53,69));
        cerrarFactura.setForeground(Color.WHITE);
        cerrarFactura.setFocusPainted(false);
        cerrarFactura.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cerrarFactura.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cerrarFactura.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        cerrarFactura.addActionListener(ev -> facturaDialog.dispose());

        JPanel abajoFactura = new JPanel();
        abajoFactura.add(cerrarFactura);

        // Agregar componentes
        facturaPanel.add(tituloFactura, BorderLayout.NORTH);
        facturaPanel.add(facturaScroll, BorderLayout.CENTER);
        facturaPanel.add(abajoFactura, BorderLayout.SOUTH);

        facturaDialog.add(facturaPanel);

        // Mostrar factura
        facturaDialog.setVisible(true);
    }
}