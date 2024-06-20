package kass.concurrente.crypto;

import kass.concurrente.constants.Constante;
import java.util.logging.Logger;

public class Buscador implements Runnable {
    private boolean ganamos = false;
    private static final Logger logger = Logger.getLogger(Buscador.class.getName());

    @Override
    public void run() {
        long inicio = System.nanoTime();
        String segmento = asignaLetras(Integer.parseInt(Thread.currentThread().getName()));
        for (int x = 0; x < segmento.length(); x++) {
            char char1 = segmento.charAt(x);
            for (int j = 0; j < Constante.ALFABETO.length(); j++) {
                char char2 = Constante.ALFABETO.charAt(j);
                for (int k = 0; k < Constante.ALFABETO.length(); k++) {
                    char char3 = Constante.ALFABETO.charAt(k);
                    for (int l = 0; l < Constante.ALFABETO.length(); l++) {
                        char char4 = Constante.ALFABETO.charAt(l);
                        for (int m = 0; m < Constante.ALFABETO.length(); m++) {
                            char char5 = Constante.ALFABETO.charAt(m);
                            for (int n = 0; n < Constante.ALFABETO.length(); n++) {
                                char char6 = Constante.ALFABETO.charAt(n);
                                String intento = "" + char1 + char2 + char3 + char4 + char5 + char6;
                                try {
                                    if (ganamos) {
                                        return;
                                    }
                                    if (Cifrar.descifraC(Constante.LLAVE, intento)) {
                                        ganamos = true;
                                        System.out.println("CONTRASENIA ENCONTRADA: " + intento);
                                        long fin = System.nanoTime();
                                        long total = fin - inicio;
                                        System.out.println("TIEMPO TOTAL para encontrar la contrasenia usando fuerza bruta: " + nanoSegundoASegundo(total));
                                        return;
                                    }
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo que pasa asigna una letra o letras al hilo dependiendo de su id
     * @param idHilo id del hilo
     */    
    private static String asignaLetras(int idHilo) {
        if (Constante.HILOS < 27) {
            int tamanoSegmento = (int) Math.ceil(26.0 / Constante.HILOS);
            int inicio = (idHilo - 1) * tamanoSegmento;
            int fin = Math.min(idHilo * tamanoSegmento, 26);
            String segmento = "";
            for (int i = inicio; i < fin; i++) {
                segmento += Constante.ALFABETO.charAt(i);
            }
            return segmento;
        } else {
            int indiceLetra = (idHilo - 1) % Constante.ALFABETO.length();
            return String.valueOf(Constante.ALFABETO.charAt(indiceLetra));
        }
    }

    /**
     * Metodo que pasa de nano segundos a segundos
     * @param tiempo Long que representa los nanosegundos que pasaran a segundos
     */
    public static double nanoSegundoASegundo(Long tiempo){
        return tiempo *1.0 * Math.pow(10,-9);
    }

    public static void main(String[] args) throws InterruptedException {
        Buscador b = new Buscador();
        Thread[] hilos = new Thread[Constante.HILOS];

        Long inicio = System.nanoTime();
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(b, String.valueOf(i + 1));
            hilos[i].start();
        }
        for (Thread hilo : hilos) {
            hilo.join();
        }

        Long fin = System.nanoTime();
        Long total = fin-inicio;
        logger.info("TIEMPO TOTAL PARA  " + Constante.HILOS + " HILOS: " + nanoSegundoASegundo(total));
        logger.info("Practica 2");
    }
}