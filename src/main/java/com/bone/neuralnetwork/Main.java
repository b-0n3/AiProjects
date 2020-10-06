package com.bone.neuralnetwork;

import com.bone.matrixmath.Matrix;

public class Main {
    private static double [][][] trainingData = {
            {{1,0},{1}},
            {{0,1},{1}},
            {{0,0},{0}},
            {{1,1},{0}}
    };


    public static void main(String []args)
    {
        NeuralNetwork nn = new NeuralNetwork(2,4,1);

        for (int i = 0; i < 1000 ; i++)
        {
            int index = (int)(Math.random() * trainingData.length);
            nn.train(trainingData[index][0],trainingData[index][1]);

        }
        System.out.println(nn.feedForward(trainingData[0][0])[0]);
        System.out.println(nn.feedForward(trainingData[1][0])[0]);
        System.out.println(nn.feedForward(trainingData[2][0])[0]);
        System.out.println(nn.feedForward(trainingData[3][0])[0]);

    }
}
