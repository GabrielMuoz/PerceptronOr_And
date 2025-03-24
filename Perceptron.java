
import java.util.Random;

public class Perceptron {
    private double[] pesos;
    private double sesgo;
    private double tasaAprendizaje;
    private boolean tieneSesgo;

    public Perceptron(int numEntradas, double tasaAprendizaje, boolean tieneSesgo) {
        this.pesos = new double[numEntradas];
        this.sesgo = tieneSesgo ? new Random().nextDouble() : 0;
        this.tasaAprendizaje = tasaAprendizaje;
        this.tieneSesgo = tieneSesgo;
        Random random = new Random();
        for (int i = 0; i < numEntradas; i++) {
            pesos[i] = random.nextDouble();
        }
    }

    private double sigmoid(double suma) {
        return 1 / (1 + Math.exp(-suma));
    }

    public void entrenar(int[][] entradas, int[] salidasEsperadas, int maxGeneraciones) {
        for (int gen = 1; gen <= maxGeneraciones; gen++) {
            boolean errorCero = true;
            System.out.println("Generacion " + gen + ":");
            for (int i = 0; i < entradas.length; i++) {
                double suma = 0;
                for (int j = 0; j < pesos.length; j++) {
                    suma += entradas[i][j] * pesos[j];
                }
                if (tieneSesgo) {
                    suma += sesgo;
                }
                double salidaPredicha = sigmoid(suma);
                int error = salidasEsperadas[i] - (int) Math.round(salidaPredicha);
                System.out.println("Entrada: " + entradas[i][0] + ", " + entradas[i][1] + ", Esperado: " + salidasEsperadas[i] + ", Predicho: " + Math.round(salidaPredicha) + ", Suma: " + suma + ", Sigmoide: " + salidaPredicha);
                if (error != 0) {
                    errorCero = false;
                    for (int j = 0; j < pesos.length; j++) {
                        pesos[j] += tasaAprendizaje * error * entradas[i][j];
                    }
                    if (tieneSesgo) {
                        sesgo += tasaAprendizaje * error;
                    }
                }
            }
            System.out.println();
            if (errorCero) break;
        }
    }

    public void imprimirResultados(boolean imprimirSesgo) {
        System.out.println("Pesos finales:");
        for (double peso : pesos) {
            System.out.print(peso + " ");
        }
        if (imprimirSesgo) {
            System.out.println("\nSesgo final: " + sesgo);
        } else {
            System.out.println(); 
        }
    }
}