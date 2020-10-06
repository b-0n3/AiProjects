package com.bone.matrixmath;

import com.bone.PerceptronV1.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Matrix {
    private final int rows;
    private final int cols;
    private double data[][];
    final static double EPSILON = 1e-12;
    public Matrix(int rows, int cols)
    {
        this.cols = cols;
        this.rows = rows;
        data = new double[rows][cols];
    }

    public static Matrix fromArray(double []array)
    {
        Matrix matrix = new Matrix(array.length,  1);
        for (int i = 0; i < array.length; i++)
            matrix.put(i,0,array[i]);
        return matrix;
    }
    public static Matrix map(Matrix m1 ,Function<Double ,Double> function)
    {
        Matrix result = new Matrix(m1.rows,m1.cols);
        for(int i = 0; i < m1.rows; i++)
            for(int j = 0; j < m1.cols;j++)
                result.put(i,j,function.apply(m1.get(i,j)));
        return  result;
    }

    public static Matrix subtract(Matrix m1, Matrix m2)
    {
        if (m1.rows != m2.rows || m2.cols != m1.cols)
            throw new IllegalArgumentException("matrix sizes does not match");

        Matrix result  = new Matrix(m1.rows ,m2.cols);
        for (int i = 0; i < m1.rows; i++)
            for(int j = 0; j < m1.cols; j++)
                result.put(i,j,m1.get(i,j) - m2.get(i , j));
        return result;
    }

    public static Matrix transpose(Matrix m)
    {
        Matrix result = new Matrix(m.cols,m.rows);
        for (int i = 0; i < m.rows; i++)
            for(int j = 0; j < m.cols; j++)
                result.put(j,i,m.get(i,j));
        return result;
    }


    public static Matrix multiply(Matrix m1, Matrix m2)
    {
        if (m1.cols != m2.rows)
            throw new IllegalArgumentException("matrix1 cols does not equal m2 rows");

        Matrix result = new Matrix(m1.rows, m2.cols);
        for (int i = 0; i < result.rows; i++ )
            for (int j = 0; j< result.cols; j++) {
                double sum =0;
                for (int k = 0; k < m1.cols; k++) {
                    sum += m1.get(i,k) * m2.get(k,j);
                }
                result.put(i, j, sum);
            }
        return  result;

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


    public void multiply(double x) {
            for(int i = 0; i < rows; i++)
                for(int j = 0; j < cols;j++)
                    data[i][j] *= x;

    }
    public void map(Function<Double ,Double> function)
    {
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols;j++)
              this.put(i,j, function.apply(this.get(i,j)));
    }


    public void map(Consumer<Double > function)
    {
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols;j++)
                 function.accept(this.get(i,j));
    }


    public double [] toArray()
    {
        double[] output = new double[this.rows * this.cols];
        for(int i = 0; i< this.rows ;i++)
            for (int j = 0; j < this.cols; j++)
                output[i * this.rows + j] = this.get(i,j);

        return output;
    }
    public void randomize()
    {
        for (int i = 0; i< this.rows ;i++)
            for (int j = 0; j< this.cols; j++) {
                this.data[i][j] = Matrix.random(-1 , 1);

            }
    }

    public void put(int x, int y , double val){
        this.data[x][y]=val;
    }

    public double get(int x, int y) { return this.data[x][y]; }

    public void add(Object x) {
        if (x instanceof  Matrix)
            for(int i = 0; i < rows; i++)
                for(int j = 0; j < cols;j++)
                    data[i][j] += ((Matrix) x).get(i,j);
        else
            for(int i = 0; i < rows; i++)
                for(int j = 0; j < cols;j++)
                    data[i][j] += (Double) x;

    }

    public void print(String name)
    {
        System.out.printf("---------------%s--------------\n" ,name);
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rows; i++) {
             sb.append("[");
            for (int j = 0; j < cols; j++)
                sb.append(String.format("%.2f," , this.get(i,j)));
            sb.append("],");
        }
        return String.format(" rows = %d , cols = %d \n data =%s",rows,cols,sb.toString());
    }
}
