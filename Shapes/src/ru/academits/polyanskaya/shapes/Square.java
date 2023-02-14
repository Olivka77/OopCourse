package ru.academits.polyanskaya.shapes;

public record Square(double sideLength) implements Shape {
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
        final int sidesCount = 4;

        return sidesCount * sideLength;
    }

    @Override
    public String toString() {
        return "Квадрат: сторона = " + sideLength + ", ширина = " + getWidth() + ", высота = " +
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        return sideLength == (((Square) obj).sideLength);
    }
}