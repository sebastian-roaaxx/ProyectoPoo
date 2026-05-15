package Vista;

import Clases.*;
import java.awt.*; // importa AWT para colores, fuentes, cursores, etc.
import java.io.File; // importa File para manejo de archivos en disco
import javax.swing.*;

public class VistaFactura {

    public static void mostrarFactura(OrdenMantenimiento o, JFrame parent) { // método estático para mostrar la factura en un diálogo

        // Costos // sección de cálculo de costos
        double costoOriginal = o.calcularCosto();
        double costoFinal = o.calcularCostoConDescuento();

        // Nombre factura // crear nombre de archivo para la factura
        String nombreArchivo = "factura_" + o.getCliente().getNombre().replace(" ","_") + ".txt";

        // Carpeta facturas // crear o referenciar carpeta para guardar facturas
        File carpetaFacturas = new File(System.getProperty("user.dir"), "facturas"); // carpeta 'facturas' dentro del directorio de trabajo

        // Ruta factura // construir la ruta completa del archivo de factura
        File rutaFactura = new File(carpetaFacturas, nombreArchivo); // archivo dentro de la carpeta de facturas

        // Crear factura // instancia de objeto Factura con el costo final
        Factura factura = new Factura(costoFinal); // crea la factura con el monto final

        // Generar txt // generar el archivo de texto físico con la factura
        factura.generarFactura(rutaFactura.getAbsolutePath(), o); // escribe la factura en la ruta especificada

        // Notificación // preparar y enviar notificación (por ejemplo email)
        Notificacion notif = new Notificacion(); // crea instancia de notificación
        notif.enviar("email"); // envía la notificación por email (según implementación)

        // Generar reporte // generar un reporte asociado a la orden
        o.generarReporte(); // llama al método que crea un reporte de la orden

        // FACTURA VISUAL // construcción de la interfaz visual de la factura
        JDialog facturaDialog = new JDialog(parent, "Factura", true); // dialog modal titulado 'Factura'
        facturaDialog.setSize(500,550); // establece tamaño del diálogo
        facturaDialog.setLocationRelativeTo(parent); // centra el diálogo relativo a la ventana padre

        JPanel facturaPanel = new JPanel(new BorderLayout()); // panel principal con BorderLayout

        // Título factura // crear etiqueta de título para la factura
        JLabel tituloFactura = new JLabel("FACTURA DE MANTENIMIENTO", JLabel.CENTER); // etiqueta centrada con título
        tituloFactura.setFont(new Font("Segoe UI", Font.BOLD, 20)); // fuente del título
        tituloFactura.setBorder(BorderFactory.createEmptyBorder(20,10,20,10)); // margen alrededor del título

        // Área texto factura // área de texto donde se mostrará el contenido de la factura
        JTextArea facturaArea = new JTextArea(); // área de texto para la factura
        facturaArea.setEditable(false); // deshabilita edición
        facturaArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // fuente monoespaciada para alineación
        facturaArea.setBackground(new Color(245,245,245)); // color de fondo suave
        facturaArea.setMargin(new Insets(10,10,10,10)); // margen interno del área de texto

        // Contenido factura // componer el texto que se mostrará en el área
        facturaArea.setText(
            "------------------------------------\n" + // separador superior
            "        SISTEMA DE SOPORTE         \n" + // nombre del sistema en la factura
            "------------------------------------\n\n" + // separador y salto de línea
            "CLIENTE\n" + // sección cliente
            "------------------------------------\n" + // separador
            "Nombre: " + o.getCliente().getNombre() + "\n\n" + // línea con el nombre del cliente
            "EQUIPO\n" + // sección equipo
            "------------------------------------\n" + // separador
            "Equipo: " + o.getEquipo().getNombre() + "\n" + // línea con nombre del equipo
            "Tipo: " + o.getEquipo().getTipo() + "\n\n" + // línea con tipo de equipo
            "SERVICIO\n" + // sección servicio
            "------------------------------------\n" + // separador
            "Problema: " + o.getProblema() + "\n" + // línea con problema reportado
            "Tecnico: " + o.getTecnico().getNombre() + "\n" + // línea con nombre del técnico
            "Estado: " + o.getEstado() + "\n\n" + // línea con estado de la orden
            "PAGO\n" + // sección pago
            "------------------------------------\n" + // separador
            "Costo Original: $" + costoOriginal + "\n" + // línea con costo original
            "Costo Final: $" + costoFinal + "\n" + // línea con costo final
            "Descuento: $" + (costoOriginal - costoFinal) + "\n\n" + // línea con monto de descuento
            "------------------------------------\n" + // separador final
            "      Siempre a su servicio      \n" + // mensaje de despedida
            "------------------------------------" // separador final
        ); // establece el texto completo de la factura en el área

        JScrollPane facturaScroll = new JScrollPane(facturaArea); // agrega scroll al área de texto

        // Botón cerrar // botón para cerrar el diálogo de factura
        JButton cerrarFactura = new JButton("Cerrar"); // botón 'Cerrar'
        cerrarFactura.setBackground(new Color(220,53,69)); // color de fondo del botón
        cerrarFactura.setForeground(Color.WHITE); // color del texto del botón
        cerrarFactura.setFocusPainted(false); // quita el contorno de foco por defecto
        cerrarFactura.setFont(new Font("Segoe UI", Font.BOLD, 14)); // fuente del texto del botón
        cerrarFactura.setCursor(new Cursor(Cursor.HAND_CURSOR)); // cursor al pasar por encima
        cerrarFactura.setBorder(BorderFactory.createEmptyBorder(10,20,10,20)); // padding en el botón

        cerrarFactura.addActionListener(ev -> facturaDialog.dispose()); // acción para cerrar el diálogo al hacer click

        JPanel abajoFactura = new JPanel(); // panel inferior para botones
        abajoFactura.add(cerrarFactura); // añade el botón cerrar al panel inferior

        // Agregar componentes // ensamblar los componentes en el panel principal
        facturaPanel.add(tituloFactura, BorderLayout.NORTH); // añade título arriba
        facturaPanel.add(facturaScroll, BorderLayout.CENTER); // añade área de texto en el centro
        facturaPanel.add(abajoFactura, BorderLayout.SOUTH); // añade panel de botones abajo

        facturaDialog.add(facturaPanel); // añade el panel principal al diálogo

        // Mostrar factura // muestra el diálogo de factura al usuario
        facturaDialog.setVisible(true); // hace visible el diálogo (bloqueante por ser modal)
    }
}