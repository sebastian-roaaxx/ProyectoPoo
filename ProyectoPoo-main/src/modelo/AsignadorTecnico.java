package modelo;

import java.util.List;

public class AsignadorTecnico {

    public static Tecnico asignar(List<Tecnico> lista, double presupuesto, String tipoEquipo) {
        Tecnico mejor = null;

        for (Tecnico t : lista) {
            if (t.getTarifa() <= presupuesto && (t.getEspecialidad().equals("General") || t.getEspecialidad().equals(tipoEquipo))) {
                if (mejor == null || t.getExperiencia() > mejor.getExperiencia()) {
                    mejor = t;
                }
            }
        }

        return mejor;
    }

    // Sobrecarga para compatibilidad
    public static Tecnico asignar(List<Tecnico> lista, double presupuesto) {
        return asignar(lista, presupuesto, "General");
    }
}