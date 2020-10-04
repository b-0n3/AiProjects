package com.bone.PerceptronV1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BackgroundFill;
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
    Perceptron brain= new Perceptron(2);
    public static void main(String []args){

        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
            stage.setTitle("Perceptron training simulation");
            double width = 500;
            double height = 300;
        pointlist = new ArrayList<>();
        Point2D [] point2DS = new Point2D[2];
        IntStream.range(0,2).forEach(i->point2DS[i] =new Point2D(Math.random() * width , Math.random()*height));
        Line line = new Line(point2DS, width);
        IntStream.range(0,100).forEach(i-> pointlist.add(new Point(width,height, line )));
       pane = new Pane();
        pane.setStyle("-fx-background-color: #eeeeee" );
       pane.getChildren().addAll(pointlist );
        pane.getChildren().add(line);
        pane.setOnMouseClicked(event -> updateColor());

        Scene scene = new Scene(pane, width,height);

        stage.setScene(scene);
        stage.show();





    }
    public void updateColor()
    {
        Point point = pointlist.get(index);
        double inputs[] = {point.position.x, point.position.y};
        int guess = brain.train(inputs,point.targetResult);
        if (guess != point.targetResult)
            point.update("d7385e");
        else
            point.update("2c786c");
        index++;
        index  = index % pointlist.size();
        System.out.println(point.targetResult);
    }
}
