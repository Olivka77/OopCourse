package ru.academits.polyanskaya.matrix;

import java.util.Vector;

public class Matrix extends Vector {
    private int n;
    private int m;

    private double[][] matrix;
    //private Vector[] matrix;

    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;

        double[][] matrix = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public Matrix(Matrix copy) {
        //  this(copy.getN(), copy.getM(), copy...);
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        this.n = matrix.length;
        this.m = matrix[0].length;
    }

    public Matrix(Vector[] matrix) {
        this.
    }

}
