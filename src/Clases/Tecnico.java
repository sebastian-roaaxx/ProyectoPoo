package Clases;

public class Tecnico {

    protected String nombre;
    protected int experiencia;
    protected double tarifa;
    protected String especialidad; 
// Constructor completo DE LOS TECNICOS CREADOS EN EL MAIN, CON TODOS LOS ATRIBUTOS
    public Tecnico(String nombre, int experiencia, double tarifa, String especialidad) {
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.tarifa = tarifa;
        this.especialidad = especialidad;
    }

    // SOBRECARGA CONSTRUCTOR POR SI QUEREMOS CREAR UN TECNICO CON SOLO EL NOMBRE
    // LOS OTROS ATRIBUTOS SE ASIGNAN POR DEFECTO
    public Tecnico(String nombre) {
        this(nombre, 1, 30000, "General");
    }
  // GETTERS PARA ACCEDER A LOS ATRIBUTOS DEL TECNICO DESDE OTRAS CLASES YA QUE SON PROTECTEDS
    public double getTarifa() {
         return tarifa; }
    public int getExperiencia() {
         return experiencia; }
    public String getNombre() {
         return nombre; }
    public String getEspecialidad() {
         return especialidad; }

    public void reparar() {                         // ESTE MENSAJE ES EN CONSOLA CUANDO SE INICIA EL MANTENIMIENTO DE UNA ORDEN,                    
        System.out.println(nombre + " reparando..."); // PARA SIMULAR QUE EL TECNICO ESTA REPARANDO
    }
}