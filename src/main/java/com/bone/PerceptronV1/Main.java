package com.bone.PerceptronV1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main  extends Application {
    public List<Point> pointlist;
    int index = 0;
    Pane pane;
    int epoch = 0;
    Line brainLine;
   static double width = 1020;
    static double height = 600;
    Perceptron brain= new Perceptron(2);
    public static void main(String []args){

        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
            stage.setTitle("Perceptron training simulation ");

        pointlist = new ArrayList<>();
        Point2D [] point2DS = new Point2D[2];

        IntStream.range(0,2).forEach(i->
        {
            double x = Point.random(-1,1);
            double y = Point.random(-1,1);
            point2DS[i] = new Point2D(x,y);

        });

        Line line = new Line(point2DS);

        brainLine = new Line(point2DS);
        brainLine.setStroke(Color.PINK);
        IntStream.range(0,6000).forEach(i-> pointlist.add(new Point(line )));
       pane = new Pane();
        pane.setStyle("-fx-background-color: #eeeeee" );
       pane.getChildren().addAll(pointlist );
        pane.getChildren().add(line);
        updateBrainLine();

        Scene scene = new Scene(pane, width,height);
      AnimationTimer an =   new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                loop();
                stage.setTitle("epoch = " + epoch);
            }
        };
      pane.getChildren().add(brainLine);
      pane.setOnMouseClicked(event -> an.start());

        stage.setScene(scene);
        stage.show();





    }
    public void loop()
    {
        Point point = pointlist.get(index);
        double inputs[] = {point.position.x, point.position.y, point.bias};
        int guess = brain.train(inputs, point.targetResult);

        if (guess != point.targetResult)
            point.update("d7385e");
        else
            point.update("2c786c");
        index++;
        if (index == pointlist.size())
            epoch++;
        index  = index % pointlist.size();
        updateBrainLine();
       // System.out.println(point.targetResult + "  guess " + guess);
    }
    public void updateBrainLine()
    {
        Point2D start = new Point2D(-1,brain.guessY(-1));
        Point2D end = new Point2D(1,brain.guessY(1));
         brainLine.setLinePos(start.getX(), start.getY(),end.getX(),end.getY());
    }
}
