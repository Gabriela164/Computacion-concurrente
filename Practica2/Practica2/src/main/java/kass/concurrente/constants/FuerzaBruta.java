package kass.concurrente.constants;

import kass.concurrente.crypto.Cifrar;

/**
 * Clase que realiza fuerza bruta para encontrar la contrasenia 
 * de una llave cifrada. 
 * @author Equipo DIA
 * @version 1.0
 */
public class FuerzaBruta {

    public static double nanoSegundoASegundo(Long tiempo){
        return tiempo *1.0 * Math.pow(10,-9);
    }

    public static void main(String[] args) {

        String llaveCifrada = Constante.LLAVE;
        long inicio = System.nanoTime();

        /*
         * Devuelve todas las posibles combinaciones del alfabeto de una cadena de caracteres
         * con longitud de 6. 
         */
        /** 
        for (int i = 0; i < Constante.ALFABETO.length(); i++) {
            char char1 = Constante.ALFABETO.charAt(i);
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
                                    if (Cifrar.descifraC(llaveCifrada, intento)) {
                                        System.out.println("CONTRASENIA ENCONTRADA: " + intento);
                                        long fin = System.nanoTime();
                                        long total = fin - inicio;
                                        System.out.println("TIEMPO TOTAL para encontrar la contrasenia usando fuerza bruta: " + nanoSegundoASegundo(total));
                                        return;
                                    }else{
                                        System.out.println("INTENTO DE CONTRASENIA: " + intento);
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
        */

        
        /***
        Al no poder soportar todas las combinaciones de 6 caracteres
        Cambiaremos manualmente la primera letra de la contrasenia
        Empezaremos con todas las posibles combinaciones del alfabeto con inicio en "a"
        y asi sucesivamente hasta que encontremos la contrasenia correcta.
        ***/
        for (int j = 0; j < Constante.ALFABETO.length(); j++) {
            char char1 = Constante.ALFABETO.charAt(4);
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
                                if (Cifrar.descifraC(llaveCifrada, intento)) {
                                    System.out.println("CONTRASENIA ENCONTRADA: " + intento);
                                    long fin = System.nanoTime();
                                    long total = fin - inicio;
                                    System.out.println("TIEMPO TOTAL para encontrar la contrasenia usando fuerza bruta: " + nanoSegundoASegundo(total));
                                    return;
                                }else{
                                    System.out.println("INTENTO DE CONTRASENIA: " + intento);
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

