package ru.academits.polyanskaya.shapes;

public record Square(double sideLength) implements ShapesBehavior {

    @Override
    public double width() {
        return sideLength;
    }

    @Override
    public double height() {
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
        return "Квадрат: сторона = " + sideLength + ", ширина = " + width() + ", высота = " +
                height() + ", периметр = " + getPerimeter() + ", площадь = " + getArea();
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