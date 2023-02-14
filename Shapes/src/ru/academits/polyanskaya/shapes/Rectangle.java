package ru.academits.polyanskaya.shapes;

public record Rectangle(double width, double height) implements Shape {
    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        final int doubleSidesCount = 2;

        return doubleSidesCount * (width + height);
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