package kas.concurrente;

import kas.concurrente.modelos.Estacionamiento;

/**
 * Clase principal, la usaran para SUS pruebas
 * Pueden modigicar los valores estaticos para ver como funciona
 * NO USEN VALORES EXTREMEDAMENTE ALTOS, puede alentar mucho su compu
 * AQUI EJECUTAN LA SIMULACION
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Main implements Runnable{
    static final int NUM_CARROS = 100;
    Estacionamiento estacionamiento;
    boolean semaforoModificado;

    /**
     * Metodo constructor
     * Se inicializa el Semaforo Modificado con _______ true
     * Se inicaliza el Estacionamiento con _______ 200 lugares
     */
    public Main(){
        this.estacionamiento = new Estacionamiento(200);
        this.semaforoModificado = true;
    }

    /**
     * Una documentacion del main xD, esta bien 
     * Paso 0: Lee estas instrucciones
     * Paso 1: Crea el Objeto de tipo main
     * Paso 2: Crea Una estructura de datos que contenga a nuestros hilos
     * Paso 3: Genera con un ciclo, el cual inialice un numero igual de NUM_CARROS
     * Paso 4: No olvides agregarlos a la estructura e inicializarlos
     * Paso 5: Finalmente has un Join a tus hilos
     * @param args Los Argumentos
     * @throws InterruptedException Por si explota su compu al ponerle medio millon de hilos xD
     */
    public static void main(String[] args) throws InterruptedException{
        Main main = new Main();
        Thread[] hilos = new Thread[NUM_CARROS];

        for(int i = 0; i < NUM_CARROS; i++){
            hilos[i] = new Thread(main, String.valueOf(i));
        }
        for(int i = 0; i < NUM_CARROS; i++){
            hilos[i].start();
        }
        for(int i = 0; i < NUM_CARROS; i++){
            hilos[i].join();
        }
    }

    /**
     * Aqui esta su primer seccion crítica
     * Paso 1: Keep calm and ...
     * Paso 2: Beware with the concurrent code
     * Paso 3: Try to remember some basics of Java and POO
     * Paso 4: Obten el ID de tu hilo
     * Paso 5: TU CARRO (HILO) ENTRARA AL ESTACIONAMIENTO (Los Hilos simulan ser carros, 
     * no es necesario que generes clase Carro (puedes hacerlo si quieres))
     */
    @Override
    public void run(){
        int id = Integer.parseInt(Thread.currentThread().getName());
        try {
            estacionamiento.entraCarro(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
