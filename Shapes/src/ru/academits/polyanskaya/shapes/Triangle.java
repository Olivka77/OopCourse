package ru.academits.polyanskaya.shapes;

public class Triangle implements ShapesBehavior {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
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

    @Override
    public double getArea() {
        double halfPerimeter = getPerimeter() / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))) *
                (halfPerimeter - Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2))) *
                (halfPerimeter - Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2))));
    }

    @Override
    public double getPerimeter() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) +
                Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2)) +
                Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "треугольник: x1 = " + x1 + ", y1 = " + y1 + ", " + "x2 = " + x2 + ", y2 = " + y2 +
                ", x3 = " + x3 + ", y3 = " + y3 + ", ширина = " + getWidth() + ", высота = " + getHeight() +
                ", периметр = " + getPerimeter() + ", площадь = " + getArea();
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }

    @Override
    public boolean equals(Object triangle) {
        if (triangle == this) {
            return true;
        }

        if (triangle == null || triangle.getClass() != getClass()) {
            return false;
        }

        return x1 == (((Triangle) triangle).x1) && y1 == (((Triangle) triangle).y1) && x2 == (((Triangle) triangle).x2) &&
                y2 == (((Triangle) triangle).y2) && x3 == (((Triangle) triangle).x3) && y3 == (((Triangle) triangle).y3);
    }
}