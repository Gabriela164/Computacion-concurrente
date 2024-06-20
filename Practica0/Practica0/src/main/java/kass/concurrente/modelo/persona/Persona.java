package kass.concurrente.modelo.persona;

/**
 * Clase que modela una persona
 * @author Kassandra Mirael
 */
public abstract class Persona {

    protected String nombre;

    /*
     * Constructor de la clase Persona. 
     * @param nombre: nombre de la persona.
     */
    protected Persona(String nombre){
        this.nombre = nombre;
    }

    /*
     * Metodo que devuelve el nombre de la persona. 
     */
    protected abstract String getNombre();

    
    
}
