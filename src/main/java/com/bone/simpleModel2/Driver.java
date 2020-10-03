package com.bone.simpleModel2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.print.attribute.standard.NumberUp;
import java.util.stream.IntStream;

public class Driver extends Application {
   static double m ,b;
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
        m = -weights[1]/weights[0];
        b = Perceptron.THRESHOLD /weights[0];
        System.out.printf("\n y = %.2fx + %.2f\n", m,b);
       launch(args);
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

    public void start(Stage stage) throws Exception {
        stage.setTitle("Neural Network example 2");
        XYChart.Series<Number,Number> series1 = new XYChart.Series<Number, Number>();
        series1.setName("zero");
        XYChart.Series<Number,Number> series2 = new XYChart.Series<Number, Number>();
        series2.setName("one");
        IntStream.range(0,Perceptron.andData.length).forEach(i->{
            int x = Perceptron.andData[i][0][0], y=  Perceptron.andData[i][0][1];
            if(Perceptron.andData[i][1][0] == 0)series1.getData().add(new XYChart.Data<>(x,y));
            else series2.getData().add(new XYChart.Data<>(x,y));
        });
        double x1 = 0,y1 =b,x2  = -(b/m) , y2 = 0;
        String title = String.format(" y = %.2f  x = %.2f  | (0,%.2f)  |  (%.2f , 0)", m,b, b, -(b/m));
        NumberAxis xAxis  = new NumberAxis( 0,4,1);
        NumberAxis yAxis  = new NumberAxis( 0,4,1);
        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setTitle(title);
        scatterChart.getData().addAll(series1,series2);
        XYChart.Series<Number,Number> series3 = new XYChart.Series<>();
        series3.getData().addAll(
                new XYChart.Data<>(x1,y1),
                new XYChart.Data<>(x2,y2));
        LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.getData().add(series3);
        //lineChart.setStyle("-fx-background-color: #111111");
        lineChart.setOpacity(0.1);
        Pane pane = new Pane();
        pane.getChildren().addAll(scatterChart,lineChart);
        stage.setScene(new Scene(pane , 500 ,400));
        stage.show();
    }

}
