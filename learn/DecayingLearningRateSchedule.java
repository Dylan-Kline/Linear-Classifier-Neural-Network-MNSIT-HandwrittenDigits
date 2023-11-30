package learn;

public class DecayingLearningRateSchedule implements LearningRateSchedule {

    /* Returns a decaying learning rate given the integer value t */
    public double alpha (int t) {
        return 1000.0 / (1000.0 + t);
    }
}
