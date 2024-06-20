package kass.concurrente.modelo.persona;

import kass.concurrente.modelo.cuchillo.*;
import java.util.ArrayList;
import java.util.List;

public class Chef extends Persona{

    Cuchillo cuchillo;
    int experiencia;
    List<Cliente> clientes = new ArrayList<>();

    /*
     * Constructor de la clase Chef. 
     * @param nombre: nombre del chef
     * @param experiencia: años de experiencia del chef
     * @param cuchillo: cuchillo magico que usara el chef para cortar los ingredientes.
     */
    public Chef(String nombre, int experiencia, Cuchillo cuchillo){
        super(nombre);
        this.experiencia = experiencia;
        this.cuchillo = cuchillo;
    }

    @Override
    public String getNombre(){
        return nombre;
    }

    /*
     * Metodo que permite que el chef pueda cambiar de cuchillo. 
     */
    public void setCuchillo(Cuchillo cuchillo){
        this.cuchillo = cuchillo;
    }

    /*
     * Metodo que devuelve el cuchillo que tiene el chef. 
     */
    public Cuchillo getCuchillo(){
        return cuchillo;
    }
    

}
