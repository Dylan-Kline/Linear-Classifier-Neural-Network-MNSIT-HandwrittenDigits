import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import learn.PerceptronClassifier;
import learn.Example;

public class Main {

    public static void main(String[] args) {

        String filename = "C://projects/CSC242-Project-4-dkline4/Examples/earthquake-clean.data.txt";
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
        PerceptronClassifier perceptron = new PerceptronClassifier(num_inputs);
        perceptron.train(examples, 700, 1);
    }
}
