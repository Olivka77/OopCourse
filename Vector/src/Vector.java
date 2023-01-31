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
        this.components = new double[]{components[dimension]};
    }

    public Vector(int dimension, double[] components) {
        if (components.length - 1 < dimension) {
            for (int i = components.length - 1; i < dimension; i++) {
                components[i] = 0;
            }
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
        return components.length - 1;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < components.length; i++) {
            if (i == 0) {
                string = new StringBuilder("{ ");
            }

            string.append(components[i]).append(", ");

            if (i == dimension) {
                string.append("}").append(System.lineSeparator());
            }
        }

        return string.toString();
    }

    public Vector addVector(Vector vector) {
        if (getSize() > vector.getSize()) {
            // подумать про реализацию создания нового массива нужной длины или заполнения текущего
            for (int i = 0; i < vector.getSize(); i++) {
                components[i] = 0;
            }
        }

        if (this.getSize() < getSize()) {
            for (int i = vector.getSize(); i < this.getSize(); i++) {
                vector.components[i] = 0;
            }
        }

        for (int i = 0; i < components.length; i++) {
            components[i] += vector.components[i];
        }

        return vector;
    }

    public double[] subtractVector(Vector vector) {
        for (int i = 0; i < components.length; i++) {
            components[i] -= vector.components[i];
        }

        return components;
    }

    public double[] multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] = components[i] * scalar;
        }

        return components;
    }

    public double[] vectorReversal() {
        for (int i = 0; i < components.length; i++) {
            components[i] *= -1;
        }

        return components;
    }

    public double getLength() {
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

    public static Vector getSum(Vector vector) {
        Vector newVector = new
        return new
    }
}