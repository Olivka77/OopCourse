package ru.academits.polyanskaya.shapes;

public class Circle implements ShapesBehavior {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return radius + radius;
    }

    @Override
    public double getHeight() {
        return radius + radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return Math.PI * radius;
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "окружность: радиус = " + radius + ", диаметр = " + getWidth() +
                ", длина = " + getPerimeter() + ", площадь = " + getArea();
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);
        return hash;
    }

    @Override
    public boolean equals(Object circle) {
        if (circle == this) {
            return true;
        }

        if (circle == null || circle.getClass() != getClass()) {
            return false;
        }

        return radius == (((Circle) circle).radius);
    }
}