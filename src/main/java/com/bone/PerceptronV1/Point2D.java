package com.bone.PerceptronV1;

public class Point2D implements Comparable<Point2D>{
    public final double x, y;


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
}
