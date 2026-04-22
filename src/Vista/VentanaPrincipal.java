package Vista;

import modelo.*;
import ClasesAbstractas.*;
import TiposdeEquipo.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class VentanaPrincipal {

    private DefaultTableModel modelo;
    private JFrame v; // Hacer v un campo para usar en dialogs
    private JTextArea logArea; // Área para mostrar mensajes

    public VentanaPrincipal() {

        // 🎯 FRAME
        v = new JFrame("Sistema de Mantenimiento");
        v.setSize(900,600); // Aumentar altura para logs
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setLayout(new BorderLayout());

        // 🎨 COLORES
        Color fondo = new Color(24, 26, 27);
        Color panel = new Color(33, 37, 41);
        Color azul = new Color(0, 123, 255);
        Color verde = new Color(40, 167, 69);
        Color gris = new Color(108, 117, 125);

        // 🔝 HEADER
        JLabel titulo = new JLabel("Panel de Administración", JLabel.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(panel);
        top.add(titulo);

        // 📥 FORMULARIO
        JPanel form = new JPanel(new GridLayout(2,4,10,10));
        form.setBackground(fondo);
        form.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JTextField txtCliente = crearInput("Cliente");
        JTextField txtEquipo = crearInput("Equipo");
        JTextField txtProblema = crearInput("Problema");
        JTextField txtPresupuesto = crearInput("Presupuesto");

        JButton btnAgregar = crearBoton("Agregar", azul);

        form.add(txtCliente);
        form.add(txtEquipo);
        form.add(txtProblema);
        form.add(txtPresupuesto);
        form.add(btnAgregar);

        // 📊 TABLA
        modelo = new DefaultTableModel();
        modelo.addColumn("Cliente");
        modelo.addColumn("Equipo");
        modelo.addColumn("Problema");
        modelo.addColumn("Prioridad");
        modelo.addColumn("Presupuesto");
        modelo.addColumn("Técnico");
        modelo.addColumn("Estado");

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setBackground(new Color(40, 44, 52));
        tabla.setForeground(Color.WHITE);
        tabla.setSelectionBackground(azul);

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(panel);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        // 🔘 BOTONES
        JPanel botones = new JPanel();
        botones.setBackground(fondo);

        JButton asignar = crearBoton("Asignar", gris);
        JButton iniciar = crearBoton("Iniciar", azul);
        JButton finalizar = crearBoton("Finalizar", verde);

        botones.add(asignar);
        botones.add(iniciar);
        botones.add(finalizar);

        // 📝 LOGS
        logArea = new JTextArea(5, 50);
        logArea.setEditable(false);
        logArea.setBackground(new Color(40, 44, 52));
        logArea.setForeground(Color.WHITE);
        logArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createTitledBorder("Mensajes del Sistema"));

        // ⚡ EVENTOS

        btnAgregar.addActionListener(e -> {
            try {
                Usuario u = new Persona(txtCliente.getText(), "1");
                Equipo eq = new Computador(txtEquipo.getText());
                double p = Double.parseDouble(txtPresupuesto.getText());
                String problema = txtProblema.getText();

                OrdenMantenimiento o = new OrdenMantenimiento(u, eq, p, problema);
                Datos.ordenes.add(o);

                actualizarTabla();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(v, "Datos inválidos");
            }
        });

        asignar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();

            if (fila != -1) {

                OrdenMantenimiento o = Datos.ordenes.get(fila);
                Tecnico mejor = AsignadorTecnico.asignar(Datos.tecnicos, o.calcularCosto(), o.getEquipo().getTipo());

                if (mejor != null) {
                    o.asignarTecnico(mejor);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(v, "No hay técnico disponible para este presupuesto");
                }

            } else {
                JOptionPane.showMessageDialog(v, "Selecciona una fila");
            }
        });

        iniciar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                Datos.ordenes.get(fila).iniciar();
                actualizarTabla();
            }
        });

        finalizar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                OrdenMantenimiento o = Datos.ordenes.get(fila);
                o.finalizar();
                
                // Calcular costo con descuento
                double costoOriginal = o.calcularCosto();
                double costoFinal = o.calcularCostoConDescuento();
                logArea.append("Orden finalizada.\n");
                logArea.append("Costo original: $" + costoOriginal + "\n");
                logArea.append("Costo final (con descuento): $" + costoFinal + "\n");
                
                // Enviar reporte al cliente
                Notificacion notif = new Notificacion();
                notif.enviar("email"); // Asumiendo envío por email
                o.generarReporte(); // Generar reporte
                
                logArea.append("Notificación enviada al cliente y reporte generado.\n");
                
                actualizarTabla();
            }
        });

        // 📦 CONTENEDOR CENTRAL
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(fondo);
        centro.add(form, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        // Panel inferior para botones y logs
        JPanel inferior = new JPanel(new BorderLayout());
        inferior.setBackground(fondo);
        inferior.add(botones, BorderLayout.NORTH);
        inferior.add(logScroll, BorderLayout.CENTER);

        centro.add(inferior, BorderLayout.SOUTH);

        // 🔗 AGREGAR
        v.add(top, BorderLayout.NORTH);
        v.add(centro, BorderLayout.CENTER);

        actualizarTabla();
        v.setVisible(true);
    }

    // 🔧 INPUT MODERNO
    private JTextField crearInput(String placeholder) {
        JTextField txt = new JTextField();
        txt.setBorder(BorderFactory.createTitledBorder(placeholder));
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return txt;
    }

    // 🔘 BOTÓN MODERNO
    private JButton crearBoton(String texto, Color color) {
        JButton b = new JButton(texto);
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return b;
    }

    private void actualizarTabla() {

        modelo.setRowCount(0);

        for (OrdenMantenimiento o : Datos.ordenes) {

            String tecnico = (o.getTecnico() != null)
                    ? o.getTecnico().getNombre()
                    : "Sin asignar";

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