package math.util;

public class VectorOps {
    
    /* Returns the dot product of two given vectors */
    public static double dot_product(double[] x, double[] y) {
        double sum = 0.0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i] * y[i];
        }
        return sum;
    }
}
