package kass.concurrente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kass.concurrente.modelo.cuchillo.*;
import kass.concurrente.modelo.persona.*;
import kass.concurrente.modelo.producto.*;
/**
 * Clase Main. Simula la cafeteria de un chef que atiende a los clientes.
 * Usando el patron de diseño Decorator, se crean platillos a partir de un componente base (Arroz) y se agregan productos de inventario.
 * y el patron de diseño Strategy, se simula la eleccion de un cuchillo aleatorio para disminuir el tiempo de coccion de los platillos.
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Main {

    /*
     * Metodo que devuelve la ganancia total del dia en la cafeteria. 
     * @param clientes: lista de clientes que han realizado una orden.
     */
    public static double getVentaTotal(List<Cliente> clientes){
        double ventaTotal = 0;
        for(Cliente cliente : clientes){
            for(Platillo platillo : cliente.getOrden()){
                ventaTotal += platillo.calculaPrecio();
            }
        }
        return ventaTotal;
    }

    /*
     * Metodo que simula la cocina de un chef para atender a los clientes.
     * @param clientes: lista de clientes que han realizado una orden.
     * @param chef: chef que atendera a los clientes.
     */
    public static void cocina(List<Cliente> clientes, Chef chef){
        // Variables para almacenar el tiempo de cocción y el pago total de la orden del cliente
        int tiempoOrdenCliente = 0;
        int pagoCliente = 0;

        for(Cliente cliente : clientes){
            System.out.println("\nEl chef " + chef.getNombre() + " está cocinando para el cliente " + cliente.getNombre());
            
            // Itera sobre cada platillo en la orden del cliente
            for(Platillo platillo : cliente.getOrden()){
                tiempoOrdenCliente += platillo.getTiempoCoccionPlatillo();
                pagoCliente += platillo.calculaPrecio();
            }
            int reduccion = tiempoOrdenCliente - chef.getCuchillo().corta();
            System.out.println("Tiempo de elaboración de la orden ha disminuido de " + tiempoOrdenCliente + " a " + reduccion
                                + "\nEl cliente " + cliente.getNombre() + " ha pagado " + pagoCliente + " por TODA su orden"
                                + "\n******Cliente despachado******\n");     
            tiempoOrdenCliente = pagoCliente = 0;
        }
    }


    public static void main(String[] args) {

        //Creamos productos de inventario
        ProductoInventario frijoles = new ProductoInventario("Frijoles", 20.0, 10, 5);
        ProductoInventario carne = new ProductoInventario("Carne", 55.0, 30,5);
        ProductoInventario tortilla = new ProductoInventario("Tortilla", 12.0, 100, 5);
        ProductoInventario queso = new ProductoInventario("Queso", 13.0, 55,5);
        ProductoInventario jitomate = new ProductoInventario("Jitomate", 5.0, 20,5);
        ProductoInventario cafe = new ProductoInventario("Cafe", 10.0, 80,5);
        ProductoInventario arroz = new ProductoInventario("Arroz", 15.0, 50,5);

        List<ProductoInventario> inventario = new ArrayList<>();
        inventario.add(frijoles);
        inventario.add(carne);
        inventario.add(tortilla);
        inventario.add(queso);
        inventario.add(jitomate);
        inventario.add(cafe);
        inventario.add(arroz);

        //Creamos 2 platillos y un producto de inventario. El arroz es nuestro componente base.
        Platillo platillo1 = new Arroz();
        Platillo platillo2 = new Arroz();

        //Agregamos productos de inventario a los platillos
        platillo1 = new ProductoInventario(platillo1, frijoles);
        platillo1 = new ProductoInventario(platillo1,tortilla);
        platillo1 = new ProductoInventario(platillo1, queso);
       
        platillo2 = new ProductoInventario(platillo2, jitomate);
        platillo2 = new ProductoInventario(platillo2, carne);
        platillo2 = new ProductoInventario(platillo2, queso);

        //Lista de ordenes realizadas por los clientes
        List<Platillo> orden1 = new ArrayList<>();
        orden1.add(platillo1);
        orden1.add(platillo2);

        List<Platillo> orden2 = new ArrayList<>();
        orden2.add(platillo2);
        
        //Lista con 1 solo producto del inventario
        List<Platillo> orden3 = new ArrayList<>();
        orden3.add(platillo1);

        //Creamos 3 nuevos clientes
        Cliente cliente1 = new Cliente("Erika", orden1);
        Cliente cliente2 = new Cliente("Rodrigo", orden2);
        Cliente cliente3 = new Cliente("Martha",orden3);

        //Creamos una lista de clientes
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);

        Cuchillo cuchilloB = new CuchilloBronce();
        Cuchillo cuchilloP = new CuchilloPlata();
        Cuchillo cuchilloO = new CuchilloOro();

        Chef chetCafeteria = new Chef("Esteban", 8, cuchilloP);
        Random random = new Random();
        int cuchilloAleatorio = random.nextInt(3)+1;

        
        switch(cuchilloAleatorio){
            case 1:
                chetCafeteria.setCuchillo(cuchilloB);
                cocina(clientes, chetCafeteria);
                break;
            case 2:
                chetCafeteria.setCuchillo(cuchilloP);
                cocina(clientes, chetCafeteria);
                break;
            case 3:
                chetCafeteria.setCuchillo(cuchilloO);
                cocina(clientes, chetCafeteria);
                break;
            default:
                break;
        }

        //Finalmente, mostramos el inventario actualizado y la venta total del dia
        System.out.println("Inventario");
        for(ProductoInventario producto : inventario){
            System.out.println("Producto: " + producto.getNombreProducto() + " => Cantidad: "                     
                                + producto.getCantidad());
        }
        System.out.println("\nVenta total del dia: " + getVentaTotal(clientes));
    }
}

