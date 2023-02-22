package ru.academits.polyanskaya.matrix;

import ru.academits.polyanskaya.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrixRows;

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
        int maxRowLength = 0;

        for (double[] doubles : array) {
            if (doubles.length > maxRowLength) {
                maxRowLength = doubles.length;
            }
        }
        matrixRows = new Vector[array.length];

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

    public int[] getSize() {
        int rowsCount = matrixRows.length;
        int columnsCount = matrixRows[0].getSize();

        return new int[]{rowsCount, columnsCount};
    }

    public Vector getVectorRow(int index) {
        if (index > getSize()[0] - 1) {
            throw new ArrayIndexOutOfBoundsException("Индекс: " + index + " превышает максимально допустимый индекс строки матрицы: " +
                    (getSize()[0] - 1));
        }

        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Передан индекс " + index + ", индекс не может быть отрицательным");
        }

        return new Vector(matrixRows[index]);
    }

    public void setVectorRow(int index, Vector vector) {
        if (index > getSize()[0] - 1) {
            throw new ArrayIndexOutOfBoundsException("Индекс: " + index + " превышает максимально допустимый индекс строки матрицы: " +
                    (getSize()[0] - 1));
        }

        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Передан индекс " + index + ", индекс не может быть отрицательным");
        }

        if (vector.getSize() != matrixRows[index].getSize()) {
            throw new ArrayIndexOutOfBoundsException("Размер переданного вектора: " + vector.getSize() +
                    ", что не соответствует размеру строки матрицы: " + matrixRows[index].getSize());
        }

        matrixRows[index] = new Vector(vector);
    }

    public Vector getVectorColumn(int index) {
        int[] matrixSize = getSize();

        if (index > matrixSize[1] - 1) {
            throw new ArrayIndexOutOfBoundsException("Индекс: " + index + " превышает максимально допустимый индекс столбца матрицы: " +
                    (matrixSize[1] - 1));
        }

        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Передан индекс " + index + ", индекс не может быть отрицательным");
        }

        Vector vector = new Vector(matrixSize[1] - 1);

        for (int i = 0; i < matrixSize[0]; i++) {
            vector.setComponent(i, matrixRows[i].getComponent(index));
        }

        return vector;
    }

    public void getTransposition() {
        int rowsCount = matrixRows.length;
        int columnsCount = matrixRows[0].getSize();

        Matrix matrix = new Matrix(columnsCount, rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                matrix.matrixRows[j].setComponent(i, matrixRows[i].getComponent(j));
            }
        }

        this.matrixRows = matrix.matrixRows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector matrixRow : matrixRows) {
            for (int j = 0; j < matrixRows[0].getSize(); j++) {
                matrixRow.setComponent(j, matrixRow.getComponent(j) * scalar);
            }
        }
    }

    public double getDeterminant() {
        if (getSize()[0] != getSize()[1]) {
            throw new IllegalArgumentException("Неверные размеры матрицы, для вычисления определителя требуется квадратная матрица");
        }

        int matrixSize = getSize()[0];

        Matrix matrixCopy = new Matrix(this);

        int coefficient = -1;
        double determinant = 1;
        double epsilon = 1.0e-10;
        boolean hasZeroInDiagonal = false;

        for (int i = 0; i < matrixSize; i++) {
            for (int j = i; j < matrixSize; j++) {
                if (Math.abs(matrixCopy.matrixRows[i].getComponent(i)) <= epsilon) {
                    hasZeroInDiagonal = true;

                    if (Math.abs(matrixCopy.matrixRows[j].getComponent(i)) > epsilon) {
                        determinant *= coefficient;

                        for (int k = 0; k < matrixSize; k++) {
                            double temp = matrixCopy.matrixRows[i].getComponent(k);
                            matrixCopy.matrixRows[i].setComponent(k, matrixCopy.matrixRows[j].getComponent(k));
                            matrixCopy.matrixRows[j].setComponent(k, temp);
                        }

                        hasZeroInDiagonal = false;
                        break;
                    }
                }
            }

            if (hasZeroInDiagonal) {
                return 0;
            }

            for (int j = i + 1; j < matrixSize; j++) {
                if (Math.abs(matrixCopy.matrixRows[j].getComponent(i)) > epsilon) {
                    double factor = matrixCopy.matrixRows[j].getComponent(i) / matrixCopy.matrixRows[i].getComponent(i);

                    for (int k = i; k < matrixSize; k++) {
                        matrixCopy.matrixRows[j].setComponent(k, matrixCopy.matrixRows[j].getComponent(k) -
                                matrixCopy.matrixRows[i].getComponent(k) * factor);
                    }
                }
            }

            determinant *= matrixCopy.matrixRows[i].getComponent(i);
        }

        return determinant;
    }

    public void multiplyByVector(Vector vector, boolean vectorIsColumn) {
        if (vectorIsColumn) {
            if (getSize()[0] != vector.getSize()) {
                throw new IllegalArgumentException("Размер матрицы " + getSize()[0] + " не соответствует размеру вектора " +
                        vector.getSize() + ", для умножения матрицы на вектор-столбец размеры должны быть одинаковыми");
            }

            Vector[] vectors = new Vector[vector.getSize()];

            for (int i = 0; i < getSize()[0]; i++) {
                vectors[i] = new Vector(1);

                for (int j = 0; j < vector.getSize(); j++) { // по столбцам вектора
                    vectors[i].setComponent(0, vectors[i].getComponent(0) +
                            matrixRows[i].getComponent(j) * vector.getComponent(j));
                }

            }

            matrixRows = vectors;
        } else {
            if (getSize()[1] > 1) {
                throw new IllegalArgumentException("Матрица не является вектором-столбцом: количество столбцов в матрице " +
                        getSize()[0] + ". Для умножения на вектор-строку матрица должна быть вектором-столбцом");
            }

            if (getSize()[0] != vector.getSize()) {
                throw new IllegalArgumentException("Размер матрицы " + getSize()[0] + " не соответствует размеру вектора " +
                        vector.getSize() + ". Для умножения матрицы на вектор-столбец размеры должны быть одинаковыми");
            }

            Vector[] vectors = new Vector[vector.getSize()];

            for (int i = 0; i < vector.getSize(); i++) {
                vectors[i] = new Vector(vector.getSize());
            }

            for (int i = 0; i < getSize()[0]; i++) {
                for (int j = 0; j < vector.getSize(); j++) {
                    vectors[i].setComponent(j, matrixRows[i].getComponent(0) * vector.getComponent(j));
                }
            }

            matrixRows = vectors;
        }
    }

    public void add(Matrix matrix) {
        if (matrix.getSize()[0] != getSize()[0] || matrix.getSize()[1] != getSize()[1]) {
            throw new IllegalArgumentException("Невозможно сложить матрицы разных размеров");
        }

        for (int i = 0; i < getSize()[0]; i++) {
            for (int j = 0; j < getSize()[1]; j++) {
                matrixRows[i].setComponent(j, matrixRows[i].getComponent(j) + matrix.matrixRows[i].getComponent(j));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Vector vector : matrixRows) {
            stringBuilder.append(vector.toString()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }
}