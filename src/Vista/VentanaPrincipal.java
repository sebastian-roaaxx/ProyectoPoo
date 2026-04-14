
package Vista;
// Importamos librerías de Swing para interfaz gráfica
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.*;


public class VentanaPrincipal {

    // Modelo de la tabla donde se mostrarán las órdenes
    private DefaultTableModel modeloOrdenes;

    // Lista que almacena las órdenes de mantenimiento
    private ArrayList<OrdenMantenimiento> ordenes = new ArrayList<>();

    // Constructor de la ventana
    public VentanaPrincipal() {

        // Creamos la ventana principal
        JFrame ventana = new JFrame("Sistema de Mantenimiento PRO");

        // Definimos tamaño de la ventana
        ventana.setSize(800, 500);

        // Definimos que el programa se cierre al cerrar la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Usamos un layout tipo BorderLayout
        ventana.setLayout(new BorderLayout());

        // 🔝 PANEL SUPERIOR (TÍTULO)
        JPanel top = new JPanel();

        // Color de fondo del panel superior
        top.setBackground(new Color(33, 37, 41));

        // Tamaño del panel superior
        top.setPreferredSize(new Dimension(800, 60));

        // Creamos un label para el título
        JLabel titulo = new JLabel("Sistema de Mantenimiento");

        // Color del texto
        titulo.setForeground(Color.WHITE);

        // Fuente del texto
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        // Agregamos el título al panel superior
        top.add(titulo);

        // 📊 PANEL CENTRAL
        JPanel centro = new JPanel(null);

        // Color de fondo del panel central
        centro.setBackground(new Color(245, 245, 245));

        // 🔹 CREACIÓN DE DATOS (OBJETOS)
        Tecnico t1 = new Tecnico("Carlos", "123", "PC");
        Tecnico t2 = new Tecnico("Tobias", "456", "Redes");
        Tecnico t3 = new Tecnico("Ana", "789", "Software");

        Equipo e1 = new Equipo("Laptop");
        Equipo e2 = new Equipo("Impresora");
        Equipo e3 = new Equipo("Servidor");

        // Creamos órdenes de mantenimiento relacionando equipo + técnico
        OrdenMantenimiento o1 = new OrdenMantenimiento(e1, t1);
        OrdenMantenimiento o2 = new OrdenMantenimiento(e2, t2);
        OrdenMantenimiento o3 = new OrdenMantenimiento(e3, t3);

        // Agregamos las órdenes a la lista
        ordenes.add(o1);
        ordenes.add(o2);
        ordenes.add(o3);

        // 🟦 CREACIÓN DE LA TABLA
        modeloOrdenes = new DefaultTableModel();

        // Definimos columnas
        modeloOrdenes.addColumn("Nombre");
        modeloOrdenes.addColumn("ID");
        modeloOrdenes.addColumn("Equipo");
        modeloOrdenes.addColumn("Estado");

        // Creamos la tabla con el modelo
        JTable tabla = new JTable(modeloOrdenes);

        // Altura de filas
        tabla.setRowHeight(25);

        // Fuente de la tabla
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Scroll para la tabla
        JScrollPane scroll = new JScrollPane(tabla);

        // Posición y tamaño del scroll
        scroll.setBounds(50, 50, 680, 200);

        // 📌 LLENAMOS LA TABLA CON LOS DATOS
        for (OrdenMantenimiento o : ordenes) {

            // Agregamos una fila con los datos de cada orden
            modeloOrdenes.addRow(new Object[]{
                    o.getTecnico().getNombre(), // nombre del técnico
                    o.getTecnico().getId(),     // id del técnico
                    o.getEquipo().getNombre(),  // nombre del equipo
                    o.getEstado()               // estado de la orden
            });
        }

        // 🔘 BOTÓN INICIAR
        JButton iniciar = new JButton("Iniciar");

        // Posición y tamaño
        iniciar.setBounds(200, 300, 150, 40);

        // Color de fondo
        iniciar.setBackground(new Color(0, 123, 255));

        // Color de texto
        iniciar.setForeground(Color.WHITE);

        // 🔘 BOTÓN FINALIZAR
        JButton finalizar = new JButton("Finalizar");

        // Posición y tamaño
        finalizar.setBounds(400, 300, 150, 40);

        // Color de fondo
        finalizar.setBackground(new Color(40, 167, 69));

        // Color de texto
        finalizar.setForeground(Color.WHITE);

        // ⚡ EVENTO DEL BOTÓN INICIAR
        iniciar.addActionListener(e -> {

            // Obtenemos la fila seleccionada
            int fila = tabla.getSelectedRow();

            // Verificamos que haya una fila seleccionada
            if (fila != -1) {

                // Iniciamos la orden correspondiente
                ordenes.get(fila).iniciar();

                // Actualizamos la tabla
                actualizarTabla();
            } else {

                // Mensaje si no selecciona nada
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            }
        });

        // ⚡ EVENTO DEL BOTÓN FINALIZAR
        finalizar.addActionListener(e -> {

            // Obtenemos la fila seleccionada
            int fila = tabla.getSelectedRow();

            // Verificamos que haya una fila seleccionada
            if (fila != -1) {

                // Finalizamos la orden correspondiente
                ordenes.get(fila).finalizar();

                // Actualizamos la tabla
                actualizarTabla();
            } else {

                // Mensaje si no selecciona nada
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            }
        });

        // Agregamos elementos al panel central
        centro.add(scroll);
        centro.add(iniciar);
        centro.add(finalizar);

        // Agregamos paneles a la ventana
        ventana.add(top, BorderLayout.NORTH);
        ventana.add(centro, BorderLayout.CENTER);

        // Hacemos visible la ventana
        ventana.setVisible(true);
    }

    // Método para actualizar la columna "Estado" de la tabla
    private void actualizarTabla() {

        // Recorremos todas las órdenes
        for (int i = 0; i < ordenes.size(); i++) {

            // Actualizamos el estado en la columna 3 (Estado)
            modeloOrdenes.setValueAt(ordenes.get(i).getEstado(), i, 3);
        }
    }
}