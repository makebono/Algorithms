package com.makebono.algorithms.pathfinder.astaralgorithm.old;

import java.util.ArrayList;

public class node implements Comparable<node> {
    private final int label;
    private final ArrayList<node> edge;
    private final double x;
    private final double y;
    private double dist;
    private double est;
    private double rem;

    public node(final int index, final double xCo, final double yCo) {
        label = index;
        x = xCo;
        y = yCo;
        edge = new ArrayList<node>();
    }

    public void setAdjacent(final node N) {
        edge.add(N);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getLabel() {
        return label;
    }

    public node getEdge(final int label) {
        return edge.get(label);
    }

    public int countEdges() {
        return edge.size();
    }

    public void setDist(final double d) {
        dist = d;
    }

    public void setEst(final double e) {
        est = e;
    }

    public void setRem(final double r) {
        rem = r;
    }

    public double getDist() {
        return dist;
    }

    public double getEst() {
        return est;
    }

    public double getRem() {
        return rem;
    }

    public void showEdges() {
        int i = 0;
        while (i < this.countEdges()) {
            System.out.println(this.getEdge(i).getLabel() + 1);
            i++;
        }
    }

    public double distTo(final node N) {
        double dist;
        dist = Math.sqrt(Math.pow(N.getX() - x, 2) + Math.pow(N.getY() - y, 2));
        return dist;
    }

    public int compareTo(final node o) {
        // TODO Auto-generated method stub
        return 0;
    }

}
