package com.makebono.algorithms.computationalgeometry.closestpointpair;

import java.util.Arrays;
import java.util.HashMap;

import com.makebono.datastructures.graph.BonoGraph;
import com.makebono.datastructures.graph.Vertex;
import com.makebono.datastructures.graph.graphInterface.Graph;
import com.makebono.datastructures.tools.graphcomparator.GraphComparatorX;
import com.makebono.datastructures.tools.graphcomparator.GraphComparatorY;

/** 
 * @ClassName: ClosestPair 
 * @Description: A short program finding closest pair of points in a graph
 * @author makebono
 * @date 2017年12月21日 下午2:52:47 
 *  
 */
@SuppressWarnings("unchecked")
public class ClosestPair<T> {
    private final Graph<T> graph;
    private Vertex<T>[] p;
    private Vertex<T>[] x;
    private Vertex<T>[] y;
    private final GraphComparatorX<T> sideKickX;
    private final GraphComparatorY<T> sideKickY;

    public ClosestPair() {
        this.graph = new BonoGraph<T>();
        this.p = null;
        this.x = null;
        this.y = null;
        this.sideKickX = new GraphComparatorX<T>();
        this.sideKickY = new GraphComparatorY<T>();
    }

    // Optimization needed.
    public Vertex<T>[] closestPair() {
        return this.closestPair(this.x, this.y);
    }

    public void init() {
        this.p = new Vertex[this.graph.getVertices().size()];
        int i = 0;
        for (final Vertex<T> cursor : this.graph.getVertices()) {
            this.p[i++] = cursor;
        }
        this.x = this.sort(true, p);
        this.y = this.sort(false, p);
    }

    private Vertex<T>[] closestPair(final Vertex<T>[] x, final Vertex<T>[] y) {
        if (x.length <= 3) {
            return this.closest(x);
        }
        final int size = x.length;
        final Vertex<T>[] xl = new Vertex[size / 2];
        final Vertex<T>[] xr = new Vertex[size - size / 2];
        final Vertex<T>[] yl = new Vertex[size];
        final Vertex<T>[] yr = new Vertex[size - size / 2];

        double xm = 0;

        for (int i = 0; i < size; i++) {
            if (i < size / 2) {
                xl[i] = x[i];
                yl[i] = y[i];
            } else {
                xr[i - size / 2] = x[i];
                yr[i - size / 2] = y[i];
            }
        }
        xm = xr[0].getX();

        final Vertex<T>[] minL = this.closestPair(xl, yl);
        final Vertex<T>[] minR = this.closestPair(xr, yr);
        Vertex<T>[] min;

        final double dl = minL[0].dist(minL[1]);
        final double dr = minR[0].dist(minR[1]);
        double md = 0;

        if (dl <= dr) {
            min = minL;
            md = dl;
        } else {
            min = minR;
            md = dr;
        }

        final double xlr = xm - md;
        final double xrr = xm + md;
        int count = 0;
        final Vertex<T>[] yymt = new Vertex[size];

        for (int i = 0; i < size; i++) {
            if (y[i].getX() >= xlr && y[i].getX() <= xrr) {
                yymt[count] = y[i];
                count++;
            }
        }

        if (count >= 2) {
            final Vertex<T>[] yym = new Vertex[count];
            for (int i = 0; i < count; i++) {
                yym[i] = yymt[i];
            }
            final Vertex<T>[] minM = this.regionalClosest(yym);

            final double dm = minM[0].dist(minM[1]);

            if (dm <= md) {
                min = minM;
            }
        }

        return min;
    }

    public Vertex<T>[] forcePickClosestPair() {
        return this.closest(this.p);
    }

    private Vertex<T>[] closest(final Vertex<T>[] input) {
        if (input.length < 3) {
            return input;
        }
        Vertex<T>[] result = new Vertex[2];
        final HashMap<Double, Vertex<T>[]> map = new HashMap<Double, Vertex<T>[]>();

        double min = Double.MAX_VALUE;

        for (int i = 0; i < input.length - 1; i++) {
            int n = i + 1;
            while (n < input.length) {
                final double tempDist = input[i].dist(input[n]);

                if (min > tempDist) {
                    min = tempDist;
                    map.put(min, new Vertex[] { input[i], input[n] });
                }
                n++;
            }
        }

        result = map.get(min);
        return result;
    }

    private Vertex<T>[] regionalClosest(final Vertex<T>[] input) {
        if (input.length <= 8) {
            return this.closest(input);
        }
        Vertex<T>[] result = new Vertex[2];
        final int size = input.length;
        final HashMap<Double, Vertex<T>[]> map = new HashMap<Double, Vertex<T>[]>();
        double min = Double.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            if (size - i + 1 >= 8) {
                final Vertex<T>[] buffer = Arrays.copyOfRange(input, i, i + 8);
                final Vertex<T>[] tempMin = this.closest(buffer);
                final double tempD = tempMin[0].dist(tempMin[1]);
                if (tempD <= min) {
                    min = tempD;
                }
                map.put(tempD, tempMin);

            } else {
                final Vertex<T>[] buffer = Arrays.copyOfRange(input, i, input.length);
                final Vertex<T>[] tempMin = this.closest(buffer);
                final double tempD = tempMin[0].dist(tempMin[1]);
                if (tempD <= min) {
                    min = tempD;
                }
                map.put(tempD, tempMin);
            }
        }

        result = map.get(min);

        return result;
    }

    public Graph<T> getGraph() {
        return this.graph;
    }

    private Vertex<T>[] sort(final boolean x, final Vertex<T>[] p) {
        final Vertex<T>[] result = Arrays.copyOf(p, p.length);
        if (x) {
            Arrays.sort(result, this.sideKickX);
        } else {
            Arrays.sort(result, this.sideKickY);
        }

        return result;
    }

    public void add(final int index, final T data, final double x, final double y) {
        final Vertex<T> newVertex = new Vertex<T>(index, data, x, y);
        this.graph.add(newVertex);
    }

}
