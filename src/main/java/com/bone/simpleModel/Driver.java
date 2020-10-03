package com.bone.simpleModel;

public class Driver {

    public static void main(String[] args) {
        int[][][] data = Perceptron.andData;
        double[] weights = Perceptron.INITIAL_WEIGHTS;
        Driver driver = new Driver();
        Perceptron perceptron = new Perceptron();
        boolean errorFlag = true;
        int epochNumber = 0;
        double[] adjustedWeights = null;
        int error =0;
        while (errorFlag) {
            driver.printHeading(epochNumber++);
            errorFlag = false;
            error =0;
            for(int x = 0; x < data.length; x++ )
            {

                    double weightedSum = perceptron.calculateWeightedSum(data[x][0],weights);
                    int result   = perceptron.applyActivationFunction(weightedSum);
                    error =  data[x][1][0] - result;
                    if (error != 0) errorFlag = true;
                    adjustedWeights = perceptron.adjustWeights(data[x][0],weights,error);
                    driver.printVector(data[x], weights, result, error,weightedSum,adjustedWeights);
                    weights = adjustedWeights;
            }
        }
    }
    public void printHeading(int epochNumber){
        System.out.println("\n==============================================Epoch = " + epochNumber +" =========================================");
        System.out.println("   W1   |   w2    |    X1    |   X2     |  Target Result  |   Result   |  error |  Weighted Sum   | adjusted W1   | adjusted W2");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
    public void printVector(int[][] data, double[] weights, int result,double error,double weightedSum, double[] adjustedWeights){
        System.out.printf("  %.2f  |  %.2f    |    %d    |    %d    |      %d          |      %d   |  %.2f  |      %.2f      |      %.2f       |   %.2f \n ",
                weights[0],
                weights[1],
                data[0][0],
                data[0][1],
                data[1][0],
                result,
                error,
                weightedSum,
                adjustedWeights[0],
                adjustedWeights[1]
                );
    }
}
