package com.bone.PerceptronV1;

import javafx.scene.paint.Color;




public class Line extends javafx.scene.shape.Line {
    public double m, b;

    public Line(Point2D [] points)
    {


            this.m = (points[1].getY() - points[0].getY()) / (points[1].getX() - points[0].getX());

            this.b = points[0].getY() - m * points[0].getX();
            double x , y  , x1,y1;
            x = points[0].mapX(-1);
             x1 = points[0].mapX(1);
             y = f(x);
             y1 =f(x1);

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

    public double f( double x)
    {
        return m * x + b;
    }
}
