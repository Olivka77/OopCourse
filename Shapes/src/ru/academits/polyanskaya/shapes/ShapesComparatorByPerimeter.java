package ru.academits.polyanskaya.shapes;

public class ShapesComparatorByPerimeter implements java.util.Comparator<ShapesBehavior> {
    @Override
    public int compare(ShapesBehavior shape1, ShapesBehavior shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}