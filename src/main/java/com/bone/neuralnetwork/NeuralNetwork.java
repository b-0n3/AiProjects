package com.bone.neuralnetwork;

import com.bone.matrixmath.Matrix;
import org.omg.CORBA.MARSHAL;

import javax.jnlp.SingleInstanceListener;
import java.rmi.MarshalException;
import java.util.function.Function;

public class NeuralNetwork {

    private  final int numberOfInputs;
    private  final int numberOfHiddenNodes;
    private  final int numberOfOutputs;
    private final Matrix weightsIH;
    private final Matrix weightsHO;
    private  final  Matrix biasH;
    private final Matrix biasO;
    private Function<Double , Double> activationFunction;
    private  Function<Double ,Double> dActivationFunction;
    private  double learningRate = 0.01;


    public NeuralNetwork(int numberOfInputs, int numberOfHiddenNodes, int numberOfOutputs) {
        this.numberOfInputs = numberOfInputs;
        this.numberOfHiddenNodes = numberOfHiddenNodes;
        this.numberOfOutputs = numberOfOutputs;
        this.weightsIH = new Matrix(numberOfHiddenNodes , numberOfInputs);
        this.weightsIH.randomize();
        this.weightsHO = new Matrix(numberOfOutputs, numberOfHiddenNodes);
        this.weightsHO.randomize();
        this.biasH = new Matrix(numberOfHiddenNodes, 1);
        this.biasH.randomize();
        this.biasO = new Matrix(numberOfOutputs, 1);
        this.biasO.randomize();
        this.dActivationFunction = y-> y * (1 / y);

        activationFunction = input-> 1 / (1 - Math.exp(-input));

    }




    public double[] feedForward(double [] inputArray)
    {
        weightsHO.print("weightsHO");
        weightsIH.print("weightsIH");
        Matrix inputs = Matrix.fromArray(inputArray);
        Matrix hidden = Matrix.multiply(weightsIH, inputs);
        hidden.add(biasH);
        hidden.map(activationFunction);

        Matrix output = Matrix.multiply(weightsHO, hidden);
        output.add(biasO);
        output.map(activationFunction);
        return output.toArray();
    }
    public void train(double [] inputArray , double [] results)
    {
        weightsHO.print("weightsHO");
        weightsIH.print("weightsIH");
        Matrix inputs = Matrix.fromArray(inputArray);
        inputs.print("inputs");
        Matrix hidden = Matrix.multiply(weightsIH, inputs);
        hidden.add(biasH);
        hidden.map(activationFunction);
        hidden.print("hidden");

        Matrix output = Matrix.multiply(weightsHO, hidden);
        output.add(biasO);
        output.map(activationFunction);
       output.print("output");
        Matrix targetResults = Matrix.fromArray(results);
            targetResults.print("targetResults");
        Matrix errorHO = Matrix.subtract(targetResults,output);
        errorHO.print("errorHO");
        Matrix gradientsHO = Matrix.map(output,dActivationFunction);
        gradientsHO =  Matrix.multiply(gradientsHO, errorHO);
        gradientsHO.multiply(learningRate);
        gradientsHO.print("gradientsHO");
        this.biasO.add(gradientsHO);
        this.biasO.print("biasO");
        Matrix hiddenT = Matrix.transpose(hidden);
        hiddenT.print("hiddenT");
        Matrix weightsHoDeltas =  Matrix.multiply(gradientsHO, hiddenT);
            weightsHoDeltas.print("weightsHoDeltas");
        this.weightsHO.add(weightsHoDeltas);
        weightsHO.print("weightsHO");
        Matrix weightsHoT = Matrix.transpose(this.weightsHO);
        weightsHoT.print("weightsHOT");
        Matrix hiddenErrors =   Matrix.multiply(weightsHoT, errorHO);
            hiddenErrors.print("hiddenErrors");
        Matrix gradientsIH = Matrix.map(hiddenT, dActivationFunction);
        gradientsIH.print("gradientIH");
    //    Matrix hiddenErrorsT = Matrix.transpose(hiddenErrors);
         gradientsIH =  Matrix.multiply( hiddenErrors,gradientsIH);
        gradientsIH.multiply(learningRate);
        gradientsIH.print("gradientIH after doing some shit");
        this.biasH.add(gradientsIH);


        Matrix inputsT = Matrix.transpose(inputs);
        System.out.println("inputsT" + inputsT);
        Matrix weightsIHDeltas =  Matrix.multiply(gradientsIH, inputsT);
        this.weightsIH.add(weightsIHDeltas);


    }
}
