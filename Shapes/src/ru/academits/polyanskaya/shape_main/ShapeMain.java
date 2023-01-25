package ru.academits.polyanskaya.shape_main;

import ru.academits.polyanskaya.triangle.Triangle;

public class ShapeMain {
    public static void main(String[] args) {
        Triangle triangle1 = new Triangle(-5, 3, -2, 1, 2, 0);

        System.out.println(triangle1.getPerimeter());
        System.out.println(triangle1.getArea());
        System.out.println(triangle1.getHeight());
        System.out.println(triangle1.getWidth());
    }
}