package kass.concurrente.modelo.producto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductoTest {
    //Producto p1,p2;
    Producto producto1,producto2;

    @BeforeEach
    void setUp(){
        //  p1 = new Producto("Test",10.);
        //  p2 = new Producto();
        producto1 = new ProductoInventario("Test0", 10);
        producto2 = new ProductoInventario();
      
    }

    @Test
    void constructorTest(){
        assertNotNull(producto2);
    }
}
