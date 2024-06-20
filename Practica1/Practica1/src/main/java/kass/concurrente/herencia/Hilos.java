package kass.concurrente.herencia;
import java.util.ArrayList;

/**
 * En esta clase, debes crear un contador extendiendo de la clase Thread
 * @author Kassandra Mirael
 * @version 1.1
 */
public class Hilos extends Thread {
    public static final Integer RONDAS = 10000;
    private static int valor = 0;

    /**
     * Metodo constructor.
     * @param nombre El nombre del hilo
     */
    public Hilos(String nombre){
        super(nombre);
    }

    @Override
    public void run() {
        if (getName().equals("11") || getName().equals("12") || getName().equals("13")){
            if(getName().equals("11") || getName().equals("12")){
                System.out.println("Soy el hilo " + getName() + " Dormire 1 milisegundo");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                suma();
            } else {
                System.out.println("Soy el hilo 13");
                suma();
            }
        } else {
            System.out.println("Hola soy el hilo: " + getName() + " de la lista de hilos");
        }
    }

    /*
     * Metodo que suma una unidad a la variable valor
     * RONDAS veces.
     */
    public void suma() {
        //Usamos synchronized para que solo un hilo pueda acceder a la vez
        synchronized(Hilos.class){
            for(int i = 0; i < RONDAS; ++i){
                valor = valor + 1;
            }
        }
    }

    /*
     * Metodo que devuelve el valor.
     */
    public static int getValor(){
        return valor;
    }

    public static void main(String[] args) throws InterruptedException {

        ArrayList<Hilos> listaHilos = new ArrayList<>();
        
        for(int i = 0; i < 10; i++){
            Hilos h = new Hilos(String.valueOf(i+1));
            listaHilos.add(h);
        }
        for(Hilos hilo: listaHilos){
            hilo.start();
        }
        for(Hilos hilo: listaHilos){
            hilo.join();
        }

        System.out.println("Fin parte 1");

        Hilos contador1 = new Hilos("11");
        Hilos contador2 = new Hilos("12");

        contador1.start();
        contador2.start(); // Se inician los hilos
        contador1.join();  // El hilo principal espera a que el hilo contador1 termine
        contador2.join();  // El hilo principal espera a que el hilo contador2 termine

        System.out.println("\nEl valor final con 2 hilos es: "+ Hilos.getValor());

        Hilos contador3 = new Hilos("13");
        contador3.start();
        contador3.join();


        System.out.println("El valor final con 3 hilos es: "+ Hilos.getValor());
    }
}
