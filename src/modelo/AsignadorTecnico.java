package modelo;

import java.util.List;

public class AsignadorTecnico {

    public static Tecnico asignar(List<Tecnico> lista, double presupuesto) {
        Tecnico mejor = null;

        for (Tecnico t : lista) {
            if (t.getTarifa() <= presupuesto) {
                if (mejor == null || t.getExperiencia() > mejor.getExperiencia()) {
                    mejor = t;
                }
            }
        }

        return mejor;
    }
}