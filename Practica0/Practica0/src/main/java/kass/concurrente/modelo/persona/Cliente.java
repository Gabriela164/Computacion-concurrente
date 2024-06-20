package kass.concurrente.modelo.persona;

import kass.concurrente.modelo.producto.Platillo;
import java.util.List;
import java.util.ArrayList;

public class Cliente extends Persona{
    
    List<Platillo> orden = new ArrayList<>();

    /*
     * Constructor de la clase Cliente.
     * @param nombre: nombre del cliente
     * @param orden: lista de platillos que el cliente ha ordenado
     */
    public Cliente(String nombre, List<Platillo> orden){
        super(nombre);
        this.orden = orden;
    }

    /*
    * Devuelve la orden que ha realizado el cliente. 
    */
    public List<Platillo> getOrden(){
        return orden;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

}
