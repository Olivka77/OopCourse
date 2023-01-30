import java.util.Arrays;

public class Vector {
    private int dimension;
    private double[] components;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("размерность должна быть больше нуля");
        }

        this.dimension = dimension;
        this.components = new double[dimension]; // обязательно ли тут this?

        Arrays.fill(components, 0);
    }

    public Vector(Vector copy) {
        this.dimension = copy.dimension;
        this.components = new double[dimension];
    }

    public Vector(double[] components) {
        this.components = components;
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

    public double getDimension() {
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

    public double[] addVector(Vector vector) {
        for (int i = 0; i < components.length; i++) {
            components[i] += vector.components[i];
        }

        return components;
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

    public static Vector getSum(Vector vector) {
        return new
    }
}