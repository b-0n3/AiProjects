package com.bone.PerceptronV1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Point extends Circle  {
    public Point2D position;
    public int targetResult;
    public Point(double width , double height , Line line)
    {
        position = new Point2D(
                Math.random() * width,
                Math.random() * height
        );

        targetResult = getTargetResult(line);
        if (targetResult == 1)
            this.setFill(Color.web("#eeeeee"));
        else
            this.setFill(Color.web("#545454"));

        this.setCenterX(position.x);
        this.setCenterY(position.y);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
        this.setRadius(7);
    }

    private int getTargetResult(Line line) {

       double a = line.m ;
       double b =1;
       double c = line.b;
            if (isBelow(a,b,c , position.x, position.y))
                return -1;
            else
                return 1;

    }
    boolean isBelow( double a, double b, double c, double x0, double y0 ){
        double d = -c/b;
        if( d == 0 ){
            return -a / b * x0 > y0;
        } else {
            double h0 = a * x0 + b * y0 + c;
            return d > 0 && (d > 0 == h0 > 0);
        }
    }

    public void update(String color)
    {
        this.setFill(Color.web(color));
    }


}
