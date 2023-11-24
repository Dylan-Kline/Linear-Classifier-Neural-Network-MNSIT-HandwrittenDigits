package learn;

public class Example {
    
    public double[] inputs;
    public double output;

    public Example (double[] inputs, double output) {
        this.inputs = inputs;
        this.output = output;
    }

    public Example (int nInputs) {
        this(new double[nInputs], 0.0);
    }

}
