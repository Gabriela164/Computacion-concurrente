package kass.concurrente.modelo.producto;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela un producto de un inventario
 * @author Kassandra Mirael
 */
public class ProductoInventario extends Producto{

    List<ProductoInventario> produc = new ArrayList<>();

    public ProductoInventario() {
        super();
    }

    /*
     * Contructor para envolver el platillo en un producto de inventario.
     * @param platilloCentro: nucleo a decorar.
     */

     public ProductoInventario(Platillo platilloCentro, ProductoInventario producto){
        super(platilloCentro, producto.getNombreProducto(), producto.getPrecioProducto());
        super.precioProducto = producto.getPrecioProducto();
        super.cantidad = producto.getCantidad();
        super.nombreProducto = producto.getNombreProducto();
        super.tiempoProducto = producto.getTiempoCoccionProducto();
        this.produc = producto.getProductosRequeridos();
        super.productosRequeridos.addAll(produc);
    }
    

    /*Contructor para la elaboracion de un platillo*/
    public ProductoInventario(String nombre, int tiempoDeCoccion){
        super(nombre, tiempoDeCoccion); 
        super.tiempoProducto = tiempoDeCoccion;
        super.nombreProducto = nombre;
    }   

    public ProductoInventario(String nombre, double precio, int  cantidad, int tiempoDeCoccion){
        super(nombre, precio);
        super.cantidad = cantidad-1;
        super.tiempoProducto = tiempoDeCoccion;
    }

    public ProductoInventario(String nombre, double precio, int  cantidad){
        super(nombre, precio);
        super.cantidad = cantidad;
    }

    /*
    Contructor para la creacion de un producto
     */
    public ProductoInventario(String nombre, double precio){
        super(nombre, precio);
    }

    /*
     * Contructor para la elaboracion de un Platillo. 
     */
    public ProductoInventario(List<ProductoInventario> productosRequeridos, double precio, int tiempoDeCoccion, String nombre){
        super(productosRequeridos, precio, tiempoDeCoccion, nombre);
        super.nombreProducto = nombre;
        super.tiempoProducto = tiempoDeCoccion;
        super.productosRequeridos = productosRequeridos;
        super.precioProducto = sumaPrecios(productosRequeridos);
    }


    @Override
    public int getCantidad(){
        return super.cantidad-1;
    }

    @Override
    public double getPrecioProducto(){
        return super.precioProducto;
    }

    @Override
    public String getNombreProducto(){
        return super.nombreProducto;
    }

    @Override
    public int getTiempoCoccionProducto(){
        return super.tiempoProducto;
    }

    @Override
    public double calculaPrecio() {
        return platilloCentro.calculaPrecio() + super.precioProducto;
    }

    @Override
    public List<ProductoInventario> getProductosRequeridos(){
        return productosRequeridos;
    }

    @Override   
    public String getNombrePlatillo() {
        return platilloCentro.getNombrePlatillo() + " " + super.nombreProducto;
    }

    @Override
    public int getTiempoCoccionPlatillo() {
        return platilloCentro.getTiempoCoccionPlatillo() + super.tiempoProducto;
    }

    /*
     * Metodo que suma los precios de una lista de productos. 
     * @param productos: lista de productos a sumar.
     */
    public static double sumaPrecios(List<ProductoInventario> productos){
        double suma = 0;
        for(Producto producto : productos){
            suma += producto.getPrecioProducto();
        }
        return suma;
    }
}
