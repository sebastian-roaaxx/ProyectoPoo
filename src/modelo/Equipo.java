package modelo;
import Interfaces.gestionable;

// ESTA CLASE IMPLEMENTA LA INTERFAZ GESTIONABLE, LO QUE SIGNIFICA QUE DEBE DEFINIR LOS METODOS DECLARADOS EN ESA INTERFAZ//
public class Equipo implements gestionable{
     String marca;
     String nombre;
    String estado;
     String modelo;
// ACA SE CREA EL CONSTRUCTOR PARA PODER CREAR OBJETOS DE LA CLASE EQUIPO//
 public Equipo(String marca, String nombre, String estado, String modelo){
    this.marca = marca;
    this.nombre = nombre;
    this.estado = estado;
    this.modelo = modelo;
 }


    // FALTA AGREGAR ACCIONES A CADA METODO DE LA INTERFAZ//
    public void registrar() {
        System.out.println("Registrando equipo: ");
    }

    
    public void actualizar() {}
        
    

    
    public void eliminar() {}
    

   
    public void mostrarInformacion() {}
    

  }

