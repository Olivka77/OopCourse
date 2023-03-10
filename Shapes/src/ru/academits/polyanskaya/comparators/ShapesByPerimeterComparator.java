package ru.academits.polyanskaya.comparators;

import ru.academits.polyanskaya.shapes.Shape;

import java.util.Comparator;

public class ShapesByPerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}