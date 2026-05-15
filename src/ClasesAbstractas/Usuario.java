package ClasesAbstractas;

public abstract class Usuario { // ESTA CLASE ES PADRE
    protected String nombre;
    protected String id;

    public Usuario(String nombre, String id) { // ESTE ES EL CONSTRUCTOR DE USUARIO,
                                                //  LOS DEMÁS USUARIOS (CLIENTES Y TECNICOS) LO HEREDAN
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {  
        return nombre;
    }

    public abstract void mostrarInfo();
}