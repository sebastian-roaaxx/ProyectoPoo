package Vista;

import ClasesAbstractas.*;
import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.table.*;
import Clases.*;
import Interfaces.*;

public class VentanaPrincipal {

    // Modelo que controla los datos de la tabla
    private DefaultTableModel modelo;

    // Ventana principal
    private JFrame v;

    // Área donde se muestran mensajes del sistema
    private JTextArea logArea;

    public VentanaPrincipal() {

        // CREACIÓN DE LA VENTANA PRINCIPAL
        v = new JFrame("Sistema de Mantenimiento");
        v.setSize(900,600);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // BorderLayout divide la ventana en NORTH, SOUTH, CENTER...
        v.setLayout(new BorderLayout());

        // Colores personalizados para la interfaz
        Color fondo = new Color(24, 26, 27);
        Color panel = new Color(33, 37, 41);
        Color azul = new Color(0, 123, 255);
        Color verde = new Color(40, 167, 69);
        Color gris = new Color(108, 117, 125);

        // TÍTULO PRINCIPAL
        JLabel titulo = new JLabel("Panel de Administración", JLabel.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(panel);
        top.add(titulo);

        // PANEL DEL FORMULARIO
        // GridLayout organiza componentes en filas y columnas
        JPanel form = new JPanel(new GridLayout(2,4,10,10));
        form.setBackground(fondo);

        // Campos de texto
        JTextField txtCliente = crearInput("Cliente");
        JTextField txtEquipo = crearInput("Equipo");
        JTextField txtProblema = crearInput("Problema");
        JTextField txtPresupuesto = crearInput("Presupuesto");

        // Botón para agregar órdenes
        JButton btnAgregar = crearBoton("Agregar", azul);

        // Agregar componentes al formulario
        form.add(txtCliente);
        form.add(txtEquipo);
        form.add(txtProblema);
        form.add(txtPresupuesto);
        form.add(btnAgregar);

        // MODELO DE LA TABLA
        modelo = new DefaultTableModel();

        // Columnas que tendrá la tabla
        modelo.addColumn("Cliente");
        modelo.addColumn("Equipo");
        modelo.addColumn("Problema");
        modelo.addColumn("Prioridad");
        modelo.addColumn("Presupuesto");
        modelo.addColumn("Técnico");
        modelo.addColumn("Estado");

        // TABLA
        JTable tabla = new JTable(modelo);

        // PERSONALIZACIÓN DE LA TABLA
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(panel);

        JScrollPane scroll = new JScrollPane(tabla);

        // PANEL DE BOTONES
        JPanel botones = new JPanel();
        botones.setBackground(fondo);

        JButton asignar = crearBoton("Asignar", gris);
        JButton iniciar = crearBoton("Iniciar", azul);
        JButton finalizar = crearBoton("Finalizar", verde);

        botones.add(asignar);
        botones.add(iniciar);
        botones.add(finalizar);

        // ÁREA DE LOGS
        logArea = new JTextArea(5, 50);

        // Evita que el usuario escriba
        logArea.setEditable(false);

        JScrollPane logScroll = new JScrollPane(logArea);

        // ================= EVENTOS =================

        // EVENTO DEL BOTÓN AGREGAR
        btnAgregar.addActionListener(e -> {

            try {

                // Crear usuario
                Usuario u = new Persona(txtCliente.getText(), "1");

                // Crear equipo
                Equipo eq = new Computador(txtEquipo.getText());

                // Convertir texto a double
                double p = Double.parseDouble(txtPresupuesto.getText());

                String problema = txtProblema.getText();

                // Crear orden de mantenimiento
                OrdenMantenimiento o = new OrdenMantenimiento(u, eq, p, problema);

                // Guardar orden en la lista
                Datos.ordenes.add(o);

                // Refrescar tabla
                actualizarTabla();

            } catch (Exception ex) {

                // Captura errores si el usuario escribe mal datos
                JOptionPane.showMessageDialog(v, "Datos inválidos");
            }
        });

        // EVENTO ASIGNAR TÉCNICO
        asignar.addActionListener(e -> {

            // Obtener fila seleccionada
            int fila = tabla.getSelectedRow();

            if (fila != -1) {

                // Obtener orden seleccionada
                OrdenMantenimiento o = Datos.ordenes.get(fila);

                // Buscar mejor técnico según costo y tipo de equipo
                Tecnico mejor = AsignadorTecnico.asignar(
                        Datos.tecnicos,
                        o.calcularCosto(),
                        o.getEquipo().getTipo()
                );

                if (mejor != null) {

                    // Asignar técnico a la orden
                    o.asignarTecnico(mejor);

                    actualizarTabla();

                } else {

                    JOptionPane.showMessageDialog(v,
                            "No hay técnico disponible para este presupuesto");
                }

            } else {

                JOptionPane.showMessageDialog(v, "Selecciona una fila");
            }
        });

        // EVENTO INICIAR ORDEN
        iniciar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila != -1) {

                // Cambia estado de la orden
                Datos.ordenes.get(fila).iniciar();

                actualizarTabla();
            }
        });

        // EVENTO FINALIZAR ORDEN
        finalizar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            // Validaciones
            if (fila == -1) {
                JOptionPane.showMessageDialog(v, "Selecciona una fila");
                return;
            }

            if (fila >= Datos.ordenes.size()) {
                JOptionPane.showMessageDialog(v, "Error: fila inválida");
                return;
            }

            OrdenMantenimiento o = Datos.ordenes.get(fila);

            if (o == null) {
                JOptionPane.showMessageDialog(v, "Error: orden no encontrada");
                return;
            }

            // Verifica si la orden tiene técnico
            if (o.getTecnico() == null) {
                logArea.append("Error: asigna un técnico antes de finalizar.\n");
                return;
            }

            // Finalizar orden
            o.finalizar();

            // POLIMORFISMO:
            // calcularCostoConDescuento probablemente cambia según la clase
            double costoOriginal = o.calcularCosto();
            double costoFinal = o.calcularCostoConDescuento();

            logArea.append("Orden finalizada.\n");

            // GENERAR FACTURA
            String nombreArchivo =
                    "factura_" +
                    o.getCliente().getNombre().replace(" ", "_")
                    + ".txt";

            File carpetaFacturas = new File(System.getProperty("user.dir"), "facturas");
            File rutaFactura = new File(carpetaFacturas, nombreArchivo);

            Factura factura = new Factura(costoFinal);

            // Crear archivo txt
            factura.generarFactura(rutaFactura.getAbsolutePath(), o);

            // NOTIFICACIONES
            Notificacion notif = new Notificacion();
            notif.enviar("email");

            // Generar reporte
            o.generarReporte();

            actualizarTabla();
        });

        // PANEL CENTRAL
        JPanel centro = new JPanel(new BorderLayout());

        centro.add(form, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        // PANEL INFERIOR
        JPanel inferior = new JPanel(new BorderLayout());

        inferior.add(botones, BorderLayout.NORTH);
        inferior.add(logScroll, BorderLayout.CENTER);

        centro.add(inferior, BorderLayout.SOUTH);

        // AGREGAR TODO A LA VENTANA
        v.add(top, BorderLayout.NORTH);
        v.add(centro, BorderLayout.CENTER);

        actualizarTabla();

        // Hace visible la ventana
        v.setVisible(true);
    }

    // MÉTODO PARA CREAR INPUTS
    private JTextField crearInput(String placeholder) {

        JTextField txt = new JTextField();

        txt.setBorder(BorderFactory.createTitledBorder(placeholder));

        return txt;
    }

    // MÉTODO PARA CREAR BOTONES
    private JButton crearBoton(String texto, Color color) {

        JButton b = new JButton(texto);

        b.setBackground(color);

        return b;
    }

    // ACTUALIZA LOS DATOS DE LA TABLA
    private void actualizarTabla() {

        // Limpia filas
        modelo.setRowCount(0);

        // Recorre todas las órdenes
        for (OrdenMantenimiento o : Datos.ordenes) {

            // Operador ternario
            String tecnico = (o.getTecnico() != null)
                    ? o.getTecnico().getNombre()
                    : "Sin asignar";

            // Agrega fila a la tabla
            modelo.addRow(new Object[]{

                    o.getCliente().getNombre(),
                    o.getEquipo().getNombre(),
                    o.getProblema(),
                    o.getPrioridad(),
                    o.calcularCosto(),
                    tecnico,
                    o.getEstado()
            });
        }
    }
}