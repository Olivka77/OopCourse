package ru.academits.polyanskaya.vector;

import java.util.Arrays;

public class Vector {
    private int dimension;
    private double[] components;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("размерность должна быть больше нуля");
        }

        this.dimension = dimension;
        this.components = new double[dimension];

        Arrays.fill(components, 0);
    }

    public Vector(Vector copy) {
        this(copy.getDimension(), copy.getComponents());
    }

    public Vector(double[] components) {
        this.components = components;
        dimension = components.length;
    }

    public Vector(int dimension, double[] components) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("размерность должна быть больше нуля");
        }

        if (components.length - 1 < dimension) {
            Vector vector = new Vector(dimension);

            for (int i = 0; i < components.length; i++) {
                vector.components[i] += components[i];
            }

            this.dimension = vector.dimension;
            this.components = vector.components;

            return;
        }

        this.dimension = dimension;
        this.components = components;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public double[] getComponents() {
        return components;
    }

    public void setComponents(double[] components) {
        this.components = components;
    }

    public int getSize() {
        return components.length;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < dimension; i++) {
            if (i == 0) {
                string = new StringBuilder("{ ");
            }

            if (i < dimension - 1) {
                string.append(components[i]).append(", ");
            }

            if (i == dimension - 2) {
                string.append(components[i + 1]).append(" }");
            }
        }

        return string.toString();
    }

    public Vector addVector(Vector vector) {
        if (components.length < vector.components.length) {
            for (int i = 0; i < components.length; i++) {
                vector.components[i] += components[i];
            }

            return vector;
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }

        return this;
    }

    public Vector subtractVector(Vector vector) {
        int minLength = Math.min(vector.components.length, components.length);
        int maxLength = Math.max(vector.components.length, components.length);

        if (vector.components.length <= components.length) {
            for (int i = 0; i < minLength; i++) {
                components[i] -= vector.components[i];
            }

            return this;
        }

        double[] tempArray = new double[maxLength];

        for (int i = 0; i < minLength; i++) {
            tempArray[i] = components[i] - vector.components[i];
        }

        for (int i = minLength; i < maxLength; i++) {
            tempArray[i] = -1 * vector.components[i];
        }

        System.arraycopy(tempArray, 0, vector.components, 0, maxLength);

        return vector;
    }

    public double[] multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }

        return components;
    }

    public Vector vectorReversal() {
        for (int i = 0; i < components.length; i++) {
            components[i] *= -1;
        }

        return this;
    }

    public double getLength() {
        double length = 0;

        for (double i : components) {
            length += Math.pow(i, 2);
        }

        return Math.sqrt(length);
    }

    public double getComponent(int index) {
        return components[index];
    }

    @Override
    public boolean equals(Object vector1) {
        if (vector1 == this) {
            return true;
        }

        if (vector1 == null || vector1.getClass() != getClass()) {
            return false;
        }

        Vector vector2 = (Vector) vector1;

        return dimension == vector2.dimension && Arrays.equals(components, vector2.components);
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int hash = 1;

        hash = prime * hash + Integer.hashCode(dimension);
        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1.getSize() <= vector2.getSize()) {
            Vector resultVector = new Vector(vector2);

            for (int i = 0; i < vector1.components.length; i++) {
                resultVector.components[i] = vector1.components[i] + vector2.components[i];
            }

            return resultVector;
        }

        Vector resultVector = new Vector(vector1);

        for (int i = 0; i < vector2.components.length; i++) {
            resultVector.components[i] = vector1.components[i] + vector2.components[i];
        }

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        if (vector1.getSize() < vector2.getSize()) {
            Vector resultVector = new Vector(vector2);

            for (int i = 0; i < vector1.components.length; i++) {
                resultVector.components[i] = vector1.components[i] - vector2.components[i];
            }

            for (int i = vector1.components.length; i < vector2.components.length; i++) {
                resultVector.components[i] *= -1;
            }

            return resultVector;
        }

        Vector resultVector = new Vector(vector1);

        for (int i = 0; i < vector2.components.length; i++) {
            resultVector.components[i] -= vector2.components[i];
        }

        return resultVector;
    }

    public static double scalarProduct(Vector vector1, Vector vector2) {
        double result = 0;
        int minLength = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < minLength; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}