package kass.concurrente.modelo.producto;
import java.util.List;

public class Arroz extends Platillo{

    String nombreA = "Desayuno de Arroz con";
    double precioA = 20.0;
    int tiempoDeCoccionA = 15;
    ProductoInventario arroz1 = new ProductoInventario("Arroz", 20.0, 50, 15);
    
    /*Contructor sin parametros*/
    public Arroz() {
        super();
        productosRequeridos.add(arroz1);
    }

    @Override
    public String getNombrePlatillo() {
        return nombreA;
    }

    @Override
    public int getTiempoCoccionPlatillo() {
        return tiempoDeCoccionA;
    }

    @Override 
    public double calculaPrecio(){
        return precioA;
    }

    @Override
    public List<ProductoInventario> getProductosRequeridos(){
        return productosRequeridos;
    }
    
}
