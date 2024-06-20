package kass.concurrente.modelo.producto;

import java.util.List;
import java.util.ArrayList;

/**
 * Interface que simula un platillo, el cual es un conjunto de items 
 * @author Kassandra Mirael
 */
public abstract class Platillo {

    protected int tiempoDeCoccionP;
    protected String nombrePlatillo;
    protected double precioPlatillo;
    protected List<ProductoInventario> productosRequeridos = new ArrayList<>();

    /*
     * Constructor sin parametros.
     * Inicializa los atributos de la clase.
     */
    protected Platillo(){
        this.nombrePlatillo = "";
        this.tiempoDeCoccionP = 0;
        this.tiempoDeCoccionP = 0;
        this.productosRequeridos = new ArrayList<>();
    }

    /*
     * Contructor para la elaboracion de un platillo. 
     * @param productos: lista de productos de inventario que se necesitan para elaborar el platillo.
     * @param precio: precio total del platillo. 
     * @param tiempoDeCoccionP: tiempo de coccion total del platillo. 
     * @param nombre: nombre del platillo
     */
    protected Platillo(List<ProductoInventario> productosRequeridos, double precio, int tiempoDeCoccionP, String nombreP){
        this.productosRequeridos = productosRequeridos;
        this.tiempoDeCoccionP = tiempoDeCoccionP;
        this.nombrePlatillo = nombreP;
        this.precioPlatillo = precio;
    }

    /*
     * Contructor para la elaboracion de un Platillo.
     * @param nombre: nombre del platillo
     * @param tiempoDeCoccion: tiempo de coccion total del platillo.
     */
    protected Platillo(String nombre,int tiempoDeCoccionP){
        this.nombrePlatillo = nombre;
        this.tiempoDeCoccionP = tiempoDeCoccionP;
    }
    
    /*
     * Metodo que devuelve el nombre del platillo.
     */
    public abstract String getNombrePlatillo();

    /*
     * Método que devuelve el tiempo total de coccion del platillo. Suma cada tiempo
     * de coccion de cada uno de sus productos que se necesitan para
     * preparar el platillo.
     * @return int tiempo total del platillo 
     */
    public abstract int getTiempoCoccionPlatillo();

    /*
     * Método que devuelve el precio total del platillo. 
     * Suma el precio de cada uno sus productos. 
     * @return double precio total del platillo
     */
    public abstract double calculaPrecio();

    /*
     * Método que devuelve una lista productos de inventario que se necesitan
     * para preparar el platillo.
     * @return List<Producto> productos requeridos
     */
    public abstract List<ProductoInventario> getProductosRequeridos();
    
}
