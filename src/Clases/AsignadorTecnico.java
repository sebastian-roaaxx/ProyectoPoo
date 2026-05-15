package Clases;
// Importa la interfaz List de Java.
// List permite trabajar con listas dinámicas de objetos.
// En este caso se usa para almacenar técnicos.
import java.util.List;

public class AsignadorTecnico {      // ESTO ES PARA ASIGNAR EL MEJOR TECNICO A CADA ORDEN
    public static Tecnico asignar( List<Tecnico> lista,    double presupuesto,     String tipoEquipo) {

        Tecnico mejor = null;
        for (Tecnico t : lista) {
            if (t.getTarifa() <= presupuesto &&   (t.getEspecialidad().equals("General")

               ||

               t.getEspecialidad().equals(tipoEquipo))) {
                if (mejor == null ||
                    t.getExperiencia() >
                    mejor.getExperiencia()) {
                    mejor = t;
                }
            }
        }

        return mejor;
    }


    // SOBRECARGA DEL MÉTODO asignar(), POR SI QUEREMOS ASIGNAR UN TECNICO SOLO CON EL PRESUPUESTO 
    // Y QUE NO SE TENGA EN CUENTA EL TIPO DE EQUIPO, ENTONCES SE ASIGNA UN TECNICO GENERAL.
    public static Tecnico asignar(    List<Tecnico> lista,   double presupuesto) {
        return asignar(lista, presupuesto, "General");
    }
}