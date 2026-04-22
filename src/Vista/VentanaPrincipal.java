package Vista;

import modelo.*;
import ClasesAbstractas.*;
import TiposdeEquipo.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class VentanaPrincipal {

    private DefaultTableModel modelo;

    public VentanaPrincipal() {

        // 🎯 FRAME
        JFrame v = new JFrame("Sistema de Mantenimiento");
        v.setSize(900,550);
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
        JTextField txtPresupuesto = crearInput("Presupuesto");

        JButton btnAgregar = crearBoton("Agregar", azul);

        form.add(txtCliente);
        form.add(txtEquipo);
        form.add(txtPresupuesto);
        form.add(btnAgregar);

        // 📊 TABLA
        modelo = new DefaultTableModel();
        modelo.addColumn("Cliente");
        modelo.addColumn("Equipo");
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

        // ⚡ EVENTOS

        btnAgregar.addActionListener(e -> {
            try {
                Usuario u = new Persona(txtCliente.getText(), "1");
                Equipo eq = new Computador(txtEquipo.getText());
                double p = Double.parseDouble(txtPresupuesto.getText());

                OrdenMantenimiento o = new OrdenMantenimiento(u, eq, p);
                Datos.ordenes.add(o);

                actualizarTabla();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Datos inválidos");
            }
        });

        asignar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();

            if (fila != -1) {

                OrdenMantenimiento o = Datos.ordenes.get(fila);
                Tecnico mejor = null;

                for (Tecnico t : Datos.tecnicos) {
                    if (t.getTarifa() <= o.calcularCosto()) {
                        if (mejor == null || t.getExperiencia() > mejor.getExperiencia()) {
                            mejor = t;
                        }
                    }
                }

                if (mejor != null) {
                    o.asignarTecnico(mejor);
                    actualizarTabla();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
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
                Datos.ordenes.get(fila).finalizar();
                actualizarTabla();
            }
        });

        // 📦 CONTENEDOR CENTRAL
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(fondo);
        centro.add(form, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);
        centro.add(botones, BorderLayout.SOUTH);

        // 🔗 AGREGAR
        v.add(top, BorderLayout.NORTH);
        v.add(centro, BorderLayout.CENTER);

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
                    o.calcularCosto(),
                    tecnico,
                    o.getEstado()
            });
        }
    }
}