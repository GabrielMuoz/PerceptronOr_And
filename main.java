public class main {
    public static void main(String[] args) {
        int[][] entradas = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        
        int[] salidasAND = {0, 0, 0, 1};
        Perceptron perceptronSinSesgo = new Perceptron(2, 0.1, false);
        perceptronSinSesgo.entrenar(entradas, salidasAND, 20);
        perceptronSinSesgo.imprimirResultados(false);  
        
        int[] salidasOR = {0, 1, 1, 1};
        Perceptron perceptronConSesgo = new Perceptron(2, 0.1, true);
        perceptronConSesgo.entrenar(entradas, salidasOR, 100);
        perceptronConSesgo.imprimirResultados(true);  
    }
}