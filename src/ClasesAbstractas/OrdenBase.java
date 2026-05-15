package ClasesAbstractas;

public abstract class OrdenBase {  // ESTE ES EL ESTADO DE LA ORDEN EN LA INTERFAZ GAFICA
                                  // Y ES CLASE PADRE 
    protected String estado = "Pendiente";

    public abstract void iniciar();
    public abstract void finalizar();
}