package ru.academits.polyanskaya.matrix_main;

import ru.academits.polyanskaya.matrix.Matrix;
import ru.academits.polyanskaya.vector.Vector;

import java.util.Arrays;

public class MatrixMain {
    public static void main(String[] args) {
        double[][] array1 = {
                {0, 1, 2, 3, 1},
                {2, 1, 3, 1, 1},
                {4, 3, 7, 1, 1},
                {9, 7, 4}
        };

        System.out.println(array1.length);
        System.out.println(array1[0].length);
        System.out.println(array1[3].length);

        Matrix matrix1 = new Matrix(5, 4);
        Matrix matrix2 = new Matrix(matrix1);
        Matrix matrix3 = new Matrix(array1);
        matrix1 = matrix3;

        Vector[] vector1 = {
                new Vector(array1[3]),
                new Vector(array1[1])
        };

        Matrix matrix4 = new Matrix(vector1);

        System.out.println(matrix1);
        System.out.println(matrix2);
        System.out.println(matrix3);
        System.out.println(matrix2);
        System.out.println(matrix4);
        System.out.println(Arrays.toString(matrix4.getSize())); // Дописать метод для печати размера аналогично вектору?
    }
}