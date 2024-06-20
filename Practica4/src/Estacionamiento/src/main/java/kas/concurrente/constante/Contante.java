package kas.concurrente.constante;

/**
 * Clase de constantes que alberga nuestro programa
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Contante {
    public static final Integer NUM_CARROS = 10000;
    public static final int NUM_CARROS_PERMITIDOS = 50;
    public static final Boolean LOGS = true;
    public static final Integer DIEZ_SEGUNDOS = 10000;
    public static final Integer CINCO_SEGUNDOS = 5000;
    public static final Integer UN_SEGUNDO = 1000;
    public static final Integer MEDIO_SEGUNDO = 500;
    public static final String ROJO = "\u001B[31m";
    public static final String AZUL = "\u001B[34m";
    /*Constructor local*/
    private Contante() {
        throw new IllegalStateException("Utility class");
    }
}
