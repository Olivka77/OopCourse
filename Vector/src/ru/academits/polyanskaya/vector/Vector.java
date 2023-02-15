package ru.academits.polyanskaya.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int vectorSize) {
        if (vectorSize <= 0) {
            throw new IllegalArgumentException("Размер вектора = " + vectorSize + ", размерность должна быть больше нуля");
        }

        components = new double[vectorSize];
    }

    public Vector(Vector copy) {
        this(copy.components);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Размер вектора = " + components.length + ", размерность должна быть больше нуля");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int vectorSize, double[] components) {
        if (vectorSize <= 0) {
            throw new IllegalArgumentException("Размер вектора = " + vectorSize + ", размерность должна быть больше нуля");
        }

        this.components = Arrays.copyOf(components, vectorSize);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (double component : components) {
            stringBuilder.append(component).append(", ");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1).deleteCharAt(stringBuilder.length() - 1).append("}");

        return stringBuilder.toString();
    }

    public Vector add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.getSize());
        }

        for (int i = 0; i < components.length; i++) {
            components[i] += vector.components[i];
        }

        return this;
    }

    public Vector subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < components.length; i++) {
            components[i] -= vector.components[i];
        }

        return this;
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public Vector deploy() {
        this.multiplyByScalar(-1);

        return this;
    }

    public double getLength() {
        double componentsSquaresSum = 0;

        for (double component : components) {
            componentsSquaresSum += component * component;
        }

        return Math.sqrt(componentsSquaresSum);
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index > components.length - 1) {
            throw new IllegalArgumentException("Индекс компоненты вектора: " + index + ", " +
                    "данный индекс больше максимально допустимого для текущего вектора: " + (components.length - 1));
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс компоненты вектора: " + index + ", индекс не может быть меньше 0");
        }

        components[index] = component;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) object;

        return components.length == vector.components.length && Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);

        return resultVector.add(vector2);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);

        return resultVector.subtract(vector2);
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;
        int minSize = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < minSize; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}