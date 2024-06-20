package kass.concurrente.modelos;

import kass.concurrente.constantes.Contante;
import static kass.concurrente.constantes.Contante.LOGS;

import java.util.logging.Logger;

import java.util.Random;

/**
 * Clase que fungira como habitacion
 * Se sabe que existe un interruptor que nos dice
 * si el foco esta prendido o no
 * Se desconoce el estado inicial del foco (Usar un random, para que sea
 * pseudoaleatorio el estado inicial)
 * @author <DIA>
 * @version 1.0
 */
public class Habitacion {
    private boolean prendido;

    /**
     * Metodo Constructor
     * Aqui se define el como estara el foco inicialmente
     */
    public Habitacion(){
        Random r = new Random();
        this.prendido = r.nextBoolean();
    }

    /**
     * Metodo que permite al prisionero entrar a la habitacion
     * Recordemos que solo uno pasa a la vez, esta es la SECCION CRITICA
     * En este caso se controla desde fuera
     * Es similar al algoritmo que progonan y similar al de su tarea
     * El prisionero espera una cantidad finita de tiempo
     * @param prisionero El prisionero que viene entrando
     * @return false si ya pasaron todos, true en otro caso
     * @throws InterruptedException Si falla algun hilo
     */
    public boolean entraHabitacion(Prisionero prisionero) throws InterruptedException{
        final Logger LOG = Logger.getLogger("kass.concurrente.modelos.Habitacion");
        if (prendido) {
            if(prisionero.getEsVocero()){
                Vocero vocero = (Vocero) prisionero;
                prisionero.setMarcado(true);
                
                //Aumentamos el contador en 1. Ya que antes habia un prisionero 
                //que paso antes y dejo el foco prendido. 
                int conteoActual = vocero.getContador();
                vocero.setContador(conteoActual + 1);

                //Esperamos 1 segundos y apagamos el interruptor
                Thread.sleep(Contante.UN_SEGUNDO);
                prendido = false;
                if(LOGS) LOG.info(Contante.ROJO + "VOCERO CONTO UNO, Contador: " + vocero.getContador());

                // Si el contador es igual al doble del numero de prisioneros, entonces ya todos pasaron al menos
                // dos veces lo hacemos así para cubrir el caso en el que el interruptor inicia encendido
                if(vocero.getContador() >= 2 * Contante.PRISIONEROS){
                    if(LOGS) LOG.info(Contante.ROJO + "VOCERO DICE: Ya hemos pasado todos ");
                    return true;
                }
                return false;
            }else{
                //Como el foco esta prendido y el prisionero no es vocero no puede pasar
                return false;
            }
        }
        //Si el foco esta apagado y el prisionero no ha pasado o solamente ha pasado una vez
        if (prisionero.puedeEncender()) {
            prendido = true;
            if(LOGS) LOG.info(Contante.AZUL + "PRISIONERO " + prisionero.getId() + " PASO");
            prisionero.marcar();
            Thread.sleep(Contante.UN_SEGUNDO);
        }
        return false;
    }

    /**
     * Metodo get para el atributo prendido de la habitacion
     * @return prendido es true cuando el interruptor esta encendido, false en otro caso
     */
    public Boolean getPrendido() {
        return prendido;
    }

    /**
     * Metodo set para el atributo prendido de la habitacion
     * @param prendido true si un prisionero ha encendido el interruptor, false si el vocero lo apago
     */
    public void setPrendido(Boolean prendido) {
        this.prendido = prendido;
    }
}
