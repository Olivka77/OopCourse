package ru.academits.polyanskaya.shapes;

public class Rectangle implements ShapesBehavior {
    private final double width;
    private final double height;

    public Rectangle(double sideLength1, double height) {
        this.width = sideLength1;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return Math.max(width, height);
    }

    @Override
    public double getHeight() {
        return Math.min(width, height);
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return width + width + height + height;
    }

    @Override
    public String toString() {
        return "Прямоугольник: сторона 1 = " + width + ", сторона 2 = " + height + ", ширина = " + getWidth() + ", высота = " +
                getHeight() + ", периметр = " + getPerimeter() + ", площадь = " + getArea();
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) obj;

        return width == rectangle.width && height == rectangle.height;
    }
}