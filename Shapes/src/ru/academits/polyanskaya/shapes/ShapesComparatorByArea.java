package ru.academits.polyanskaya.shapes;

public class ShapesComparatorByArea implements java.util.Comparator<ShapesBehavior> {
    @Override
    public int compare(ShapesBehavior shape1, ShapesBehavior shape2) {
        return (int) (shape1.getArea() - shape2.getArea());
    }
}