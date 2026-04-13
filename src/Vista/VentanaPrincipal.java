package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import modelo.*;

public class VentanaPrincipal {

    private DefaultTableModel modeloOrdenes;
    private OrdenMantenimiento orden;

    public VentanaPrincipal() {

        //  VENTANA
        JFrame ventana = new JFrame("Sistema de Mantenimiento");
        ventana.setSize(800, 500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        // COLORES
        Color fondo = new Color(245, 245, 245);
        Color barra = new Color(33, 37, 41);
        Color botonColor = new Color(0, 123, 255);

        //  BARRA SUPERIOR
        JPanel top = new JPanel();
        top.setBackground(barra);
        top.setPreferredSize(new Dimension(800, 60));

        JLabel titulo = new JLabel("Sistema de Mantenimiento");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        top.add(titulo);

        //  PANEL CENTRAL
        JPanel centro = new JPanel();
        centro.setBackground(fondo);
        centro.setLayout(null);

        //  DATOS
        Tecnico t1 = new Tecnico("Carlos", "123", "Computadores");
        Equipo e1 = new Equipo("Laptop");
        orden = new OrdenMantenimiento(e1, t1);

        //  TABLA
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

        // DATOS INICIALES
        modeloOrdenes.addRow(new Object[]{
                t1.getNombre(),
                t1.getId(),
                e1.getNombre(),
                "Pendiente"
        });

        //  BOTONES
        JButton iniciar = new JButton("Iniciar");
        iniciar.setBounds(200, 300, 150, 40);
        iniciar.setBackground(botonColor);
        iniciar.setForeground(Color.WHITE);
        iniciar.setFocusPainted(false);

        JButton finalizar = new JButton("Finalizar");
        finalizar.setBounds(400, 300, 150, 40);
        finalizar.setBackground(new Color(40, 167, 69));
        finalizar.setForeground(Color.WHITE);
        finalizar.setFocusPainted(false);

        // ⚡ EVENTOS
        iniciar.addActionListener(e -> {
            orden.iniciar();
            actualizarEstado();
        });

        finalizar.addActionListener(e -> {
            orden.finalizar();
            actualizarEstado();
        });

        // 📦 AGREGAR COMPONENTES
        centro.add(scroll);
        centro.add(iniciar);
        centro.add(finalizar);

        ventana.add(top, BorderLayout.NORTH);
        ventana.add(centro, BorderLayout.CENTER);

        ventana.setVisible(true);
    }

    //  ACTUALIZAR TABLA
    private void actualizarEstado() {
        modeloOrdenes.setValueAt(orden.getEstado(), 0, 3);
    }
}