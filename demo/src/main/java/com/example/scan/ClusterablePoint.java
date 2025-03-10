package com.example.scan;

import org.apache.commons.math3.ml.clustering.Clusterable;

public class ClusterablePoint implements Clusterable {
    private final double[] points;

    public ClusterablePoint(double[] points) {
        this.points = points;
    }

    @Override
    public double[] getPoint() {
        return points;
    }
}
