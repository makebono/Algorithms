/**Instruction:
 *     final JarvisMarch<Integer> jm = new JarvisMarch<Integer>();
 *         jm.add(1, 1, 3, -3);
 *         jm.add(2, 3, 0.5, 0.5);
 *         jm.add(3, 3, 3, 0);
 *         jm.add(4, 3, 0, 3);
 *         jm.add(5, 3, -2, 1);
 *         jm.add(6, 3, -1.5, -2);
 *         System.out.println(jm); 
 */
package com.makebono.algorithms.computationalgeometry.convexhull.jarvismarch;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.makebono.algorithms.computationalgeometry.convexhull.ConvexHullGenerator;
import com.makebono.datastructures.graph.Vertex;
import com.makebono.datastructures.tools.polaranglecomparator.CrossProductComparator;
import com.makebono.datastructures.tools.polaranglecomparator.InvertedScan;

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
        Queue<Vertex<T>> unvisited = new PriorityQueue<Vertex<T>>(new CrossProductComparator<T>(this.minimumY()));

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
        ArrayList<Vertex<T>> temp = new ArrayList<Vertex<T>>();
        temp.add(this.minimumY());

        // System.out.println(cursor.getIndex());

        while (cursor.getIndex() != this.maximumY().getIndex()) {
            temp = new ArrayList<Vertex<T>>();
            temp.addAll(unvisited);
            unvisited = new PriorityQueue<Vertex<T>>(new CrossProductComparator<T>(cursor));
            unvisited.addAll(temp);

            vertices.add(unvisited.poll());
            cursor = vertices.get(vertices.size() - 1);
            // System.out.println(cursor.getIndex());
        }

        // System.out.println(cursor + " is current cursor \n");

        temp = new ArrayList<Vertex<T>>();
        temp.addAll(unvisited);
        temp.add(this.minimumY());
        unvisited = new PriorityQueue<Vertex<T>>(new InvertedScan<T>(cursor));
        unvisited.addAll(temp);
        /*
        final int size = unvisited.size();
        for (int i = 0; i < size; i++) {
            System.out.println(unvisited.poll());
        }
        */
        // and then reverse the axis when reaching the top vertex. Scan the graph until reached bottom (minimumY)
        // vertex.
        while (cursor.getIndex() != this.minimumY().getIndex()) {
            temp = new ArrayList<Vertex<T>>();
            temp.addAll(unvisited);
            unvisited = new PriorityQueue<Vertex<T>>(new InvertedScan<T>(cursor));
            unvisited.addAll(temp);

            vertices.add(unvisited.poll());
            cursor = vertices.get(vertices.size() - 1);
        }

        // Remove the redundant bottom vertex.
        vertices.remove(vertices.size() - 1);

        for (int i = 0; i < vertices.size() - 1; i++) {
            this.getGraph().add(vertices.get(i), vertices.get(i + 1));
        }

        this.getGraph().add(vertices.get(vertices.size() - 1), vertices.get(0));

        return vertices;
    }

}
