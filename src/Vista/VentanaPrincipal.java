package Vista; // Este archivo pertenece al paquete Vista, que agrupa las clases de la interfaz gráfica

import Clases.*;         // Importa todas las clases del paquete Clases (Persona, Impresora, Computador, etc.)
import ClasesAbstractas.*; // Importa las clases abstractas (Equipo, Usuario, OrdenMantenimiento, etc.)
import java.awt.*;       // Importa las herramientas de diseño visual de Java (Color, Font, BorderLayout, etc.)
import javax.swing.*;    // Importa los componentes visuales de Swing (JFrame, JButton, JTable, etc.)
import javax.swing.table.*; // Importa herramientas específicas para manejar tablas (DefaultTableModel, JTableHeader)

public class VentanaPrincipal { // Clase principal de la interfaz gráfica

    private DefaultTableModel modelo; // Modelo de datos de la tabla — controla filas y columnas en memoria
    private JFrame v;                 // Ventana principal de la aplicación
    private JTextArea logArea;        // Área de texto donde se muestran mensajes del sistema al usuario

    public VentanaPrincipal() { // Constructor — se ejecuta cuando se crea la ventana con "new VentanaPrincipal()"

        v = new JFrame("Sistema de Mantenimiento"); // Crea la ventana con ese título en la barra superior
        v.setSize(1000, 650);                        // Define el tamaño de la ventana en píxeles (ancho x alto)
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra el programa completo al cerrar la ventana
        v.setLayout(new BorderLayout());             // Divide la ventana en zonas: NORTH, CENTER, SOUTH, EAST, WEST

        // COLORES — se definen una vez aquí para reutilizarlos en todos los componentes
        Color fondo = new Color(18, 18, 18);  // Negro oscuro para el fondo general
        Color panel = new Color(30, 30, 30);  // Gris oscuro para el encabezado
        Color azul  = new Color(88, 101, 242); // Azul morado para botones principales
        Color verde = new Color(46, 204, 113); // Verde para el botón Finalizar
        Color gris  = new Color(64, 68, 75);   // Gris para el botón Asignar

        // HEADER — título que aparece en la parte superior de la ventana
        JLabel titulo = new JLabel("Panel de Administración", JLabel.CENTER); // Etiqueta de texto centrada
        titulo.setForeground(Color.WHITE);                    // Color del texto: blanco
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Fuente, estilo negrita, tamaño 24
        JPanel top = new JPanel(new BorderLayout()); // Panel contenedor del título
        top.setBackground(panel);  // Fondo gris oscuro para el encabezado
        top.add(titulo);           // Agrega el título al panel superior

        // FORMULARIO — campos de texto para ingresar datos de una nueva orden
        JTextField txtCliente     = crearInput("Cliente");       // Campo para el nombre del cliente
        JTextField txtEquipo      = crearInput("Nombre Equipo"); // Campo para el nombre del equipo
        JTextField txtProblema    = crearInput("Problema");      // Campo para describir el problema
        JTextField txtPresupuesto = crearInput("Presupuesto");   // Campo para el presupuesto disponible
        JComboBox<String> cbTipo  = new JComboBox<>(new String[]{"Computador", "Impresora", "Televisor"}); // Lista desplegable para elegir tipo de equipo
        cbTipo.setBorder(BorderFactory.createTitledBorder("Tipo de Equipo")); // Borde con etiqueta "Tipo de Equipo"
        cbTipo.setBackground(Color.WHITE); // Fondo blanco para la lista desplegable
        JButton btnAgregar = crearBoton("Agregar", azul); // Botón para agregar la orden a la tabla

        JPanel form = new JPanel(new GridLayout(2, 5, 10, 10)); // Panel con cuadrícula de 2 filas y 5 columnas, separación de 10px
        form.setBackground(fondo); // Fondo negro oscuro
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Margen interno de 15px en todos los lados
        form.add(txtCliente); form.add(txtEquipo); form.add(cbTipo);          // Primera fila del formulario
        form.add(txtProblema); form.add(txtPresupuesto); form.add(btnAgregar); // Segunda fila del formulario

        // TABLA — muestra todas las órdenes registradas
        modelo = new DefaultTableModel(); // Crea el modelo de datos vacío que manejará filas y columnas
        for (String col : new String[]{"Cliente", "Equipo", "Problema", "Prioridad", "Costo", "Técnico", "Estado"})
            modelo.addColumn(col); // Agrega cada columna al modelo usando un for — evita repetir modelo.addColumn() 7 veces

        JTable tabla = new JTable(modelo); // Crea la tabla visual conectada al modelo de datos
        tabla.setRowHeight(30);            // Altura de cada fila en píxeles
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13)); // Fuente del contenido de la tabla
        tabla.setBackground(new Color(45, 45, 45)); // Fondo gris oscuro para las celdas
        tabla.setForeground(Color.WHITE);           // Texto blanco dentro de las celdas
        tabla.setGridColor(new Color(70, 70, 70));  // Color de las líneas entre celdas
        tabla.setSelectionBackground(azul);         // Color de fondo cuando seleccionas una fila
        tabla.setSelectionForeground(Color.WHITE);  // Color del texto cuando la fila está seleccionada
        JTableHeader header = tabla.getTableHeader(); // Obtiene el encabezado de la tabla para personalizarlo
        header.setBackground(panel);                  // Fondo del encabezado
        header.setForeground(Color.WHITE);            // Texto del encabezado en blanco
        header.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fuente negrita para los títulos de columna

        // BOTONES DE ACCIÓN
        JButton asignar   = crearBoton("Asignar", gris);   // Botón para asignar técnico a una orden
        JButton iniciar   = crearBoton("Iniciar", azul);   // Botón para cambiar estado a "En proceso"
        JButton finalizar = crearBoton("Finalizar", verde); // Botón para finalizar la orden y generar factura
        JPanel botones = new JPanel();       // Panel que agrupa los tres botones
        botones.setBackground(fondo);        // Fondo negro oscuro
        botones.add(asignar); botones.add(iniciar); botones.add(finalizar); // Agrega los botones al panel

        // LOGS — área donde se imprimen mensajes para el usuario
        logArea = new JTextArea(5, 50);    // Área de texto con 5 filas visibles y 50 columnas de ancho
        logArea.setEditable(false);        // El usuario no puede escribir aquí — es solo de lectura
        logArea.setBackground(new Color(20, 20, 20)); // Fondo casi negro
        logArea.setForeground(new Color(0, 255, 140)); // Texto en verde neón estilo terminal
        logArea.setFont(new Font("Consolas", Font.PLAIN, 13)); // Fuente monoespaciada estilo consola
        JScrollPane logScroll = new JScrollPane(logArea); // Envuelve el logArea en un scroll por si hay muchos mensajes

        // EVENTO — AGREGAR ORDEN
        btnAgregar.addActionListener(e -> { // Se ejecuta cuando el usuario hace clic en "Agregar"
            try {
                String tipo = cbTipo.getSelectedItem().toString(); // Obtiene el tipo de equipo seleccionado en la lista
                Equipo eq = tipo.equals("Impresora") ? new Impresora(txtEquipo.getText()) // Si es Impresora, crea Impresora
                          : tipo.equals("Televisor") ? new Televisor(txtEquipo.getText()) // Si es Televisor, crea Televisor
                          : new Computador(txtEquipo.getText());                           // Si no, crea Computador por defecto

                OrdenMantenimiento o = new OrdenMantenimiento( // Crea la orden con todos los datos del formulario
                    new Persona(txtCliente.getText(), "1"),     // Crea un cliente tipo Persona con el nombre ingresado
                    eq,                                          // El equipo creado arriba
                    Double.parseDouble(txtPresupuesto.getText()), // Convierte el texto del presupuesto a número decimal
                    txtProblema.getText()                         // El problema descrito en el formulario
                );
                Datos.ordenes.add(o);  // Guarda la orden en la lista global de órdenes
                actualizarTabla();     // Refresca la tabla para mostrar la nueva orden
                logArea.append("Orden agregada.\n"); // Muestra mensaje de confirmación en el log
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(v, "Datos inválidos"); // Si algo falla (ej: presupuesto no es número), muestra error
            }
        });

        // EVENTO — ASIGNAR TÉCNICO
        asignar.addActionListener(e -> {
            int fila = tabla.getSelectedRow(); // Obtiene el índice de la fila seleccionada (-1 si no hay ninguna)
            if (fila == -1) { JOptionPane.showMessageDialog(v, "Selecciona una fila"); return; } // Si no hay fila seleccionada, avisa y sale
            OrdenMantenimiento o = Datos.ordenes.get(fila); // Obtiene la orden correspondiente a esa fila
            Tecnico mejor = AsignadorTecnico.asignar(       // Busca el mejor técnico disponible según presupuesto y tipo de equipo
                Datos.tecnicos,
                o.calcularCosto(),
                o.getEquipo().getTipo()
            );
            if (mejor != null) {           // Si encontró un técnico compatible
                o.asignarTecnico(mejor);   // Lo asigna a la orden
                actualizarTabla();         // Refresca la tabla para mostrar el técnico asignado
                logArea.append("Técnico asignado.\n");
            } else {
                JOptionPane.showMessageDialog(v, "No hay técnico disponible"); // Avisa si no hay técnico que cumpla los criterios
            }
        });

        // EVENTO — INICIAR ORDEN
        iniciar.addActionListener(e -> {
            int fila = tabla.getSelectedRow(); // Obtiene la fila seleccionada
            if (fila != -1) {                  // Solo actúa si hay una fila seleccionada
                Datos.ordenes.get(fila).iniciar(); // Cambia el estado de la orden a "En proceso"
                actualizarTabla();                 // Refresca la tabla para reflejar el nuevo estado
                logArea.append("Mantenimiento iniciado.\n");
            }
        });

        // EVENTO — FINALIZAR ORDEN
        finalizar.addActionListener(e -> {
            int fila = tabla.getSelectedRow(); // Obtiene la fila seleccionada
            if (fila < 0) { JOptionPane.showMessageDialog(v, "Selecciona una fila"); return; } // Valida que haya fila seleccionada
            OrdenMantenimiento o = Datos.ordenes.get(fila); // Obtiene la orden de esa fila
            if (o.getTecnico() == null) { logArea.append("Asigna un técnico primero.\n"); return; } // No puede finalizar sin técnico
            o.finalizar();                       // Cambia el estado de la orden a "Finalizado"
            VistaFactura.mostrarFactura(o, v);   // Abre la ventana de factura con los datos de la orden
            actualizarTabla();                   // Refresca la tabla
            logArea.append("Orden finalizada.\n");
        });

        // LAYOUT — organiza todos los paneles dentro de la ventana
        JPanel centro = new JPanel(new BorderLayout()); // Panel central que contiene formulario y tabla
        centro.setBackground(fondo);
        centro.add(form, BorderLayout.NORTH);              // Formulario en la parte superior del centro
        centro.add(new JScrollPane(tabla), BorderLayout.CENTER); // Tabla con scroll en el centro

        JPanel inferior = new JPanel(new BorderLayout()); // Panel inferior que contiene botones y logs
        inferior.setBackground(fondo);
        inferior.add(botones, BorderLayout.NORTH);   // Botones arriba del panel inferior
        inferior.add(logScroll, BorderLayout.CENTER); // Logs debajo de los botones
        centro.add(inferior, BorderLayout.SOUTH);    // El panel inferior va al sur del centro

        v.add(top, BorderLayout.NORTH);    // Encabezado en la parte superior de la ventana
        v.add(centro, BorderLayout.CENTER); // Todo lo demás en el centro

        actualizarTabla(); // Carga las órdenes que ya existen en Datos.ordenes al abrir la app
        v.setVisible(true); // Hace visible la ventana — sin esto no se ve nada
    }

    // MÉTODO AUXILIAR — crea un campo de texto con borde y etiqueta
    private JTextField crearInput(String placeholder) {
        JTextField txt = new JTextField();
        txt.setBorder(BorderFactory.createTitledBorder(placeholder)); // Borde con el nombre del campo como etiqueta
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return txt; // Devuelve el campo ya configurado para usarlo en el formulario
    }

    // MÉTODO AUXILIAR — crea un botón con estilo consistente
    private JButton crearBoton(String texto, Color color) {
        JButton b = new JButton(texto);
        b.setBackground(color);           // Color de fondo según el tipo de acción
        b.setForeground(Color.WHITE);     // Texto blanco
        b.setFocusPainted(false);         // Quita el borde de foco que aparece al hacer clic
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano al pasar por encima
        b.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding interno: 10px arriba/abajo, 20px izquierda/derecha
        return b; // Devuelve el botón configurado
    }

    // MÉTODO — recarga la tabla con los datos actuales de Datos.ordenes
    private void actualizarTabla() {
        modelo.setRowCount(0); // Borra todas las filas actuales de la tabla para evitar duplicados
        for (OrdenMantenimiento o : Datos.ordenes) { // Recorre cada orden guardada
            modelo.addRow(new Object[]{ // Agrega una fila nueva con los datos de esa orden
                o.getCliente().getNombre(),   // Nombre del cliente
                o.getEquipo().getNombre(),    // Nombre del equipo
                o.getProblema(),              // Descripción del problema
                o.getPrioridad(),             // Prioridad asignada (Alta, Media, Baja)
                o.calcularCosto(),            // Costo calculado según la lógica de la orden
                o.getTecnico() != null ? o.getTecnico().getNombre() : "Sin asignar", // Muestra el técnico o "Sin asignar" si es null
                o.getEstado()                 // Estado actual: Pendiente, En proceso, Finalizado
            });
        }
    }
}