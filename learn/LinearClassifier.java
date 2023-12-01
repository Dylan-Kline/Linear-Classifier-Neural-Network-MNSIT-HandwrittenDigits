package learn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import math.util.VectorOps;

abstract class LinearClassifier {

    public double[] weights;

    LinearClassifier(double[] weights) {
        this.weights = weights;
    }

    LinearClassifier(int nInputs) {
        this(new double[nInputs]);
    }

    /** Updates the weights using the input/output variables and alpha */
    abstract public void update (double[] inputs, double output, double alpha);

    /** Approximation function for the given dot product of the current weights and the input vector.
     * @param wdotx
     * @return threshold value
     */
    abstract public double threshold (double wdotx);

    /** Returns the threshold value after performing the dot product of the weight vector and x vector */
    public double evaluate (double[] x) {
        return threshold(VectorOps.dot_product(this.weights, x));
    }

    /* Trains the model with the given set of examples for the given number of steps, using
     * a variable learning rate schedule.
    */
    public void train(List<Example> examples, int num_steps, LearningRateSchedule schedule) {
        Random random = new Random();
        int num_of_examples = examples.size();

        try (PrintWriter writer = new PrintWriter(new File("training_data.csv"))) {
            writer.println("Step,Accuracy"); // Header for CSV file

            /* Uses a stochastic learning methodology to train the model with the examples for the num_steps */
            for (int i = 0; i < num_steps; i++) {

                /* Randomly grab an example from the list of examples to train on */
                int example_index = random.nextInt(num_of_examples);
                Example example = examples.get(example_index);
                this.update(example.inputs, example.output, schedule.alpha(i + 1));
                this.training_report(examples, i + 1, writer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    
    }

    /* Trains the model with the given set of examples using a constant learning rate */
    public void train(List<Example> examples, int num_steps, double alpha) {
        train(examples, num_steps, new LearningRateSchedule() {
            public double alpha(int t) { return alpha; }
        });
    }

    /* Training report that prints out the current step number and the accuracy  */
    private void training_report(List<Example> examples, int step_num, PrintWriter writer) {
        double acc = accuracy(examples);
        System.out.println("Step num: " + step_num + "\t" + "Accuracy: " + acc);
        writer.println(step_num + "," + acc);
    }

    // (squaredErrorPerSample squared error function that returns the result of the total L2 loss divided by the number of examples
    
    /* Returns the proportion for which the model is correct for the given set of examples */
    private double accuracy(List<Example> examples) {
        double num_correct = 0.0;
        for (Example example : examples) {
            double result = evaluate(example.inputs);
            result = (result >= 0.5) ? 1.0 : 0.0;
            if (result == example.output) {
                num_correct += 1;
            }
        }

        return (double)num_correct / examples.size();
    }
}