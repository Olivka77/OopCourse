package ru.academits.polyanskaya.shapes_main;

import ru.academits.polyanskaya.shapes.*;

import java.util.Arrays;

public class ShapesMain {
    public static void main(String[] args) {
        ShapesBehavior[] shapes = {
                new Circle(20),
                new Circle(20),
                new Square(10.2),
                new Square(15.2),
                new Rectangle(10, 20),
                new Rectangle(13, 14.5),
                new Triangle(-1, -1, -3, -2, -2, -4),
                new Triangle(1, 2, 3.5, 5, 3.5, 2)
        };

        System.out.println("Фигура с максимальной площадью - " + getShapeWithMaxArea(shapes));
        System.out.println("Фигура со вторым по величине периметром - " + getShapeWithPreviousMaxPerimeter(shapes));
        System.out.println("Все фигуры: " + System.lineSeparator() + Arrays.toString(shapes));
        System.out.println("Проверка переопределения equals: " + shapes[6].equals(shapes[7]));
    }

    public static ShapesBehavior getShapeWithMaxArea(ShapesBehavior[] shapes) {
        Arrays.sort(shapes, new ShapesComparatorByArea());

        return shapes[shapes.length - 1];
    }

    public static ShapesBehavior getShapeWithPreviousMaxPerimeter(ShapesBehavior[] shapes) {
        Arrays.sort(shapes, new ShapesComparatorByPerimeter());

        return shapes[shapes.length - 2];
    }
}