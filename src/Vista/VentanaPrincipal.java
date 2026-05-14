package Vista;

import Clases.*;
import ClasesAbstractas.*;
import Interfaces.*;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.table.*;

public class VentanaPrincipal {

    // Modelo de la tabla
    private DefaultTableModel modelo;

    // Ventana principal
    private JFrame v;

    // Área de logs
    private JTextArea logArea;

    public VentanaPrincipal() {

        // ================= VENTANA =================

        v = new JFrame("Sistema de Mantenimiento");

        v.setSize(1000,650);

        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        v.setLayout(new BorderLayout());

        // ================= COLORES =================

        Color fondo = new Color(18,18,18);

        Color panel = new Color(30,30,30);

        Color azul = new Color(88,101,242);

        Color verde = new Color(46,204,113);

        Color gris = new Color(64,68,75);

        // ================= TITULO =================

        JLabel titulo = new JLabel(
                "Panel de Administración",
                JLabel.CENTER
        );

        titulo.setForeground(Color.WHITE);

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        JPanel top = new JPanel(
                new BorderLayout()
        );

        top.setBackground(panel);

        top.add(titulo);

        // ================= FORMULARIO =================

        JPanel form = new JPanel(
                new GridLayout(2,5,10,10)
        );

        form.setBackground(fondo);

        form.setBorder(
                BorderFactory.createEmptyBorder(
                        15,15,15,15
                )
        );

        // Campo cliente
        JTextField txtCliente =
                crearInput("Cliente");

        // Campo nombre equipo
        JTextField txtEquipo =
                crearInput("Nombre Equipo");

        // Campo problema
        JTextField txtProblema =
                crearInput("Problema");

        // Campo presupuesto
        JTextField txtPresupuesto =
                crearInput("Presupuesto");

        // ComboBox tipo equipo
        String[] tipos = {
                "Computador",
                "Impresora",
                "Televisor"
        };

        JComboBox<String> cbTipo =
                new JComboBox<>(tipos);

        cbTipo.setBorder(
                BorderFactory.createTitledBorder(
                        "Tipo de Equipo"
                )
        );

        cbTipo.setBackground(Color.WHITE);

        // Botón agregar
        JButton btnAgregar =
                crearBoton("Agregar", azul);

        // Agregar componentes
        form.add(txtCliente);

        form.add(txtEquipo);

        form.add(cbTipo);

        form.add(txtProblema);

        form.add(txtPresupuesto);

        form.add(btnAgregar);

        // ================= TABLA =================

        modelo = new DefaultTableModel();

        modelo.addColumn("Cliente");

        modelo.addColumn("Equipo");

        modelo.addColumn("Problema");

        modelo.addColumn("Prioridad");

        modelo.addColumn("Costo");

        modelo.addColumn("Técnico");

        modelo.addColumn("Estado");

        JTable tabla = new JTable(modelo);

        // Diseño tabla
        tabla.setRowHeight(30);

        tabla.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tabla.setBackground(
                new Color(45,45,45)
        );

        tabla.setForeground(Color.WHITE);

        tabla.setGridColor(
                new Color(70,70,70)
        );

        tabla.setSelectionBackground(azul);

        tabla.setSelectionForeground(Color.WHITE);

        JTableHeader header =
                tabla.getTableHeader();

        header.setBackground(panel);

        header.setForeground(Color.WHITE);

        header.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JScrollPane scroll =
                new JScrollPane(tabla);

        // ================= BOTONES =================

        JPanel botones = new JPanel();

        botones.setBackground(fondo);

        JButton asignar =
                crearBoton("Asignar", gris);

        JButton iniciar =
                crearBoton("Iniciar", azul);

        JButton finalizar =
                crearBoton("Finalizar", verde);

        botones.add(asignar);

        botones.add(iniciar);

        botones.add(finalizar);

        // ================= LOGS =================

        logArea = new JTextArea(5,50);

        // Evitar escritura manual
        logArea.setEditable(false);

        // Diseño logs
        logArea.setBackground(
                new Color(20,20,20)
        );

        logArea.setForeground(
                new Color(0,255,140)
        );

        logArea.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        13
                )
        );

        JScrollPane logScroll =
                new JScrollPane(logArea);

        // ================= EVENTO AGREGAR =================

        btnAgregar.addActionListener(e -> {

            try {

                // Crear cliente
                Usuario u = new Persona(
                        txtCliente.getText(),
                        "1"
                );

                // Obtener tipo equipo
                String tipo =
                        cbTipo.getSelectedItem()
                                .toString();

                Equipo eq;

                // Crear objeto según selección
                switch (tipo) {

                    case "Impresora":

                        eq = new Impresora(
                                txtEquipo.getText()
                        );

                        break;

                    case "Televisor":

                        eq = new Televisor(
                                txtEquipo.getText()
                        );

                        break;

                    default:

                        eq = new Computador(
                                txtEquipo.getText()
                        );

                        break;
                }

                // Obtener presupuesto
                double p =
                        Double.parseDouble(
                                txtPresupuesto.getText()
                        );

                // Obtener problema
                String problema =
                        txtProblema.getText();

                // Crear orden
                OrdenMantenimiento o =
                        new OrdenMantenimiento(
                                u,
                                eq,
                                p,
                                problema
                        );

                // Guardar orden
                Datos.ordenes.add(o);

                // Actualizar tabla
                actualizarTabla();

                // Mostrar log
                logArea.append(
                        "Orden agregada.\n"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        v,
                        "Datos inválidos"
                );
            }
        });

        // ================= EVENTO ASIGNAR =================

        asignar.addActionListener(e -> {

            // Obtener fila seleccionada
            int fila =
                    tabla.getSelectedRow();

            // Validar fila
            if (fila != -1) {

                // Obtener orden
                OrdenMantenimiento o =
                        Datos.ordenes.get(fila);

                // Buscar técnico compatible
                Tecnico mejor =
                        AsignadorTecnico.asignar(
                                Datos.tecnicos,
                                o.calcularCosto(),
                                o.getEquipo().getTipo()
                        );

                // Validar técnico
                if (mejor != null) {

                    // Asignar técnico
                    o.asignarTecnico(mejor);

                    actualizarTabla();

                    logArea.append(
                            "Técnico asignado.\n"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            v,
                            "No hay técnico disponible"
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                        v,
                        "Selecciona una fila"
                );
            }
        });

        // ================= EVENTO INICIAR =================

        iniciar.addActionListener(e -> {

            int fila =
                    tabla.getSelectedRow();

            // Validar fila
            if (fila != -1) {

                // Cambiar estado
                Datos.ordenes.get(fila)
                        .iniciar();

                actualizarTabla();

                logArea.append(
                        "Mantenimiento iniciado.\n"
                );
            }
        });

        // ================= EVENTO FINALIZAR =================

        finalizar.addActionListener(e -> {

            int fila =
                    tabla.getSelectedRow();

            // Validar selección
            if (fila < 0) {

                JOptionPane.showMessageDialog(
                        v,
                        "Selecciona una fila"
                );

                return;
            }

            // Obtener orden
            OrdenMantenimiento o =
                    Datos.ordenes.get(fila);

            // Verificar técnico
            if (o.getTecnico() == null) {

                logArea.append(
                        "Asigna un técnico primero.\n"
                );

                return;
            }

            // Finalizar orden
            o.finalizar();

            // Costos
            double costoOriginal =
                    o.calcularCosto();

            double costoFinal =
                    o.calcularCostoConDescuento();

            // Nombre factura
            String nombreArchivo =
                    "factura_" +
                    o.getCliente()
                            .getNombre()
                            .replace(" ","_")
                    + ".txt";

            // Carpeta facturas
            File carpetaFacturas =
                    new File(
                            System.getProperty(
                                    "user.dir"
                            ),
                            "facturas"
                    );

            // Ruta factura
            File rutaFactura =
                    new File(
                            carpetaFacturas,
                            nombreArchivo
                    );

            // Crear factura
            Factura factura =
                    new Factura(costoFinal);

            // Generar txt
            factura.generarFactura(
                    rutaFactura.getAbsolutePath(),
                    o
            );

            // Notificación
            Notificacion notif =
                    new Notificacion();

            notif.enviar("email");

            // Generar reporte
            o.generarReporte();

            // ================= FACTURA VISUAL =================

            JDialog facturaDialog =
                    new JDialog(
                            v,
                            "Factura",
                            true
                    );

            facturaDialog.setSize(500,550);

            facturaDialog.setLocationRelativeTo(v);

            JPanel facturaPanel =
                    new JPanel(
                            new BorderLayout()
                    );

            // Título factura
            JLabel tituloFactura =
                    new JLabel(
                            "FACTURA DE MANTENIMIENTO",
                            JLabel.CENTER
                    );

            tituloFactura.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            20
                    )
            );

            tituloFactura.setBorder(
                    BorderFactory.createEmptyBorder(
                            20,10,20,10
                    )
            );

            // Área texto factura
            JTextArea facturaArea =
                    new JTextArea();

            facturaArea.setEditable(false);

            facturaArea.setFont(
                    new Font(
                            "Monospaced",
                            Font.PLAIN,
                            14
                    )
            );

            facturaArea.setBackground(
                    new Color(245,245,245)
            );

            facturaArea.setMargin(
                    new Insets(10,10,10,10)
            );

            // Contenido factura
            facturaArea.setText(

                    "====================================\n" +
                    "        SISTEMA DE SOPORTE         \n" +
                    "====================================\n\n" +

                    "CLIENTE\n" +
                    "------------------------------------\n" +

                    "Nombre: " +
                    o.getCliente().getNombre()
                    + "\n\n" +

                    "EQUIPO\n" +
                    "------------------------------------\n" +

                    "Equipo: " +
                    o.getEquipo().getNombre()
                    + "\n" +

                    "Tipo: " +
                    o.getEquipo().getTipo()
                    + "\n\n" +

                    "SERVICIO\n" +
                    "------------------------------------\n" +

                    "Problema: " +
                    o.getProblema()
                    + "\n" +

                    "Tecnico: " +
                    o.getTecnico().getNombre()
                    + "\n" +

                    "Estado: " +
                    o.getEstado()
                    + "\n\n" +

                    "PAGO\n" +
                    "------------------------------------\n" +

                    "Costo Original: $" +
                    costoOriginal + "\n" +

                    "Costo Final: $" +
                    costoFinal + "\n" +

                    "Descuento: $" +
                    (costoOriginal - costoFinal)
                    + "\n\n" +

                    "====================================\n" +
                    "      Gracias por confiar ❤️      \n" +
                    "===================================="
            );

            JScrollPane facturaScroll =
                    new JScrollPane(facturaArea);

            // Botón cerrar
            JButton cerrarFactura =
                    crearBoton(
                            "Cerrar",
                            new Color(220,53,69)
                    );

            cerrarFactura.addActionListener(
                    ev -> facturaDialog.dispose()
            );

            JPanel abajoFactura =
                    new JPanel();

            abajoFactura.add(cerrarFactura);

            // Agregar componentes
            facturaPanel.add(
                    tituloFactura,
                    BorderLayout.NORTH
            );

            facturaPanel.add(
                    facturaScroll,
                    BorderLayout.CENTER
            );

            facturaPanel.add(
                    abajoFactura,
                    BorderLayout.SOUTH
            );

            facturaDialog.add(facturaPanel);

            // Mostrar factura
            facturaDialog.setVisible(true);

            actualizarTabla();

            logArea.append(
                    "Orden finalizada.\n"
            );
        });

        // ================= PANEL CENTRAL =================

        JPanel centro =
                new JPanel(
                        new BorderLayout()
                );

        centro.setBackground(fondo);

        centro.add(form, BorderLayout.NORTH);

        centro.add(scroll, BorderLayout.CENTER);

        // ================= PANEL INFERIOR =================

        JPanel inferior =
                new JPanel(
                        new BorderLayout()
                );

        inferior.setBackground(fondo);

        inferior.add(
                botones,
                BorderLayout.NORTH
        );

        inferior.add(
                logScroll,
                BorderLayout.CENTER
        );

        centro.add(
                inferior,
                BorderLayout.SOUTH
        );

        // ================= AGREGAR TODO =================

        v.add(top, BorderLayout.NORTH);

        v.add(centro, BorderLayout.CENTER);

        actualizarTabla();

        v.setVisible(true);
    }

    // ================= INPUT =================

    private JTextField crearInput(
            String placeholder
    ) {

        JTextField txt =
                new JTextField();

        txt.setBorder(
                BorderFactory.createTitledBorder(
                        placeholder
                )
        );

        txt.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        return txt;
    }

    // ================= BOTONES =================

    private JButton crearBoton(
            String texto,
            Color color
    ) {

        JButton b =
                new JButton(texto);

        // Color botón
        b.setBackground(color);

        // Color texto
        b.setForeground(Color.WHITE);

        // Quitar borde foco
        b.setFocusPainted(false);

        // Fuente
        b.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        // Cursor mano
        b.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        // Espaciado interno
        b.setBorder(
                BorderFactory.createEmptyBorder(
                        10,20,10,20
                )
        );

        return b;
    }

    // ================= ACTUALIZAR TABLA =================

    private void actualizarTabla() {

        // Limpiar tabla
        modelo.setRowCount(0);

        // Recorrer órdenes
        for (OrdenMantenimiento o :
                Datos.ordenes) {

            String tecnico;

            // Verificar técnico
            if (o.getTecnico() != null) {

                tecnico =
                        o.getTecnico()
                                .getNombre();

            } else {

                tecnico =
                        "Sin asignar";
            }

            // Agregar fila
            modelo.addRow(new Object[] {

                    o.getCliente()
                            .getNombre(),

                    o.getEquipo()
                            .getNombre(),

                    o.getProblema(),

                    o.getPrioridad(),

                    o.calcularCosto(),

                    tecnico,

                    o.getEstado()
            });
        }
    }
}