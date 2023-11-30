import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import learn.PerceptronClassifier;
import learn.DecayingLearningRateSchedule;
import learn.Example;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        /* Ask the user to select a classifier to train */
        System.out.println("Select the classifier you wish to train: ");
        System.out.println("    1: Perceptron Classifier");
        System.out.println("    2: Logistic Classifier");
        System.out.print("Enter a choice: ");

        int classifier_type = 0;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid input.");
                scanner.next();
            }

            classifier_type = scanner.nextInt();

            if (classifier_type < 1 || classifier_type > 2) {
                System.out.println("Enter either 1 or 2.");
            }
        }
        while (classifier_type < 1 || classifier_type > 2);

        System.out.println();

        /* Ask user what file they wish to train on */
        String filename = null;
        System.out.println("Select which data file you wish to train with: ");
        System.out.println("    1: Clean Earthquake Data");
        System.out.println("    2: Noisy Earthquake Data");
        System.out.println("    3: Numeric House Votes Data");
        System.out.print("Enter a choice: ");

        int file_choice = 0;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid input.");
                scanner.next();
            }

            file_choice = scanner.nextInt();

            if (file_choice < 1 || file_choice > 3) {
                System.out.println("Enter either 1, 2, or 3.");
            }
        }
        while (file_choice < 1 || file_choice > 3);

        System.out.println();

        /* Set filename based on user's file choice */
        if (file_choice == 1) {
            filename = "./Examples/earthquake-clean.data.txt";
        }
        else if (file_choice == 2) {
            filename = "./Examples/earthquake-noisy.data.txt";
        }
        else {
            filename = "./Examples/house-votes-84.data.num.txt";
        }

        /* Ask the user how many iterations they wish to perform */
        System.out.print("Enter the number of iterations you wish to train for or enter a nonpositive number or 0 for the default number: ");
        while(!scanner.hasNextInt()) {
            System.out.println("Please enter a valid input.");
            scanner.next();
        }

        /* Set the iteration count based on user input. Set to default if 0 or nonpositive */
        int iteration_count = scanner.nextInt();
        if (iteration_count <= 0) {
            iteration_count = 5000;
        }

        BufferedReader reader = null;
        List<Example> examples = new ArrayList<>();

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
                double output_data = Double.parseDouble(string_data[string_data.length - 1]);

                Example example = new Example(inputs_data, output_data);
                examples.add(example);

                System.out.println(example);
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

        int num_inputs = examples.get(0).inputs.length;

        if (classifier_type == 1) {     
            PerceptronClassifier perceptron = new PerceptronClassifier(num_inputs);
            perceptron.train(examples, iteration_count, new DecayingLearningRateSchedule());
        }

        scanner.close();
    }
}
