package ru.academits.polyanskaya.shapes;

public record Circle(double radius) implements Shape {
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
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Окружность: радиус = " + radius + ", диаметр = " + getWidth() +
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        return radius == (((Circle) obj).radius);
    }
}