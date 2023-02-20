package ru.academits.polyanskaya.vector_main;

import ru.academits.polyanskaya.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        double[] components1 = {1, 0, 3.5, 4, 5};
        double[] components2 = {3, 3, 3};
        double[] components3 = {3, 3, 3, 3};
        double[] components4 = {3, 3, 3};

        Vector vector1 = new Vector(10);
        Vector vector2 = new Vector(components1);
        Vector vector3 = new Vector(vector1);
        Vector vector4 = new Vector(vector2);
        Vector vector5 = new Vector(9, components1);
        Vector vector6 = new Vector(4, components2);

        System.out.println("Вектор 1 " + vector1);
        System.out.println("Вектор 2 " + vector2);
        System.out.println("Вектор 3 " + vector3);
        System.out.println("Вектор 4 " + vector4);
        System.out.println("Вектор 5 " + vector5);
        System.out.println("Вектор 6 " + vector6);

        System.out.println("Вектор 6 + вектор 2 = " + vector6.add(vector2));
        System.out.println("Вектор 2 после addVector " + vector2);
        System.out.println("Вектор 6 после addVector " + vector6);

        Vector vector7 = new Vector(vector6.subtract(vector4));
        System.out.println("Вектор 6 - вектор 4 = " + vector7);
        System.out.println("Вектор 6 после subtractVector " + vector6);
        System.out.println("Вектор 4 после subtractVector " + vector4);

        Vector vector8 = new Vector(components2);
        vector8.multiplyByScalar(2);
        System.out.println("Вектор 8 * скаляр = " + vector8);
        vector8.setComponent(0, 111);
        System.out.println("Вектор 8 с измененной компонентой: " + vector8);

        Vector vector9 = new Vector(vector4.reverse());
        System.out.println("Разворот вектора 4 = " + vector9);
        System.out.println("Длина вектора 4 = " + vector4.getLength());
        System.out.println("Компонента вектора 4 по индексу [4] = " + vector4.getComponent(4));

        Vector vector10 = new Vector(components2);
        Vector vector11 = new Vector(components3);
        Vector vector12 = new Vector(components4);
        System.out.println(vector10.equals(vector11));
        System.out.println(vector10.equals(vector12));

        System.out.println("Сумма векторов 10 и 11 = " + Vector.getSum(vector10, vector11));
        System.out.println("Вектор 10 после getSum " + vector10);
        System.out.println("Вектор 11 после getSum " + vector11);

        System.out.println("Разность векторов = " + Vector.getDifference(vector10, vector11));

        System.out.println("Скалярное произведение вектора 10 и вектора 11 = " + Vector.getScalarProduct(vector10, vector11));
    }
}