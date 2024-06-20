package kass.concurrente.modelo.producto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlatilloTest {
    Platillo platillo1, platillo2, platillo3, arroz;
    ProductoInventario p2,p3;
    List<ProductoInventario> productos;
    
    @BeforeEach
    void setUp(){
        //p1 = new Platillo();
        //p2 = new Platillo("Test",10);
        //p3 = new Platillo(productos,10.,10,"Test3");
        platillo1 = new Arroz();
        platillo2 = new Arroz();
        p2 = new ProductoInventario("Test", 10);
        platillo2 = new ProductoInventario(platillo1, p2);
        
        productos = new ArrayList<>();
        annadeProductos();
        platillo3 = new Arroz();
        p3 = new ProductoInventario(productos, 10.0, 10, "Test3");
        platillo3 = new ProductoInventario(platillo3, p3);
    }

    void annadeProductos(){
        productos.add(new ProductoInventario("Producto1",5.0));
        productos.add(new ProductoInventario("Producto2",6.0));
        productos.add(new ProductoInventario("Productos3",7.0));
    }

    @Test
    void constructorTest1(){
        assertNotNull(platillo1);
    }

    @Test
    void constructorTest2(){
        assertTrue(platillo2.getNombrePlatillo().equals("Desayuno de Arroz con Test")&&platillo2.getTiempoCoccionPlatillo()==25);
    }

    @Test
    void constructorTest3(){
        assertTrue(platillo3.getNombrePlatillo().equals("Desayuno de Arroz con Test3")&&platillo3.getTiempoCoccionPlatillo()==25&&platillo3.getProductosRequeridos().size()==3);
    }

    @Test
    void calculaPrecio(){
        assertEquals(38., platillo3.calculaPrecio());
    }
}
