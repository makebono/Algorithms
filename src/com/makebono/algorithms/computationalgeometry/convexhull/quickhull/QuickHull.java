package com.makebono.algorithms.computationalgeometry.convexhull.quickhull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

import com.makebono.algorithms.computationalgeometry.convexhull.ConvexHullGenerator;
import com.makebono.algorithms.computationalgeometry.lineintersectiondetection.tools.Line;
import com.makebono.datastructures.graph.Vertex;

/** 
 * @ClassName: QuickHull 
 * @Description: QuickHull 
 * @author makebono
 * @date 2018年2月12日 上午11:52:00 
 *  
 */
@SuppressWarnings("unchecked")
public class QuickHull<T> extends ConvexHullGenerator<T> {

    @Override
    protected ArrayList<Vertex<T>> candidates() {
        final ArrayList<Vertex<T>> vertices = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> candidates = new ArrayList<Vertex<T>>();
        candidates.addAll(this.getGraph().getVertices());
        final ArrayList<Vertex<T>> uCandidates = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> lCandidates = new ArrayList<Vertex<T>>();

        final Vertex<T> xmin = this.minimumX();
        final Vertex<T> xmax = this.maximumX();

        vertices.add(xmin);
        vertices.add(xmax);

        final Line l = new Line(xmin.getX(), xmin.getY(), xmax.getX(), xmax.getY());

        candidates.forEach(x -> {
            if (l.isAbove(x.getX(), x.getY()) && x != xmin && x != xmax) {
                uCandidates.add(x);
            } else if (x != xmin && x != xmax) {
                lCandidates.add(x);
            }
        });

        // System.out.println(uCandidates);
        // System.out.println(lCandidates);

        final ArrayList<Vertex<T>> upperHalf = this.findU(uCandidates, l, xmin, xmax);
        final ArrayList<Vertex<T>> lowerHalf = this.findL(lCandidates, l, xmin, xmax);
        upperHalf.sort(Comparator.comparing(x -> ((Vertex<T>) x).getX()));
        lowerHalf.sort(Comparator.comparing(x -> ((Vertex<T>) x).getX()).reversed());

        vertices.addAll(upperHalf);
        vertices.addAll(lowerHalf);

        this.getGraph().add(xmin, upperHalf.get(0));

        for (int i = 0; i < upperHalf.size() - 1; i++) {
            this.getGraph().add(upperHalf.get(i), upperHalf.get(i + 1));
        }

        this.getGraph().add(upperHalf.get(upperHalf.size() - 1), xmax);

        this.getGraph().add(xmax, lowerHalf.get(lowerHalf.size() - 1));

        for (int i = lowerHalf.size() - 1; i > 1; i--) {
            this.getGraph().add(upperHalf.get(i - 1), upperHalf.get(i));
        }

        this.getGraph().add(lowerHalf.get(0), xmin);

        return vertices;
    }

    private ArrayList<Vertex<T>> findU(final ArrayList<Vertex<T>> uCandidates, final Line l, final Vertex<T> xmin,
            final Vertex<T> xmax) {
        if (uCandidates.size() <= 1) {
            return uCandidates;
        }

        final ArrayList<Vertex<T>> result = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> ulCandidates = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> urCandidates = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> ul = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> ur = new ArrayList<Vertex<T>>();

        Vertex<T> top = uCandidates.get(0);
        final BigDecimal initDist = l.distTo(top.getX(), top.getY());

        for (int i = 1; i < uCandidates.size(); i++) {
            final Vertex<T> cursor = uCandidates.get(i);
            if (l.distTo(cursor.getX(), cursor.getY()).compareTo(initDist) > 0) {
                top = cursor;
            }
        }

        for (final Vertex<T> cursor : uCandidates) {
            if (l.isAbove(cursor.getX(), cursor.getY()) && cursor != xmin && cursor != top
                    && cursor.getX() <= top.getX()) {
                ulCandidates.add(cursor);
            } else if (l.isAbove(cursor.getX(), cursor.getY()) && cursor != xmax && cursor != top
                    && cursor.getX() > top.getX()) {
                urCandidates.add(cursor);
            }
        }

        final Line ll = new Line(xmin.getX(), xmin.getY(), top.getX(), top.getY());
        final Line lr = new Line(top.getX(), top.getY(), xmax.getX(), xmax.getY());

        ul.addAll(this.findU(ulCandidates, ll, xmin, top));
        ur.addAll(this.findU(urCandidates, lr, top, xmax));

        result.addAll(ul);
        result.addAll(ur);
        result.add(top);

        return result;
    }

    private ArrayList<Vertex<T>> findL(final ArrayList<Vertex<T>> lCandidates, final Line l, final Vertex<T> xmin,
            final Vertex<T> xmax) {
        if (lCandidates.size() == 1) {
            return lCandidates;
        }

        final ArrayList<Vertex<T>> result = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> llCandidates = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> lrCandidates = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> ll = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> lr = new ArrayList<Vertex<T>>();

        Vertex<T> bottom = lCandidates.get(0);
        final BigDecimal initDist = l.distTo(bottom.getX(), bottom.getY());

        for (final Vertex<T> cursor : lCandidates) {
            if (l.distTo(cursor.getX(), cursor.getY()).compareTo(initDist) > 0) {
                bottom = cursor;
            }
        }

        for (final Vertex<T> cursor : lCandidates) {
            if (!l.isAbove(cursor.getX(), cursor.getY()) && cursor != xmin && cursor != bottom
                    && cursor.getX() <= bottom.getX()) {
                llCandidates.add(cursor);
            } else if (!l.isAbove(cursor.getX(), cursor.getY()) && cursor != xmax && cursor != bottom
                    && cursor.getX() > bottom.getX()) {
                lrCandidates.add(cursor);
            }
        }

        final Line lleft = new Line(xmin.getX(), xmin.getY(), bottom.getX(), bottom.getY());
        final Line lright = new Line(bottom.getX(), bottom.getY(), xmax.getX(), xmax.getY());

        ll.addAll(this.findL(llCandidates, lleft, xmin, bottom));
        lr.addAll(this.findL(lrCandidates, lright, bottom, xmax));

        result.addAll(ll);
        result.addAll(lr);
        result.add(bottom);

        return result;
    }
}
