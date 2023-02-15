package ru.academits.polyanskaya.matrix;

import ru.academits.polyanskaya.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private final Vector[] matrixRows;

    public Matrix(int n, int m) {
        int maxSize = Math.max(n, m);
        matrixRows = new Vector[maxSize];

        for (int i = 0; i < maxSize; i++) {
            matrixRows[i] = new Vector(maxSize);
        }
    }

    public Matrix(Matrix copy) {
        this(copy.matrixRows);
    }

    public Matrix(double[][] array) {
        matrixRows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            matrixRows[i] = new Vector(Arrays.copyOf(array[i], array.length));
        }
    }

    public Matrix(Vector[] vectors) {
        matrixRows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            int vectorSize = vectors[i].getSize();

            double[] components = new double[vectorSize];

            for (int j = 0; j < vectorSize; j++) {
                components[j] = vectors[i].getComponent(j);
            }

            matrixRows[i] = new Vector(vectors.length, components);
        }
    }

    public int getSize() {
        int matrixSize = matrixRows.length;

        for (Vector matrixRow : matrixRows) {
            if (matrixRow.getSize() != matrixSize) {
                throw new IllegalArgumentException("Матрица " + matrixSize + " x " + matrixRow.getSize() +
                        " не является квадратной");
            }
        }

        return matrixSize;
    }

    public Vector getVector(int index) {

    }

    public void setVector(int index) {

    }

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