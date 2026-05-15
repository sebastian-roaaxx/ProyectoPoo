package Clases;

// Gracias a esto, la orden puede trabajar con cualquier tipo de equipo
// (Computador, Impresora, etc.) usando polimorfismo.
import ClasesAbstractas.Equipo;
import ClasesAbstractas.OrdenBase;
import ClasesAbstractas.Usuario;
import Interfaces.*;


public class OrdenMantenimiento extends OrdenBase
implements Asignable, Calculable, Priorizable, Reportable {  // SOLO USA ALGUNAS INTERFACES, NO TODAS Y ES HERENCIA MULTIPLE.

    // Guarda el cliente dueño de la orden.
    private Usuario cliente;

    private Equipo equipo;
    
    private Tecnico tecnico;

    private double presupuesto;

    private String problema;
    // La prioridad inicia por defecto en "Media" de los que ya tenemos en PANTALLA POR DEFECTO
    private String prioridad = "Media";

    // Aquí se inicializa toda la información principal
    // de una orden de mantenimiento
    public OrdenMantenimiento(Usuario c, Equipo e, double p, String problema) {
        cliente = c;
        equipo = e;
        presupuesto = p;
        this.problema = problema; // ESTE TIENE THIS PORQUE SE LLAMA IGUAL
                                 // ENTONCES, PARA DIFERENCIARLO SE USA THIS.
    }

    // CONSTRUCTOR SOBRECARGADO.
    // Permite crear una orden sin problema registrado
    
    public OrdenMantenimiento(Usuario c, Equipo e, double p) {
        this(c, e, p, "Sin descripción");        // SI EN LA INTERFAZ GRAFICA CREAMOS OTRA NUEVA ORDEN 
                                                    // Y NO SE INGRESA UN PROBLEMA, SE ASIGNA "Sin descripción" POR DEFECTO
    }

    // SOBRECARGA DE CONSTRUCTOR.
    // El presupuesto se coloca automáticamente en 0.
    public OrdenMantenimiento(Usuario c, Equipo e) {
        this(c, e, 0);
    }

    // Método encargado de asignar un técnico DE LA INTERFAZ ASIGNABLE.
    public void asignarTecnico(Tecnico t) {
        if (t == null)
            throw new IllegalArgumentException("Sin técnico"); // THROW ES EXEPCCION Y NO SE EJECUTA EL PROGRAMA EN GENERAL
        tecnico = t;
    }

    // Método que inicia el mantenimiento.
    public void iniciar() {                                         
        if (tecnico == null) {
            System.out.println("No hay técnico"); // ESTO SALE ES EN CONSOLA SI INICIAMOS UNA ORDEN SIN TECNICO.
            return;
        }
   // esto es de ORDENBASE
        estado = "En proceso";
        equipo.iniciarMantenimiento();
        // Llama al método del técnico.
        tecnico.reparar();
    }

    // SOBRECARGA DEL MÉTODO iniciar().
    public void iniciar(String prioridad) {
        this.prioridad = prioridad;
        
        iniciar();
    }

    // Método encargado de finalizar la orden.
    public void finalizar() {
        // Cambia el estado de la orden a "Finalizado". 
        estado = "Finalizado";
        // Finaliza el mantenimiento del equipo usando el método de la clase Equipo.
        equipo.finalizarMantenimiento();
    }

    // Método implementado desde la interfaz Calculable.
    public double calcularCosto() {
        return presupuesto; // En este caso, el costo es igual al presupuesto registrado.       
    }

    // Método adicional para calcular descuento este metodo es específico de esta clase
    // NO ES DE LA INTERFAZ CALCULABLE
    // aplica descuento del 5%.
    public double calcularCostoConDescuento() {
      // LA VARIABLE EMPIEZA EN 0 SIEMPRE
        double descuento = 0;
        if (presupuesto > 100000) {
            descuento = presupuesto * 0.05;
        }
        return presupuesto - descuento; // DEVUELVE DOUBLE CON EL DESCUENTO APLICADO SI CORRESPONDE
    }

    // Permite modificar la prioridad manualmente.
    public void setPrioridad(String p) {
        prioridad = p;
    }

    // Método implementado desde Reportable para enviar mensaje CONSOLA
    public void generarReporte() {
        // Usa getNombre() del cliente PARA MOSTRAR EL NOMBRE DEL CLIENTE EN EL REPORTE, ASI SE VE MAS PROFESIONAL
        System.out.println(
            "Orden de " +
            cliente.getNombre() +
            " estado: " +
            estado
        );
    }

    // GETTERS PARA OBTENER INFORMACIÓN CUANDO SE NECESITE MOSTRARLA EN LA INTERFAZ O USARLA EN OTROS LUGARES
    // YA QUE SON ATRIBUTOS PRIVADOS.  
    public String getEstado() {
        return estado;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    
    public String getPrioridad() {
        return prioridad;
    }

    public Usuario getCliente() {
        return cliente;
    }

    
    public Equipo getEquipo() {
        return equipo;
    }

   
    public String getProblema() {
        return problema;
    }
}