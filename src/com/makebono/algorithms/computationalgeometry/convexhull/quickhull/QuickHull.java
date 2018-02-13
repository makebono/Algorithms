/**Note:
 * Quick hull algorithm is pretty much like quick sort. it is a divide-and-conquer approach. It recursively looking for
 * new convex hull vertices and connect them to pre-detected ones, then going for next hull vertices until finished. 
 */
package com.makebono.algorithms.computationalgeometry.convexhull.quickhull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

import com.makebono.algorithms.computationalgeometry.convexhull.ConvexHullGenerator;
import com.makebono.algorithms.computationalgeometry.lineintersectiondetection.tools.Line;
import com.makebono.datastructures.graph.Vertex;

/** 
 * @ClassName: QuickHull 
 * @Description: Quick hull algorithm for finding convex hull in a graph. 
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

        // Regional line, both of the ends will be guaranteed to lay on the convex hull. Then find convex hull vertices
        // above and below this line recursively until done.
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

        // System.out.println(upperHalf);
        // System.out.println(lowerHalf);

        vertices.addAll(upperHalf);
        vertices.addAll(lowerHalf);

        // Add edges to the graph, this is done by adding vertices to the graph. Although candidate vertices are already
        // in the graph, they will be ignored and only the edges will be created in graph.
        if (upperHalf.size() > 0) {
            this.getGraph().add(xmin, upperHalf.get(0));

            for (int i = 0; i < upperHalf.size() - 1; i++) {
                this.getGraph().add(upperHalf.get(i), upperHalf.get(i + 1));
            }

            this.getGraph().add(upperHalf.get(upperHalf.size() - 1), xmax);
        }

        if (lowerHalf.size() > 0) {
            this.getGraph().add(xmax, lowerHalf.get(0));

            for (int i = 0; i < lowerHalf.size() - 1; i++) {
                this.getGraph().add(lowerHalf.get(i), lowerHalf.get(i + 1));
            }

            this.getGraph().add(lowerHalf.get(lowerHalf.size() - 1), xmin);
        }

        return vertices;
    }

    // Find new convex hull vertices in upper section of the regional line.
    private ArrayList<Vertex<T>> findU(final ArrayList<Vertex<T>> uCandidates, final Line l, final Vertex<T> xmin,
            final Vertex<T> xmax) {
        // Base case, if no or just one vertex is above the regional line, return the candidates set. Either an empty
        // set will be added to the result set or a new convex hull vertex will be added to the result set.
        if (uCandidates.size() <= 1) {
            // System.out.println(uCandidates);
            return uCandidates;
        }

        final ArrayList<Vertex<T>> result = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> ulCandidates = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> urCandidates = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> ul = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> ur = new ArrayList<Vertex<T>>();

        Vertex<T> top = uCandidates.get(0);
        BigDecimal maxDist = l.distTo(top.getX(), top.getY());

        // Find the vertex farest to the line among candidates.
        for (int i = 1; i < uCandidates.size(); i++) {
            final Vertex<T> cursor = uCandidates.get(i);
            if (l.distTo(cursor.getX(), cursor.getY()).compareTo(maxDist) > 0) {
                top = cursor;
                maxDist = l.distTo(top.getX(), top.getY());
            }
        }

        // System.out.println(top);

        // Draw 2 lines, which connect from left most and right most vertex in this recursion to the top vertex. Use
        // them to get 2 new sets of candidates for next recursion.
        final Line ll = new Line(xmin.getX(), xmin.getY(), top.getX(), top.getY());
        final Line lr = new Line(top.getX(), top.getY(), xmax.getX(), xmax.getY());

        for (final Vertex<T> cursor : uCandidates) {
            if (ll.isAbove(cursor.getX(), cursor.getY()) && cursor != xmin && cursor != top
            // At first I used [cursor.getX() <= top.getX()], but there won't be a vertex above the line and have same
            // x-coordinate as top vertex, otherwise it will be the top.
                    && cursor.getX() < top.getX()) {
                ulCandidates.add(cursor);
            } else if (lr.isAbove(cursor.getX(), cursor.getY()) && cursor != xmax && cursor != top
                    && cursor.getX() > top.getX()) {
                urCandidates.add(cursor);
            }
        }

        // System.out.println(ulCandidates);
        // System.out.println(urCandidates);

        // Get convex hull vertices from new recursions.
        ul.addAll(this.findU(ulCandidates, ll, xmin, top));
        ur.addAll(this.findU(urCandidates, lr, top, xmax));

        // Add them to the result. don't forget about the top vertices from each recursion.
        result.addAll(ul);
        result.addAll(ur);
        result.add(top);

        return result;
    }

    private ArrayList<Vertex<T>> findL(final ArrayList<Vertex<T>> lCandidates, final Line l, final Vertex<T> xmin,
            final Vertex<T> xmax) {
        if (lCandidates.size() <= 1) {
            return lCandidates;
        }

        final ArrayList<Vertex<T>> result = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> llCandidates = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> lrCandidates = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> ll = new ArrayList<Vertex<T>>();
        final ArrayList<Vertex<T>> lr = new ArrayList<Vertex<T>>();

        Vertex<T> bottom = lCandidates.get(0);
        BigDecimal maxDist = l.distTo(bottom.getX(), bottom.getY());

        for (final Vertex<T> cursor : lCandidates) {
            if (l.distTo(cursor.getX(), cursor.getY()).compareTo(maxDist) > 0) {
                bottom = cursor;
                maxDist = l.distTo(bottom.getX(), bottom.getY());
            }
        }

        final Line lleft = new Line(xmin.getX(), xmin.getY(), bottom.getX(), bottom.getY());
        final Line lright = new Line(bottom.getX(), bottom.getY(), xmax.getX(), xmax.getY());

        for (final Vertex<T> cursor : lCandidates) {
            if (!lleft.isAbove(cursor.getX(), cursor.getY()) && cursor != xmin && cursor != bottom
                    && cursor.getX() < bottom.getX()) {
                llCandidates.add(cursor);
            } else if (!lright.isAbove(cursor.getX(), cursor.getY()) && cursor != xmax && cursor != bottom
                    && cursor.getX() > bottom.getX()) {
                lrCandidates.add(cursor);
            }
        }

        ll.addAll(this.findL(llCandidates, lleft, xmin, bottom));
        lr.addAll(this.findL(lrCandidates, lright, bottom, xmax));

        result.addAll(ll);
        result.addAll(lr);
        result.add(bottom);

        return result;
    }
}
