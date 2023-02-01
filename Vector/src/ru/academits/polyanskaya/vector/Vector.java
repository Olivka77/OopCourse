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
        this.components = new double[dimension]; // проверить - обязательно ли тут this?

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

    public double[] addVector(Vector vector) {
        int minLength = Math.min(vector.components.length, components.length);

        for (int i = 0; i < minLength; i++) {
            components[i] += vector.components[i];
        }

        return components.length > vector.components.length ? components : vector.components;
    }

    public double[] subtractVector(Vector vector) {
        int minLength = Math.min(vector.components.length, components.length);
        int maxLength = Math.max(vector.components.length, components.length);

        for (int i = 0; i < minLength; i++) {
            components[i] -= vector.components[i];
        }

        if (components.length < vector.components.length) {
            for (int i = minLength; i < maxLength; i++) {
                components[i] *= -1;
            }

            return components;
        }

        for (int i = 0; i < minLength; i++) {
            components[i] -= vector.components[i];
        }

        return components;
    }

    public double[] multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }

        return components;
    }

    public double[] vectorReversal() {
        for (int i = 0; i < components.length; i++) {
            components[i] *= -1;
        }

        return components;
    }

    public double getLength() { // переписать - все компоненты в цикле д.б. сложены
        return Math.sqrt(Math.pow(components[0], 2) + Math.pow(components[dimension - 1], 2));
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

            for (int i = 0; i < resultVector.components.length; i++) {
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
        Vector resultVector = new Vector(Math.max(vector1.getSize(), vector2.getSize()));

        if (vector1.getSize() < vector2.getSize()) {
            for (int i = 0; i < vector1.components.length; i++) {
                resultVector.components[i] = vector1.components[i] - vector2.components[i];
            }

            return resultVector;
        }

        for (int i = 0; i < vector2.components.length; i++) {
            resultVector.components[i] = vector1.components[i] - vector2.components[i];
        }

        return resultVector;
    }

    public static double scalarProduct(Vector vector1, Vector vector2, double cos) {
        return vector1.getLength() * vector2.getLength() * Math.cos(cos);
    }
}