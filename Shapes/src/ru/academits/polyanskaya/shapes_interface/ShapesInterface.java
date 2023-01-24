package ru.academits.polyanskaya.shapes_interface;

public interface ShapesInterface {
    double ZERO = 0.0; // если не пригодится - удалить; public static final удалено - можно не указывать

    double getWidth(); // public удалено - можно не указывать
    double getHeight();
    double getArea();
    double getPerimeter();
}