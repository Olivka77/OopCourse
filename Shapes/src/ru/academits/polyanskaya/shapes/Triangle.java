package ru.academits.polyanskaya.shapes;

public record Triangle(double x1, double y1, double x2, double y2, double x3, double y3) implements ShapesBehavior {

    @Override
    public double getWidth() {
        double maxX = Math.max(x1, x2);
        maxX = Math.max(maxX, x3);

        double minX = Math.min(x1, x2);
        minX = Math.min(minX, x3);

        return maxX - minX;
    }

    @Override
    public double getHeight() {
        double maxY = Math.max(y1, y2);
        maxY = Math.max(maxY, y3);

        double minY = Math.min(y1, y2);
        minY = Math.min(minY, y3);

        return maxY - minY;
    }

    public double getSegmentLength(double coordinate1, double coordinate2, double coordinate3, double coordinate4) {
        return Math.sqrt(Math.pow(coordinate1 - coordinate2, 2) + Math.pow(coordinate3 - coordinate4, 2));
    }

    @Override
    public double getArea() {
        double halfPerimeter = getPerimeter() / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - getSegmentLength(x2, x1, y2, y1)) *
                (halfPerimeter - getSegmentLength(x3, x1, y3, y1)) * (halfPerimeter - getSegmentLength(x3, x2, y3, y2)));
    }

    @Override
    public double getPerimeter() {
        return getSegmentLength(x2, x1, y2, y1) + getSegmentLength(x3, x1, y3, y1) + getSegmentLength(x3, x2, y3, y2);
    }

    @Override
    public String toString() {
        return "Треугольник: x1 = " + x1 + ", y1 = " + y1 + ", x2 = " + x2 + ", y2 = " + y2 +
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) obj;

        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 &&
                y2 == triangle.y2 && x3 == triangle.x3 && y3 == triangle.y3;
    }
}