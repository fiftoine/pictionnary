package com.fiftoine.model;

import java.io.Serializable;

/**
 * Represents a point of a line
 *
 * It has x and y coordinates
 *
 */
public class Vertice implements Serializable {

    /**
     * The X coordinate of the vertice
     */
    private final double x;

    /**
     * The Y coordinate of the vertice
     */
    private final double y;

    public Vertice(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the X coordinate of the vertice
     * @return the X coordinate of the vertice
     */
    public double getX() {
        return x;
    }
    /**
     * Returns the Y coordinate of the vertice
     * @return the Y coordinate of the vertice
     */
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "VerticeInfo{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


}
