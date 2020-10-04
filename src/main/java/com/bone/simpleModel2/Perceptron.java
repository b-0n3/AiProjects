package com.bone.simpleModel2;

public class Perceptron {
    public static final int THRESHOLD =1;
    public static final int[][][] andData = {

                                                {{0, 1}, {0}},
                                                {{1, 0}, {0}},
                                                {{1, 1}, {1}},
            {{0, 0}, {0}}};
    public static  final double LEARNING_RATE = 0.05;
    public static final double[] INITIAL_WEIGHTS = {Math.random(), Math.random()};

    public double calculateWeightedSum(int[] data, double[] weights)
    {
        double weightedSum = 0;
        for (int i = 0; i < data.length ;i++)   weightedSum +=data[i] * weights[i];
        return weightedSum;
    }

    public int applyActivationFunction(double weightedSum)
    {
        return weightedSum > THRESHOLD ? 1 : 0;
    }

    public double[] adjustWeights(int[] data,double [] weights ,double error){
        double[] adjustedWeights = new double[weights.length];
        for (int i = 0; i< weights.length;i++) adjustedWeights[i] = LEARNING_RATE * error *data[i] + weights[i];
        return  adjustedWeights;
    }
}