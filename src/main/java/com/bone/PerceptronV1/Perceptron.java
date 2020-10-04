package com.bone.PerceptronV1;

public class Perceptron {
    private  double weights[];
    private final double LEARNING_RATE = 0.05;


    public Perceptron(int nbInputs)
    {
        weights = new double[nbInputs];
        for(int i =0;i < nbInputs;i++)
                weights[i]=Math.random();
    }
    public double calculateWeightedSum(double[] inputs, double[] weights)
    {
        double weightedSum = 0;
        for (int i = 0; i < inputs.length ;i++)   weightedSum +=inputs[i] * weights[i];
        return weightedSum;
    }
    public int applyActivationFunction(double weightedSum)
    {
        return weightedSum > 0 ? 1 : -1;
    }
    public double[] adjustWeights(double[] inputs,double [] weights , double error){
        double[] adjustedWeights = new double[weights.length];
        for (int i = 0; i< weights.length;i++) adjustedWeights[i] = LEARNING_RATE * error *inputs[i] + weights[i];
        return  adjustedWeights;
    }
    public int guess(double [] inputs)
    {
        double sum = calculateWeightedSum(inputs,weights);
        return applyActivationFunction(sum);
    }
    public int train (double []inputs , int result)
    {
        int guess = guess(inputs);
        int error = result - guess;
        weights =   adjustWeights(inputs,weights,error);
        return guess;
    }
}
