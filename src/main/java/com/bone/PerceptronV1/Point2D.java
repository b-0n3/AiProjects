package com.bone.PerceptronV1;

import java.security.cert.PolicyNode;

public class Point2D implements Comparable<Point2D>{
    public  double x, y;


    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point2D lhs) {
        if ((x * x + y * y < lhs.x * lhs.x + lhs.y * lhs.y))
            return -1;
        else
            return  1;

    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public double getX() {
        return mapX(x);
    }

    public double getY() {
        return mapY(y);
    }

    public double mapX(double x)
    {
        return Point.map(x, -1,1,0 , Main.width);
    }
    public double mapY(double y)
    {
        return Point.map(y, -1,1, Main.height , 0);
    }
}
