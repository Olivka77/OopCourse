package ru.academits.polyanskaya.matrix;

import ru.academits.polyanskaya.vector.Vector;

public class Matrix extends Vector {
    private int n;
    private int m;
    private double[][] matrix;

    public Matrix(int dimension, int n, int m) {
        super(dimension);
        this.n = n;
        this.m = m;
        this.matrix = new double[n][m];
    }
}
