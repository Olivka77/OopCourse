package ru.academits.polyanskaya.matrix;

import ru.academits.polyanskaya.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount < 1 || columnsCount < 1) {
            throw new IllegalArgumentException("Ошибка создания матрицы размера " + rowsCount + " на " + columnsCount + ", " +
                    "размер матрицы должен быть больше 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
    }

    public Matrix(double[][] array) {
        int maxRowLength = 0;

        for (double[] row : array) {
            if (row.length > maxRowLength) {
                maxRowLength = row.length;
            }
        }

        if (maxRowLength == 0) {
            throw new IllegalArgumentException("Ошибка создания матрицы размера 0");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxRowLength, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Ошибка создания матрицы размера 0");
        }

        rows = new Vector[vectors.length];

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

            rows[i] = new Vector(maxVectorSize, components);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index >= rows.length) {
            throw new IllegalArgumentException("Индекс: " + index + " превышает максимально допустимый индекс строки матрицы: " +
                    (rows.length - 1));
        }

        if (index < 0) {
            throw new IllegalArgumentException("Передан индекс " + index + ", индекс не может быть отрицательным");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IllegalArgumentException("Индекс " + index + " за пределами диапазона допустимых значений от 0 до " +
                    (rows.length - 1));
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размер переданного вектора: " + vector.getSize() +
                    ", что не соответствует размеру строки матрицы: " + (getColumnsCount()));
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        int rowsCount = rows.length;
        int columnsCount = getColumnsCount();

        if (index >= columnsCount) {
            throw new IllegalArgumentException("Индекс: " + index + " превышает максимально допустимый индекс столбца матрицы: " +
                    (columnsCount - 1));
        }

        if (index < 0) {
            throw new IllegalArgumentException("Передан индекс " + index + ", индекс не может быть отрицательным");
        }

        Vector vector = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            vector.setComponent(i, rows[i].getComponent(index));
        }

        return vector;
    }

    public void transpose() {
        int columnsCount = getColumnsCount();

        Vector[] vectors = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; i++) {
            vectors[i] = getColumn(i);
        }

        rows = vectors;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new UnsupportedOperationException("Неверные размеры матрицы: " + rows.length + " x " + getColumnsCount() +
                    ", для вычисления определителя требуется квадратная матрица");
        }

        int matrixSize = rows.length;

        Matrix matrixCopy = new Matrix(this);

        int coefficient = -1;
        double determinant = 1;
        double epsilon = 1.0e-10;
        boolean hasZeroInDiagonal = false;

        for (int i = 0; i < matrixSize; i++) {
            for (int j = i; j < matrixSize; j++) {
                if (Math.abs(matrixCopy.rows[i].getComponent(i)) <= epsilon) {
                    hasZeroInDiagonal = true;

                    if (Math.abs(matrixCopy.rows[j].getComponent(i)) > epsilon) {
                        determinant *= coefficient;

                        for (int k = 0; k < matrixSize; k++) {
                            double temp = matrixCopy.rows[i].getComponent(k);
                            matrixCopy.rows[i].setComponent(k, matrixCopy.rows[j].getComponent(k));
                            matrixCopy.rows[j].setComponent(k, temp);
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
                if (Math.abs(matrixCopy.rows[j].getComponent(i)) > epsilon) {
                    double factor = matrixCopy.rows[j].getComponent(i) / matrixCopy.rows[i].getComponent(i);

                    for (int k = i; k < matrixSize; k++) {
                        matrixCopy.rows[j].setComponent(k, matrixCopy.rows[j].getComponent(k) -
                                matrixCopy.rows[i].getComponent(k) * factor);
                    }
                }
            }

            determinant *= matrixCopy.rows[i].getComponent(i);
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Количество столбцов матрицы: " + getColumnsCount() + ", размер вектора: " + vector.getSize() +
                    ", для умножения матрицы на вектор-столбец количество столбцов матрицы и размер вектора должны быть одинаковыми");
        }

        Vector resultVector = new Vector(vector.getSize());

        for (int i = 0; i < rows.length; i++) {
            resultVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    private static void sizesControl(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры матриц: " + matrix1.rows.length + " x " + matrix1.getColumnsCount() + ", "
                    + matrix2.rows.length + " х " + matrix2.getColumnsCount() + ", невозможно выполнить операцию с матрицами разных размеров");
        }
    }

    public void add(Matrix matrix) {
        sizesControl(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        sizesControl(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        sizesControl(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);

        for (int i = 0; i < matrix1.rows.length; i++) {
            resultMatrix.rows[i] = Vector.getSum(matrix1.rows[i], matrix2.rows[i]);
        }

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        sizesControl(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);

        for (int i = 0; i < matrix1.rows.length; i++) {
            resultMatrix.rows[i] = Vector.getDifference(matrix1.rows[i], matrix2.rows[i]);
        }

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Невозможно получить произведение матриц, количество столбцов первой матрицы: "
                    + matrix1.getColumnsCount() + " не соответствует количеству строк второй матрицы: " + matrix2.rows.length);
        }

        Matrix resultMatrix = new Matrix(matrix1.rows.length, matrix2.getColumnsCount());

        for (int i = 0; i < resultMatrix.rows.length; i++) {
            for (int j = 0; j < resultMatrix.getColumnsCount(); j++)
                resultMatrix.rows[i].setComponent(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
        }

        return resultMatrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Vector vector : rows) {
            stringBuilder.append(vector).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }
}