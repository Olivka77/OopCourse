package ru.academits.polyanskaya.matrix;

import ru.academits.polyanskaya.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private final Vector[] matrixRows;

    public Matrix(int n, int m) {
        matrixRows = new Vector[n];

        for (int i = 0; i < n; i++) {
            matrixRows[i] = new Vector(m);
        }
    }

    public Matrix(Matrix copy) {
        this(copy.matrixRows);
    }

    public Matrix(double[][] array) {
        matrixRows = new Vector[array.length];

        int maxRowLength = 0;

        for (int i = 0; i < matrixRows.length; i++) {
            if (array[i].length > maxRowLength) {
                maxRowLength = array[i].length;
            }
        }

        for (int i = 0; i < array.length; i++) {
            matrixRows[i] = new Vector(Arrays.copyOf(array[i], maxRowLength));
        }
    }

    public Matrix(Vector[] vectors) {
        matrixRows = new Vector[vectors.length];

        int maxVectorSize = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > maxVectorSize) {
                maxVectorSize = vector.getSize();
            }
        }

        for (int i = 0; i < vectors.length; i++) {
            double[] components = new double[maxVectorSize];

            for (int j = 0; j < vectors[i].getSize(); j++) {
                components[j] = vectors[i].getComponent(j);
            }

            matrixRows[i] = new Vector(maxVectorSize, components);
        }
    }

    public int[] getSize() { // Исключение на вызов от null?
        int rowsCount = matrixRows.length;
        int columnsCount = matrixRows[0].getSize();

        for (int i = 0; i < rowsCount; i++) {
            if (columnsCount != matrixRows[i].getSize()) { // Нужно ли? Могут ли у созданного объекта быть разные строки?
                throw new IllegalArgumentException("Разная длина строк матрицы: строка 1 длиной " + columnsCount +
                        ", строка " + (i + 1) + " длиной " + matrixRows[i].getSize());
            }
        }

        return new int[]{rowsCount, columnsCount};
    }

    public Vector getVector(int index) {
        if (index > getSize()[0] - 1) {
            throw new IllegalArgumentException("Индекс " + index + " превышает максимально допустимый индекс вектора " +
                    (this.getSize()[0] - 1));
        }

        if (index < 0) {
            throw new IllegalArgumentException("Передан индекс " + index + ", индекс не может быть отрицательным");
        }

        return new Vector(matrixRows[index]);
    }

    /*public void setVector(int index) { // Добавить исключение про вектор отличающейся длины

    }*/

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Vector vector : matrixRows) {
            stringBuilder.append(vector.toString()).append(", ");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1).deleteCharAt(stringBuilder.length() - 1).append("}");

        return stringBuilder.toString();
    }
}