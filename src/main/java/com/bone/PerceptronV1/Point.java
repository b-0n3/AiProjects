package com.bone.PerceptronV1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Point extends Circle {
    public Point2D position;
    public int targetResult;
    public int bias;

    final static double EPSILON = 1e-12;


    public Point( Line line) {
        position = new Point2D(
                random(-1,1) ,
               random(-1,1)
        );
//algorithm


        this.bias = 1;
        targetResult = getTargetResult(line);
        if (targetResult == 1)
            this.setFill(Color.web("#eeeeee"));
        else
            this.setFill(Color.web("#545454"));

        this.setCenterX(position.getX());
        this.setCenterY(position.getY());
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
        this.setRadius(7);
    }

    private int getTargetResult(Line line) {

        double yLine = line.f(position.getX());

        if (yLine > position.getY())
            return 1;
        else
            return -1;

    }


    public void update(String color) {
        this.setFill(Color.web(color));
    }


    public static double map(double valueCoord1,
                             double startCoord1,
                             double endCoord1,
                             double startCoord2,
                             double endCoord2) {

        if (Math.abs(endCoord1 - startCoord1) < EPSILON) {
            throw new ArithmeticException("/ 0");
        }

        double offset = startCoord2;
        double ratio = (endCoord2 - startCoord2) / (endCoord1 - startCoord1);
        return ratio * (valueCoord1 - startCoord1) + offset;
    }

    public  static double random(double x , double y)
    {
        return Point.map(Math.random(), 0,1,x,y);
    }

}
