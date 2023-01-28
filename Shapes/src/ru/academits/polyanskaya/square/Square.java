package ru.academits.polyanskaya.square;

import ru.academits.polyanskaya.shape_interface.ShapeInterface;

public class Square implements ShapeInterface {
    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength + sideLength + sideLength + sideLength;
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "квадрат: сторона = " + sideLength + ", ширина = " + getWidth() + ", высота = " +
                getHeight() + ", периметр = " + getPerimeter() + ", площадь = " + getArea();
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);
        return hash;
    }

    @Override
    public boolean equals(Object square) {
        if (square == this) {
            return true;
        }

        if (square == null || square.getClass() != getClass()) {
            return false;
        }

        return sideLength == (((Square) square).sideLength);
    }
}