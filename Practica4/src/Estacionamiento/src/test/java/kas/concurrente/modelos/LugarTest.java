package kas.concurrente.modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kas.concurrente.constante.Contante;

public class LugarTest {
    Semaphore semaforo;
    Lugar lugar;
    List<Thread> hilos;
    static final int numHilos = 15;

    @BeforeEach
    void setUp() throws InterruptedException{
        lugar = new Lugar(1);
        semaforo = new Semaphore(1);
        initHilos();
    }

    @Test
    void constructorTest(){
        assertTrue(lugar.getId() == 1 && lugar.getDisponible() == true);
    }

    @Test
    void estacionaTest() throws InterruptedException{
        lugar.estaciona();
        assertTrue(lugar.getDisponible());
    }

    
    void initHilos(){
        hilos = new ArrayList<>();

        for(int i=0; i < numHilos*2; ++i){
            Thread t = new Thread();
            hilos.add(t);
        }
    }

    /**
     * AGREGA 2 TEST MAS
     * TEST bien hechos
     */

    @Test
    void verificaVecesEstacionadoTest() throws InterruptedException {
        assertEquals(0, lugar.getVecesEstacionado());
        lugar.estaciona();
        lugar.estaciona();
        assertEquals(2, lugar.getVecesEstacionado());
    }

    @Test
    void verificaVePorPastelTest() throws InterruptedException {
        long comienzo = System.currentTimeMillis();
        lugar.vePorPastel();
        long tiempoTotal = System.currentTimeMillis() - comienzo;
        assertTrue(tiempoTotal <= Contante.UN_SEGUNDO);
    }
}



