import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import nn.Example;
import nn.MultiLayerFeedForwardNeuralNetwork;


public class nn_main {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        /* Ask user what file they wish to train on */
        String filename = null;
        System.out.println("Select which data file you wish to train with: ");
        System.out.println("    1: Iris dataset");
        System.out.println("    2: MNIST dataset");
        System.out.print("Enter a choice: ");

        int file_choice = 0;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid input.");
                scanner.next();
            }

            file_choice = scanner.nextInt();

            if (file_choice < 1 || file_choice > 2) {
                System.out.println("Enter either 1, or 2");
            }
        }
        while (file_choice < 1 || file_choice > 2);

        System.out.println();

        /* Set filename based on user's file choice */
        if (file_choice == 1) {
            filename = "./nn/Examples/iris.data.txt";
        }
        else if (file_choice == 2) {
            filename = "./Examples/earthquake-noisy.data.txt";
        }

        /* Ask the user how many epochs they wish to perform */
        System.out.print("Enter the number of epochs you wish to train for or enter a nonpositive number or 0 for the default number: ");
        while(!scanner.hasNextInt()) {
            System.out.println("Please enter a valid input.");
            scanner.next();
        }

        /* Set the iteration count based on user input. Set to default if 0 or nonpositive */
        int epochs = scanner.nextInt();
        if (epochs <= 0) {
            epochs = 500;
        }

        /* Parse the file for the data to use for training */
        BufferedReader reader = null;
        List<Example> examples = new ArrayList<>();
        Map<String, double[]> classMapping = new HashMap<>();
        classMapping.put("Iris-setosa", new double[]{1, 0, 0});
        classMapping.put("Iris-versicolor", new double[]{0, 1, 0});
        classMapping.put("Iris-virginica", new double[]{0, 0, 1});

        try {
            reader = new BufferedReader(new FileReader(filename));

            String line;
            while((line = reader.readLine()) != null) {
                String[] string_data  = line.split(",");
                double[] inputs_data = new double[string_data.length - 1];
                
                // Convert each data value to a double up to the last element
                for (int i = 0; i < string_data.length - 1; i++) {
                    inputs_data[i] = Double.parseDouble(string_data[i]);
                }

                // convert the last element of the string data to a double and set as the output value
                double[] outputs = classMapping.get(string_data[string_data.length - 1]);

                if (outputs != null) {
                    examples.add(new Example(inputs_data, outputs));
                }
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        MultiLayerFeedForwardNeuralNetwork nn = new MultiLayerFeedForwardNeuralNetwork(4, 7, 3);
        nn.train(examples, epochs, .01);

        scanner.close();
    }
}
