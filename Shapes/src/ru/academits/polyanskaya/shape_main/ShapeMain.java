package ru.academits.polyanskaya.shape_main;

import ru.academits.polyanskaya.circle.Circle;
import ru.academits.polyanskaya.comparator.Comparator;
import ru.academits.polyanskaya.rectangle.Rectangle;
import ru.academits.polyanskaya.shape_interface.ShapeInterface;
import ru.academits.polyanskaya.square.Square;
import ru.academits.polyanskaya.triangle.Triangle;

import java.util.Arrays;

public class ShapeMain {

    public static void main(String[] args) {
        ShapeInterface[] shapesArray = {new Circle(20), new Circle(20),
                new Square(10.2), new Square(15.2),
                new Rectangle(10, 20), new Rectangle(13, 14.5),
                new Triangle(-1, -1, -3, -2, -2, -4),
                new Triangle(1, 2, 3.5, 5, 3.5, 2)};

        System.out.println("Фигура с максимальной площадью - " + Comparator.getShapeMaxArea(shapesArray));
        System.out.println("Фигура со вторым по величине периметром - " + Comparator.getShapePreviousMaxPerimeter(shapesArray));
        System.out.println("Все фигуры: " + System.lineSeparator() + Arrays.toString(shapesArray));
        System.out.println("Проверка переопределения equals: " + shapesArray[6].equals(shapesArray[7]));
    }
}