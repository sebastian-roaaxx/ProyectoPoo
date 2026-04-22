package modelo;

import Interfaces.Notificable;
import Interfaces.Reportable;

public class Notificacion implements Notificable, Reportable {

    public void enviar() {
        System.out.println("Notificación enviada");
    }

    // SOBRECARGA
    public void enviar(String canal) {
        System.out.println("Enviado por " + canal);
    }

    public void generarReporte() {
        System.out.println("Reporte de notificación");
    }
}
