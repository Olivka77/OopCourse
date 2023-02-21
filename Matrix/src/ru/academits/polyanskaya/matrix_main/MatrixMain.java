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

        Vector[] vectors = {
                new Vector(array1[3]),
                new Vector(array1[1])
        };

        Vector vector1 = new Vector(new double[]{2, 4, 7, 10, 11});

        Matrix matrix4 = new Matrix(vectors);
        Matrix matrix5 = new Matrix(array1);

        System.out.println(matrix1);
        System.out.println(matrix2);
        System.out.println(matrix3);
        System.out.println(matrix2);
        System.out.println(matrix4);
        System.out.println(Arrays.toString(matrix4.getSize())); // Дописать метод для печати размера аналогично вектору?
        System.out.println(matrix4.getVectorRow(1));

        matrix4.setVectorRow(1, vector1);

        System.out.println(matrix4);
        System.out.println(matrix5);
        System.out.println(matrix5.getVectorColumn(0));

        matrix5.getTransposition();
        System.out.println(matrix5);

        matrix5.multiplyByScalar(2);
        System.out.println(matrix5);

        double[][] array2 = {
                {3, 7, 1, 5},
                {5, 6, 0, 1},
                {4, 9, 1, 2},
                {4, 1, 1, 0}
        };

        double[][] array3 = {
                {2, 4, 0},
                {-2, 1, 3},
                {-1, 0, 1}
        };

        double[] array4 = {1, 2, -1};

        double[][] array5 = {
                {3},
                {2},
                {0},
                {-1}
        };

        double[] array6 = {-1, 1, 0, 2};

        Matrix matrix7 = new Matrix(array3);
        Vector vector2 = new Vector(array4);
        Matrix matrix8 = new Matrix(array5);
        Vector vector3 = new Vector(array4);

        matrix7.multiplyByVector(vector2, true);
        System.out.println(matrix7);

        Matrix matrix6 = new Matrix(array2);
        System.out.println(matrix6.getDeterminant());
    }
}