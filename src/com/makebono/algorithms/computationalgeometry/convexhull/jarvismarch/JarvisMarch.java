package com.makebono.algorithms.computationalgeometry.convexhull.jarvismarch;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.makebono.algorithms.computationalgeometry.convexhull.ConvexHullGenerator;
import com.makebono.datastructures.graph.Vertex;
import com.makebono.datastructures.tools.polaranglecomparator.Clockwised;
import com.makebono.datastructures.tools.polaranglecomparator.CounterClockwised;

/** 
 * @ClassName: JarvisMarch 
 * @Description: Gift wrapping algorithm for convex hull generating
 * @author makebono
 * @date 2017年11月17日 下午4:54:46 
 *  
 */
public class JarvisMarch<T> extends ConvexHullGenerator<T> {

    @Override
    protected ArrayList<Vertex<T>> candidates() {
        Queue<Vertex<T>> unvisited = new PriorityQueue<Vertex<T>>(new CounterClockwised<T>(this.minimumY()));

        // For vertices output.
        final ArrayList<Vertex<T>> vertices = new ArrayList<Vertex<T>>();

        // Add all vertices except the sink (the one with lowest Y value) in priority queue.
        for (final Vertex<T> temp : this.getGraph().getVertices()) {
            if (temp.getIndex() != this.minimumY().getIndex()) {
                unvisited.add(temp);
            }
        }

        vertices.add(this.minimumY());
        vertices.add(unvisited.poll());
        Vertex<T> cursor = vertices.get(vertices.size() - 1);

        // Counter clockwised scan the graph until reach the top vertex. This complete the right half of the hull.
        while (cursor.getIndex() != this.maximumY().getIndex()) {
            final ArrayList<Vertex<T>> temp = new ArrayList<Vertex<T>>();
            temp.addAll(unvisited);
            unvisited = new PriorityQueue<Vertex<T>>(new CounterClockwised<T>(cursor));
            unvisited.addAll(temp);

            vertices.add(unvisited.poll());
            cursor = vertices.get(vertices.size() - 1);
        }
        // System.out.println(unvisited);

        unvisited.add(this.maximumY());
        ArrayList<Vertex<T>> temp = new ArrayList<Vertex<T>>();
        temp.addAll(unvisited);
        unvisited = new PriorityQueue<Vertex<T>>(new Clockwised<T>(this.minimumY()));
        unvisited.addAll(temp);

        vertices.add(unvisited.poll());

        // Add the top vertex back and then scan the graph clockwised until reached the top vertex. This complete the
        // left half of convex hull.
        while (cursor.getIndex() != this.maximumY().getIndex()) {
            temp = new ArrayList<Vertex<T>>();
            temp.addAll(unvisited);
            unvisited = new PriorityQueue<Vertex<T>>(new Clockwised<T>(cursor));
            unvisited.addAll(temp);

            vertices.add(unvisited.poll());
            cursor = vertices.get(vertices.size() - 1);
        }

        for (int i = 0; i < vertices.size() - 1; i++) {
            this.getGraph().add(vertices.get(i), vertices.get(i + 1));
        }
        this.getGraph().add(vertices.get(vertices.size() - 1), vertices.get(0));

        return vertices;
    }

}
