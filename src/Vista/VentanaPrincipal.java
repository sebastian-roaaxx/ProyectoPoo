package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import modelo.*;

public class VentanaPrincipal {

    private DefaultTableModel modeloOrdenes;
    private ArrayList<OrdenMantenimiento> ordenes = new ArrayList<>();

    public VentanaPrincipal() {

        JFrame ventana = new JFrame("Sistema de Mantenimiento PRO");
        ventana.setSize(800, 500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        // 🔝 BARRA SUPERIOR
        JPanel top = new JPanel();
        top.setBackground(new Color(33, 37, 41));
        top.setPreferredSize(new Dimension(800, 60));

        JLabel titulo = new JLabel("Sistema de Mantenimiento");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        top.add(titulo);

        // 📊 PANEL CENTRAL
        JPanel centro = new JPanel(null);
        centro.setBackground(new Color(245, 245, 245));

        // 🔹 DATOS
        Tecnico t1 = new Tecnico("Carlos", "123", "PC");
        Tecnico t2 = new Tecnico("Tobias", "456", "Redes");
        Tecnico t3 = new Tecnico("Ana", "789", "Software");

        Equipo e1 = new Equipo("Laptop");
        Equipo e2 = new Equipo("Impresora");
        Equipo e3 = new Equipo("Servidor");

        OrdenMantenimiento o1 = new OrdenMantenimiento(e1, t1);
        OrdenMantenimiento o2 = new OrdenMantenimiento(e2, t2);
        OrdenMantenimiento o3 = new OrdenMantenimiento(e3, t3);

        ordenes.add(o1);
        ordenes.add(o2);
        ordenes.add(o3);

        // 🟦 TABLA
        modeloOrdenes = new DefaultTableModel();
        modeloOrdenes.addColumn("Nombre");
        modeloOrdenes.addColumn("ID");
        modeloOrdenes.addColumn("Equipo");
        modeloOrdenes.addColumn("Estado");

        JTable tabla = new JTable(modeloOrdenes);
        tabla.setRowHeight(25);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(50, 50, 680, 200);

        // 📌 LLENAR TABLA
        for (OrdenMantenimiento o : ordenes) {
            modeloOrdenes.addRow(new Object[]{
                    o.getTecnico().getNombre(),
                    o.getTecnico().getId(),
                    o.getEquipo().getNombre(),
                    o.getEstado()
            });
        }

        // 🔘 BOTONES
        JButton iniciar = new JButton("Iniciar");
        iniciar.setBounds(200, 300, 150, 40);
        iniciar.setBackground(new Color(0, 123, 255));
        iniciar.setForeground(Color.WHITE);

        JButton finalizar = new JButton("Finalizar");
        finalizar.setBounds(400, 300, 150, 40);
        finalizar.setBackground(new Color(40, 167, 69));
        finalizar.setForeground(Color.WHITE);

        // ⚡ EVENTOS
        iniciar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();

            if (fila != -1) {
                ordenes.get(fila).iniciar();
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            }
        });

        finalizar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();

            if (fila != -1) {
                ordenes.get(fila).finalizar();
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            }
        });

        centro.add(scroll);
        centro.add(iniciar);
        centro.add(finalizar);

        ventana.add(top, BorderLayout.NORTH);
        ventana.add(centro, BorderLayout.CENTER);

        ventana.setVisible(true);
    }

    private void actualizarTabla() {
        for (int i = 0; i < ordenes.size(); i++) {
            modeloOrdenes.setValueAt(ordenes.get(i).getEstado(), i, 3);
        }
    }
}