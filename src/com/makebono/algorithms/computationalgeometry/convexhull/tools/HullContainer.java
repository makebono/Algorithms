package com.makebono.algorithms.computationalgeometry.convexhull.tools;

import java.util.ArrayList;

import com.makebono.datastructures.graph.Edge;
import com.makebono.datastructures.graph.Vertex;

/** 
 * @ClassName: HullContainer 
 * @Description: A container of an array list of vertices and an array list of edges, these elements form the convex hull.
 * @author mekebono
 * @date 2017年11月17日 下午3:19:07 
 *  
 */
public class HullContainer<T> {
    private final ArrayList<Vertex<T>> vertices;
    private final ArrayList<Edge<T>> edges;

    public HullContainer(final ArrayList<Vertex<T>> vertices, final ArrayList<Edge<T>> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public ArrayList<Vertex<T>> getVertices() {
        return this.vertices;
    }

    public ArrayList<Edge<T>> getEdges() {
        return this.edges;
    }
}
