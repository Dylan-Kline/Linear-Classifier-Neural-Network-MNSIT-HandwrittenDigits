package nn;

/**
 * A PerceptronUnit is a Unit that uses a hard threshold
 * activation function.
 */
public class PerceptronUnit extends NeuronUnit {
	
	/**
	 * The activation function for a Perceptron is a hard 0/1 threshold
	 * at z=0. (AIMA Fig 18.7)
	 */
	@Override
	public double activation(double z) {
		return z >= 0.0 ? 1.0 : 0.0;
	}
	
	/**
	 * Update this unit's weights using the Perceptron learning
	 * rule (AIMA Eq 18.7).
	 * Remember: If there are n input attributes in vector x,
	 * then there are n+1 weights including the bias weight w_0. 
	 */
	@Override
	public void update(double[] x, double y, double alpha) {
		double prediction = h_w(x);

		double error = y - prediction;

		setWeight(0, getWeight(0) + alpha * error * 1.0);

		// Update the rest of the weights
		for (int i = 1; i < this.incomingConnections.size(); i++) {
			// Update rule: w_i <- w_i + alpha * (y - yHat) * x_i
			double xi = x[i-1]; // Adjust index for bias
			setWeight(i, getWeight(i) + alpha * error * xi);
		}
	}
}
