package kas.concurrente.modelos;

import kas.concurrente.constante.Contante;
import static kas.concurrente.constante.Contante.LOGS;

import java.util.logging.Logger;
import java.util.Random;

/**
 * En esta clase se simula el estacionamiento en si
 * Posee un conjunto de arreglos de tipo Lugar (o arreglo bidimensional?)
 * Posee un entero de lugaresDisponibles (Se podra hacer por pisos?) (Habra otra manera de hacerlo mejor?)
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Estacionamiento {

    private static Random random = new Random();
    int capacidadTotal;
    int lugaresDisponibles;
    Lugar[] lugares;

    /**
     * Metodo constructor
     * Modifica el constructor o crea otro segun consideres necesario
     * @param capacidad La capacidad del estacionamiento
     */
    public Estacionamiento(int capacidadTotal){
        this.lugaresDisponibles = capacidadTotal;
        this.capacidadTotal = capacidadTotal;
        this.lugares = new Lugar[capacidadTotal];
        inicializaLugares();
    }

    /**
     * Metodo que regresa la cantidad de lugares disponibles en el estacionamiento
     * @return int cantidad de lugares disponibles
     */
    public int getLugaresDisponibles() {
        return lugaresDisponibles;
    }

    /**
     * Metodo que asigna la cantidad de lugares disponibles en el estacionamiento
     * @param lugaresDisponibles La cantidad de lugares que estaran disponibles
     */
    public void setLugaresDisponibles(int lugaresDisponibles) {
        this.lugaresDisponibles = lugaresDisponibles;
    }

    /**
     * Metodo que nos indica si esta lleno el estacionamiento
     * @return true si esta lleno, false en otro caso
     */
    public boolean estaLleno(){
        return lugaresDisponibles == 0;
    }

    /**
     * Metodo que inicaliza los lugares del arreglo
     * Este es un método optativo
     */
    public void inicializaLugares(){
        for(int i = 0; i < capacidadTotal; i++){
            lugares[i] = new Lugar(i);
        }
    }

    /**
     * Metodo en el que se simula la entrada de un carro
     * Imprime un texto que dice que el carro a entrado de color AZUL
     * @param nombre El nombre del carro
     * @throws InterruptedException Si llega a fallar
     */
    public void entraCarro(int nombre) throws InterruptedException{
        final Logger log = Logger.getLogger("kas.concurrente.modelos.Estacionamiento");
        String resetColor = "\u001B[0m";
        if(LOGS) log.info(Contante.AZUL + "El carro " + nombre + " ha entrado" + resetColor);
        boolean logroEstacionarse = false;
        while (!logroEstacionarse) {
            for (int i = 0; i < capacidadTotal; i++) {
                if (lugares[i].getDisponible()) {
                    lugares[i].estaciona();
                    try {
                        logroEstacionarse = true;
                        lugaresDisponibles--;
                        break;
                    } finally {
                        // No logro estacionarse
                    }
                }
            }
        }
        Thread.sleep(Contante.MEDIO_SEGUNDO);
    }

    /**
     * Metodo que asigna el lugar, una vez asignado ESTACIONA su nave
     * @param lugar El lugar que correspone
     * @throws InterruptedException
     */
    public void asignaLugar(int lugar) throws InterruptedException {
        if(lugar<0 || lugar >= capacidadTotal){
            throw new IllegalArgumentException("El lugar esta fuera del rango la capacidad del estacionamiento");
        }
        if(lugares[lugar].getDisponible()){
            lugares[lugar].estaciona();
            lugares[lugar].setDisponible(false);
            lugaresDisponibles--;
        }
    }

    /**
     * Se obtiene un lugar de forma pseudoAleatoria
     * Aqui necesito que revisen el repaso de estadistica que mande en 
     * repaso, quiero que expliquen porque lo pedimos en forma pseudoAleatoria
     * @return Retorna el indice del lugar
     */
    public int obtenLugar(){
        double media = capacidadTotal/2.0;
        double desviacion = capacidadTotal/6.0;

        //Generamos un num aleatorio basado en la campana de Gauss
        int lugar = (int) (random.nextGaussian() * desviacion + media);

        if(lugar<0){
            lugar = 0;
        }else if(lugar>=capacidadTotal){
            lugar = capacidadTotal - 1;
        }
        return lugar;
    }

    /**
     * Regresa los lugares del estacionamiento
     * @return Lugar[] los lugares en el estacionamiento
     */
    public Lugar[] getLugares() {
        return lugares;
    }
}
