package ru.academits.polyanskaya.shapes_triangle;

import ru.academits.polyanskaya.shapes_interface.ShapesInterface;

public class Triangle implements ShapesInterface {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        double[] arrayX = {x1, x2, x3};

        double minX = x1, maxX = x1;


        for (double x : arrayX) {
            if (x > maxX) {
                maxX = x;
            }

            if (x < minX) {
                minX = x;
            }
        }

        return maxX - minX;
    }

    @Override
    public double getHeight() {
        double[] arrayY = {y1, y2, y1};

        double minY = y1, maxY = y2;

        for (double y : arrayY) {
            if (y > maxY) {
                maxY = y;
            }

            if (y < minY) {
                minY = y;
            }
        }

        return maxY - minY;
    }

    double sideLength1 = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    double sideLength2 = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
    double sideLength3 = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));

    @Override
    public double getArea() {
        double halfPerimeter = getPerimeter() / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - sideLength1) *
                (halfPerimeter - sideLength2) * (halfPerimeter - sideLength3));
    }

    @Override
    public double getPerimeter() {
        return sideLength1 + sideLength2 + sideLength3;
    }
}