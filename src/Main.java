
import Vista.VentanaPrincipal;
import modelo.Equipo;
import modelo.OrdenMantenimiento;
import modelo.Tecnico;
public class Main {
    // ACA ARRANCA TODO
    public static void main(String[] args) {

        new VentanaPrincipal();

        Tecnico t1 = new Tecnico("Carlos", "123", "Computadores");
        Equipo e1 = new Equipo("Laptop HP");

        OrdenMantenimiento orden = new OrdenMantenimiento(e1, t1);

        t1.mostrarInfo();

        orden.iniciar();
        orden.finalizar();
    }
}