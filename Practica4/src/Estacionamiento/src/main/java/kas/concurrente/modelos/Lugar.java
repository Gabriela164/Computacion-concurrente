package kas.concurrente.modelos;

import kas.concurrente.constante.Contante;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Clase que modela un Lugar
 * El lugar consta de un id
 * un booleano que nos dice si esta dispoible
 * y un objeto del tipo Semaphore (El semaforo)
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Lugar {
    Random random = new Random();
    private int id;
    volatile boolean estaDisponible = true;
    volatile boolean semaforo = true;
    volatile int vecesEstacionado;

    /**
     * Metodo constructor
     * El lugar por defecto esta disponible
     * Pueden llegar un numero n de carros en el peor de los casos
     * veces estacionado sera el numero de veces que se han estacianado en el lugar
     * Si llegan 2 carros y ambos se estacionan, entonces, su valor sera de 2
     * @param id El id del Lugar
     */
    public Lugar(int id){
        this.id = id;
        this.estaDisponible = true;
        this.vecesEstacionado = 0;
    }

    /**
     * En este metodo se simula que se estaciona
     * PELIGRO: ESTAS ENTRANDO A LA 2da SECCION CRITICA
     * Cambia el valor de disponible a false
     * Y se simula que vas pastel de cumple :D
     * Al final, imprime un texto color ROJO diciendo que va salir (Esperen instrucciones para esto)
     * @throws InterruptedException Si algo falla
     */
    public void estaciona() throws InterruptedException{
        try {
            while(!adquirirSemaforo()){
                Thread.sleep(Contante.MEDIO_SEGUNDO);
            }
            final Logger log = Logger.getLogger("kas.concurrente.modelos.Lugar");
            String resetColor = "\u001B[0m";
            estaDisponible  = false;
            semaforo = false;
            if (Contante.LOGS) log.info(Contante.ROJO + "Me he estacionado"+ resetColor);
            vePorPastel();
            vecesEstacionado +=  1;
            if (Contante.LOGS) log.info(Contante.ROJO + "Voy a salir"+ resetColor);
            estaDisponible = true;
            semaforo = true;
        }catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    /*
     * Metodo que adquiere el semaforo
     * @return true si el lugar esta disponible y el semaforo esta disponible
     * false en otro caso
     */
    private boolean adquirirSemaforo(){
        return estaDisponible && semaforo;
    }

    /**
     * En este metodo se genera la sumulación de espera
     * Se genera un tiempo entre 1 y 5 segundos
     * Es pseudo aleatorio
     * @throws InterruptedException En caso de que falle
     */
    public void vePorPastel() throws InterruptedException{
        int tiempoEspera = random.nextInt(Contante.UN_SEGUNDO);
        final Logger log = Logger.getLogger("kas.concurrente.modelos.Lugar");
        Thread.sleep(tiempoEspera);
        if (Contante.LOGS) log.info("VAMOS POR EL PASTEL JUJUI");
    }

    /*
     * Metodo que determina las veces que se ha estacionado un carro 
     * en el lugar
     */
    public int getVecesEstacionado() {
        return vecesEstacionado;    
    }

    /**
     * Metodo que asigna el id del lugar
     * @param id Id para el lugar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo que nos regresa el id del lugar
     * @return int el id del lugar
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo que nos indica si esta el lugar esta disponible
     * @return true si esta disponible, false en otro caso
     */
    public boolean getDisponible() {
        return estaDisponible;
    }

    /**
     * Metodo que cambia el estado de disponible del lugar
     * @param disponible nuevo estado de disponible del lugar
     */
    public void setDisponible(boolean disponible) {
        this.estaDisponible = disponible;
    }
}
