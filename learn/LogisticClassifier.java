package learn;

import java.lang.Math;

import math.util.VectorOps;


public class LogisticClassifier extends LinearClassifier{
    
    public LogisticClassifier (double[] weights) {
        super(weights);
    }

    public LogisticClassifier (int num_inputs) {
        super(num_inputs);
    }

    /* Logisitic update rule */
    public void update(double[] inputs, double output, double alpha) {

        /* wi <- wi + alpha(output - threshold) * threshold(1 - threshold) * xi */
        for (int i = 0; i < inputs.length; i++) {
            double weight = weights[i];
            double current_input = inputs[i];
            double threshold_val = this.threshold(VectorOps.dot_product(inputs, this.weights));

            /* Apply update rule */
            weights[i] = weight + alpha * (output - threshold_val) * current_input;
        }

    }

    /* Soft threshold function using the logistic threshold 1 / (1 + e^-1(w * x)) */
    public double threshold(double wdotx) {
        return 1 / (1 + Math.exp(-wdotx));
    }
}
