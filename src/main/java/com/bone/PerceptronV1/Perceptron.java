package com.bone.PerceptronV1;

public class Perceptron {
    private  double weights[];
    private final double LEARNING_RATE = 0.01;

    public Perceptron(int nbInputs)
    {
        weights = new double[nbInputs + 1];
        /*
        *
        * */
        for(int i =0;i < nbInputs + 1; i++) {
            weights[i] = Point.random(-1,1);
            System.out.println(weights[i]);
        }
        }
    public double calculateWeightedSum(double[] inputs, double[] weights)
    {
        double weightedSum = 0;
        for (int i = 0; i < inputs.length ;i++)   weightedSum += inputs[i] * weights[i];
        return weightedSum;
    }
    public int applyActivationFunction(double weightedSum)
    {
        return weightedSum > 0 ? 1 : -1;
    }

    public double[] adjustWeights(double[] inputs, double [] weights , double error){
        double[] adjustedWeights = new double[weights.length];
        for (int i = 0; i< weights.length;i++)
            adjustedWeights[i] = LEARNING_RATE * error * inputs[i] + weights[i];
        return  adjustedWeights;
    }
    public int guess(double [] inputs)
    {
        double sum = calculateWeightedSum(inputs, weights);
        return applyActivationFunction(sum);
    }

    public int train (double []inputs , int result)
    {
        int guess = guess(inputs);
        print(inputs);
        int error = result - guess;
        weights =   adjustWeights(inputs,weights,error);
        return guess;
    }

    public double guessY(double x) {
        double w0 = weights[0];
        double w1 = weights[1];
        double w2 = weights[2];
        return -(w2 / w1) - (w0 / w1) * x;
    }




    public void print(double [] inputs)
    {
        System.out.println("______________________weights _______________________________");
        for (int i = 0; i < weights.length; i++) {
            System.out.print( " " + weights[i]);
        }
        System.out.println();
        System.out.println("______________________inputs _______________________________");
        for (double d: inputs) {
            System.out.print( " " + d);
        }
        System.out.println("______________ _______________________________");
    }

}
