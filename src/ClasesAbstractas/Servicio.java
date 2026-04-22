package ClasesAbstractas;

public abstract class Servicio {
    protected String descripcion;

    public Servicio(String descripcion) {
        this.descripcion = descripcion;
    }

    public abstract void ejecutar();
}