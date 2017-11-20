package com.makebono.algorithms.computationalgeometry.convexhull.grahamscan;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.makebono.algorithms.computationalgeometry.convexhull.ConvexHullGenerator;
import com.makebono.datastructures.graph.Vertex;
import com.makebono.datastructures.tools.polaranglecomparator.CrossProductComparator;

/** 
 * @ClassName: GrahamScan 
 * @Description: Graham scan for finding convex hull, uses a container of edges and vertices for output.
 * @author makebono
 * @param <T>
 * @date 2017年11月16日 下午3:35:12 
 *  
 */
public class GrahamScan<T> extends ConvexHullGenerator<T> {

    @Override
    protected ArrayList<Vertex<T>> candidates() {
        final CrossProductComparator<T> sideKick = new CrossProductComparator<T>(this.minimumY());
        final Queue<Vertex<T>> unvisited = new PriorityQueue<Vertex<T>>(sideKick);
        final Stack<Vertex<T>> candidates = new Stack<Vertex<T>>();

        // For vertices output.
        final ArrayList<Vertex<T>> vertices = new ArrayList<Vertex<T>>();

        // Add all vertices except the sink (the one with lowest Y value) in priority queue.
        for (final Vertex<T> temp : this.getGraph().getVertices()) {
            if (temp.getIndex() != this.minimumY().getIndex()) {
                unvisited.add(temp);
            }
        }

        // System.out.println(unvisited.toString());
        final int size = unvisited.size();
        candidates.push(this.minimumY());
        candidates.push(unvisited.poll());
        candidates.push(unvisited.poll());

        // The queue is ordered by vertex's polar angle to the sink. Polling this queue to scan the graph counter
        // clockwise.
        for (int i = 2; i < size; i++) {
            final Vertex<T> temp = unvisited.poll();

            // If the next move is not a "left turn" comparing to current angle, pop the top and add next vertex in.
            // Consider this procedure as extending the hull to proper size.
            while (!this.leftTurn(temp, candidates.get(candidates.size() - 1), candidates.get(candidates.size() - 2))) {
                candidates.pop();
            }

            candidates.push(temp);
        }

        for (int i = 0; i < candidates.size() - 1; i++) {
            this.getGraph().add(candidates.get(i), candidates.get(i + 1));
            vertices.add(candidates.get(i));
        }

        // Don't forget to add the last edge in. Which connects last vertex and the first vertex (the sink) of the hull.
        this.getGraph().add(candidates.get(candidates.size() - 1), candidates.get(0));
        vertices.add(candidates.get(candidates.size() - 1));

        return vertices;
    }

    // If cross product of two vertex(geometrically speaking, means vertices on a shape) v1 v2 is larger than 0, then it
    // means v1 is at the right side of v2.
    private boolean leftTurn(final Vertex<T> nextCandidate, final Vertex<T> top1, final Vertex<T> top2) {
        boolean left = false;

        // (X1 - X0)*(Y2 - Y0) - (X2 - X0)*(Y1 - Y0) > 0 => left turn.
        left = ((top1.getX() - top2.getX()) * (nextCandidate.getY() - top2.getY())
                - (nextCandidate.getX() - top2.getX()) * (top1.getY() - top2.getY())) > 0;
        return left;
    }
}
