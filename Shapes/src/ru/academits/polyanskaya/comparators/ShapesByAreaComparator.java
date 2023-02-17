package ru.academits.polyanskaya.comparators;

import ru.academits.polyanskaya.shapes.Shape;

import java.util.Comparator;

public class ShapesByAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}