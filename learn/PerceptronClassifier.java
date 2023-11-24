package learn;

import math.util.VectorOps;

public class PerceptronClassifier extends LinearClassifier {
    
    public PerceptronClassifier (double[] weights) {
        super(weights);
    }

    public PerceptronClassifier (int num_inputs) {
        super(num_inputs);
    }

    /* Performs the perceptron update rule using the given input, output, and alpha */
    public void update (double[] inputs, double output, double alpha) {

        // wi <- wi + alpha(output - threshold(x)) * xi
        for (int i = 0; i < inputs.length; i++) {
            double weight = this.weights[i];
            double current_input = inputs[i];
            double threshold_val = this.threshold(VectorOps.dot_product(this.weights, inputs));
            this.weights[i] = weight + alpha * (output - threshold_val) * current_input;
        }

    }

    /* Hard threshold function that returns 1 if the dot product of the weights and input vector is >= 0,
     * otherwise returns 0.
     */
    public double threshold (double wdotx) {
        if (wdotx >= 0) {
            return 1.0;
        } 
        else {
            return 0.0;
        }
    }
}
