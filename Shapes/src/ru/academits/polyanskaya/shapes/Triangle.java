package ru.academits.polyanskaya.shapes;

public record Triangle(double x1, double y1, double x2, double y2, double x3, double y3) implements Shape {
    @Override
    public double getWidth() {
        double maxX = Math.max(Math.max(x1, x2), x3);
        double minX = Math.min(Math.min(x1, x2), x3);

        return maxX - minX;
    }

    @Override
    public double getHeight() {
        double maxY = Math.max(Math.max(y1, y2), y3);
        double minY = Math.min(Math.min(y1, y2), y3);

        return maxY - minY;
    }

    private static double getSegmentLength(double segmentPointX2, double segmentPointX1, double segmentPointY2, double segmentPointY1) {
        return Math.sqrt(Math.pow(segmentPointX2 - segmentPointX1, 2) + Math.pow(segmentPointY2 - segmentPointY1, 2));
    }

    @Override
    public double getArea() {
        double sideLength1 = getSegmentLength(x2, x1, y2, y1);
        double sideLength2 = getSegmentLength(x3, x1, y3, y1);
        double sideLength3 = getSegmentLength(x3, x2, y3, y2);
        double halfPerimeter = (sideLength1 + sideLength2 + sideLength3) / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - sideLength1) * (halfPerimeter - sideLength2) * (halfPerimeter - sideLength3));
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