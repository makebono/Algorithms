package com.makebono.algorithms.graphalgorithms.kcoloring;

import java.util.Comparator;
import java.util.List;

import com.makebono.datastructures.graph.BonoGraph;
import com.makebono.datastructures.graph.Edge;
import com.makebono.datastructures.graph.Vertex;
import com.makebono.datastructures.graph.graphInterface.Graph;
import com.makebono.datastructures.tools.graphcomparator.VertexIndexComparator;

/** 
 * @ClassName: AbstractKColoringPortal 
 * @Description: Abstract K-Coloring skeleton
 * @author makebono
 * @date 2018年1月17日 上午8:59:14 
 *  
 */
public abstract class AbstractKColoringPortal {
    protected static final int a = 97;
    protected int k;
    protected Graph<Character> graph;
    protected boolean colored = false;
    protected Comparator<Vertex<Character>> sidekick;

    public AbstractKColoringPortal() {
        this.graph = new BonoGraph<Character>();
    }

    public abstract void dealWithIt();

    public Graph<Character> getGraph() {
        return this.graph;
    }

    public void add(final Vertex<Character> v1, final Vertex<Character> v2) {
        v1.setData('〇');
        v2.setData('〇');
        this.graph.add(v1, v2);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        if (this.colored) {
            sb.append(this.getClass().getSimpleName() + " based "
                    + "k-Coloring completed. A brief view of the graph is:\nEdges:\n    ");
        } else {
            sb.append("Not colored yet. A brief view of the graph is:\nEdges:\n    ");
        }

        for (final Edge<Character> cursor : this.graph.getEdges()) {
            sb.append("Edge(V" + cursor.getV1().getIndex() + ", V" + cursor.getV2().getIndex() + ") ");
        }
        sb.append("\nVertices:\n    ");

        final List<Vertex<Character>> list = this.graph.getVertices();
        // Sort the vertices by index ordered. More comprehensible to see and debug.
        list.sort(new VertexIndexComparator<Character>());

        for (final Vertex<Character> cursor : list) {
            sb.append("V" + cursor.getIndex() + "(" + cursor.getData() + ") ");
        }
        return sb.toString();
    }

}
