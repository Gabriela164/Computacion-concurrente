package kass.concurrente.modelo.producto;

import java.util.List;

/**
 * Clase que modela un producto. Representa la clase Decorator en el patron de diseño Decorator.
 * @author Kassandra Mirael
 */
public abstract class Producto extends Platillo{
   
    /*
     * Objeto de tipo platillo que se envuelve en un producto de inventario
     */
    protected Platillo platilloCentro;
    
    protected String nombreProducto;
    protected double precioProducto;
    protected int tiempoProducto;   
    protected int cantidad;

    /*
     * Constructor sin parametros
     */
    protected Producto(){
        super();
    }
    
    /*
     * Contructor para envolver el platillo en un producto de inventario
     */
    protected Producto(Platillo platilloCentro,String nombre, double precio){
        this.platilloCentro = platilloCentro;
        this.nombreProducto = nombre;
        this.precioProducto = precio;
    }

    /*
     * Contructor para la creacion de un producto
     * @param nombre: nombre del producto
     * @param precio: precio del producto
     */
    protected Producto(String nombre, double precio){
        this.nombreProducto = nombre;
        this.precioProducto = precio;
    }

    /*
     * Contructor para la elaboración de un Platillo 
     */
    protected Producto(String nombre, int tiempoDeCoccion){
        super(nombre, tiempoDeCoccion);
    }

    /*
     * Contructor para la elaboracion de un platillo
     */
    protected Producto(List<ProductoInventario> productos, double precio, int tiempoDeCoccion, String nombre){
        super(productos, precio, tiempoDeCoccion, nombre);
    }

    /*
     * Metodo que devuelve el nombre del producto
     */
    public abstract String getNombreProducto();  

    /*
     * Metodo que devuelve el precio individual del producto
     */
    public abstract double getPrecioProducto();

    /*
     * Metodo que devuelve el tiempo de coccion del producto
    */
    public abstract int getTiempoCoccionProducto();

    /*
     * Metodo que devuelve la cantidad de productos en inventario
     */
    public abstract int getCantidad();

    

    

}
