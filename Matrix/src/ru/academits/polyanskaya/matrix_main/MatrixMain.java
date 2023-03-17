package ru.academits.polyanskaya.matrix_main;

import ru.academits.polyanskaya.matrix.Matrix;
import ru.academits.polyanskaya.vector.Vector;

public class MatrixMain {
    public static void main(String[] args) {
        double[][] arrays1 = {
                {0, 1, 2, 3, 1},
                {2, 1, 3, 1, 1},
                {4, 3, 7, 1, 1},
                {9, 7, 4}
        };

        System.out.println("Количество строк двумерного массива arrays1: " + arrays1.length);
        System.out.println("Длина строки по индексу 0 двумерного массива arrays1: " + arrays1[0].length);
        System.out.println("Длина строки по индексу 3 двумерного массива arrays1: " + arrays1[3].length);

        Matrix matrix1 = new Matrix(5, 4);
        Matrix matrix2 = new Matrix(matrix1);
        Matrix matrix3 = new Matrix(arrays1);
        matrix1 = matrix3;

        Vector[] vectors = {
                new Vector(arrays1[3]),
                new Vector(arrays1[1])
        };

        Vector vector1 = new Vector(new double[]{2, 4, 7, 10, 11});

        Matrix matrix4 = new Matrix(vectors);
        Matrix matrix5 = new Matrix(arrays1);

        System.out.println("Матрица matrix1: " + matrix1);
        System.out.println("Матрица matrix2: " + matrix2);
        System.out.println("Матрица matrix3: " + matrix3);
        System.out.println("Матрица matrix4: " + matrix4);
        System.out.println("Размер матрицы matrix4: " + matrix4.getRowsCount() + " х " + matrix4.getColumnsCount());
        System.out.println("Получение строки по индексу 1 матрицы matrix4: " + matrix4.getRow(1));

        matrix4.setRow(1, vector1);

        System.out.println("Матрица matrix4 после замены строки по индексу 1: " + matrix4);
        System.out.println("Матрица matrix5: " + matrix5);
        System.out.println("Получение столбца по индексу 0 матрицы matrix5: " + matrix5.getColumn(0));

        matrix5.transpose();
        System.out.println("Матрица matrix5 после транспонирования: " + matrix5);

        matrix5.multiplyByScalar(2);
        System.out.println("Матрица matrix5 после умножение на скаляр 2: " + matrix5);

        double[][] arrays2 = {
                {3, 7, 1, 5},
                {5, 6, 0, 1},
                {4, 9, 1, 2},
                {4, 1, 1, 0}
        };

        Matrix matrix6 = new Matrix(arrays2);
        System.out.println("Определитель матрицы matrix6: " + matrix6.getDeterminant());

        double[][] arrays3 = {
                {2, 4, 0},
                {-2, 1, 3},
                {-1, 0, 1},
                {1, 1, 1}
        };

        double[] array1 = {1, 2, -1};

        Matrix matrix7 = new Matrix(arrays3);

        Vector vector2 = new Vector(array1);

        System.out.println("Произведение матрицы matrix7 на вектор vector2: " + matrix7.multiplyByVector(vector2));

        double[][] arrays4 = {
                {2, 4, 0},
                {-2, 1, 3},
                {-1, 0, 1}
        };

        Matrix matrix8 = new Matrix(arrays4);
        Matrix matrix9 = new Matrix(arrays4);

        matrix8.add(matrix9);
        System.out.println("Матрица matrix8 после прибавления матрицы matrix9: " + matrix8);

        matrix8.subtract(matrix9);
        System.out.println("Матрица matrix8 после вычитания матрицы matrix9: " + matrix8);

        System.out.println("Сумма матриц matrix8 и matrix8: " + Matrix.getSum(matrix8, matrix8));
        System.out.println("Разность матриц matrix8 и matrix9: " + Matrix.getDifference(matrix8, matrix9));

        double[][] arrays5 = {
                {2, 1},
                {-3, 0},
                {4, -1}
        };

        double[][] arrays6 = {
                {5, -1, 6},
                {-3, 0, 7}
        };

        Matrix matrix10 = new Matrix(arrays5);
        Matrix matrix11 = new Matrix(arrays6);

        System.out.println("Произведение матриц matrix10 и matrix11: " + Matrix.getProduct(matrix10, matrix11));
    }
}