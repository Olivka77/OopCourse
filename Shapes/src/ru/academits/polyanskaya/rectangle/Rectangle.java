package ru.academits.polyanskaya.rectangle;

import ru.academits.polyanskaya.shape_interface.ShapeInterface;

public class Rectangle implements ShapeInterface {
    private final double sideLength1;
    private final double sideLength2;

    public Rectangle(double sideLength1, double sideLength2) {
        this.sideLength1 = sideLength1;
        this.sideLength2 = sideLength2;
    }

    @Override
    public double getWidth() {
        return Math.max(sideLength1, sideLength2);
    }

    @Override
    public double getHeight() {
        return Math.min(sideLength1, sideLength2);
    }

    @Override
    public double getArea() {
        return sideLength1 * sideLength2;
    }

    @Override
    public double getPerimeter() {
        return sideLength1 + sideLength1 + sideLength2 + sideLength2;
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "прямоугольник: сторона 1 = " + sideLength1 + ", сторона 2 = " + sideLength2 + ", ширина = " + getWidth() + ", высота = " +
                getHeight() + ", периметр = " + getPerimeter() + ", площадь = " + getArea();
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength1);
        hash = prime * hash + Double.hashCode(sideLength2);
        return hash;
    }

    @Override
    public boolean equals(Object rectangle) {
        if (rectangle == this) {
            return true;
        }

        if (rectangle == null || rectangle.getClass() != getClass()) {
            return false;
        }

        return sideLength1 == (((Rectangle) rectangle).sideLength1) && sideLength2 == (((Rectangle) rectangle).sideLength2);
    }
}