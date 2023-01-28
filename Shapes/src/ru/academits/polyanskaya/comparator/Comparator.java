package ru.academits.polyanskaya.comparator;

import ru.academits.polyanskaya.shape_interface.ShapeInterface;

import java.util.Arrays;

public class Comparator implements java.util.Comparator<ShapeInterface> {
    @Override
    public int compare(ShapeInterface shape1, ShapeInterface shape2) {
        return (int) (shape1.getArea() - shape2.getArea());
    }

    public static ShapeInterface getShapeMaxArea(ShapeInterface[] shapesArray) {
        Arrays.sort(shapesArray, new Comparator());

        return shapesArray[shapesArray.length - 1];
    }

    public static ShapeInterface getShapePreviousMaxPerimeter(ShapeInterface[] shapesArray) {
        Arrays.sort(shapesArray, new Comparator());

        return shapesArray[shapesArray.length - 2];
    }
}