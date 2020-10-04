package com.bone.PerceptronV1;

import javafx.scene.paint.Color;




public class Line extends javafx.scene.shape.Line {
    public double m, b;

    public Line(Point2D [] points, double width)
    {


            this.m = (points[1].y - points[0].y) / (points[1].x - points[0].x);

            this.b = points[0].y - m * points[0].x;
            double x , y  , x1,y1;
            x = 0;
             x1 = width;
             y =  m  * x + b;
             y1 = m * x1 + b;

            this.setStroke(Color.RED);
            setLinePos(x,y,x1,y1);
    }
    public void setLinePos(double x, double y , double x1, double y1)
    {

        this.setStartX(x);
        this.setStartY(y);
        this.setEndX(x1);
        this.setEndY(y1);
    }
}
