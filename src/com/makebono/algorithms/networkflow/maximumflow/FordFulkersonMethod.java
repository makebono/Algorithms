/**Note:
 * Ford-Fulkerson method for calculating maximum flow, use Edmonds-Karp algorithm to find path(BFS) each iteration. In every iteration,
 * find a path from source to sink, doesn't have to be shortest path or any specific path. The path would be legit since the vertices 
 * chose have residual capacity to be distributed to And, for sure it's a simple path, which means cycle free. Once a path found, update
 * all vertices' flow by the least residual capacity in that path. Following below formula:
 *                         f(u,v) = f(u,v) + cf(p)     if edge u->v exists
 *                                = f(v,u) - cf(p)     if edge v->u exists
 *                         
 *                         Where cf(p) is the minimum residual capacity down the path.
 * Loop until no more path available to go, and there is the maximum network flow.
 * 
 * Instruction:
 *     final Vertex<String> s = new Vertex<String>(0, "Vancouver");
 *     final Vertex<String> v1 = new Vertex<String>(1, "Edmonton");
 *     final Vertex<String> v2 = new Vertex<String>(2, "Calgary");
 *     final Vertex<String> v3 = new Vertex<String>(3, "Saskatoon");
 *     final Vertex<String> v4 = new Vertex<String>(4, "Regina");
 *     final Vertex<String> t = new Vertex<String>(5, "Winnipeg");
 *     
 *     final FordFulkersonMethod ffm = new FordFulkersonMethod();
 *     ffm.add(s, v1, 16);
 *     ffm.add(s, v2, 13);
 *     ffm.add(v1, v3, 12);
 *     ffm.add(v2, v1, 4);
 *     ffm.add(v3, v2, 9);
 *     ffm.add(v2, v4, 14);
 *     ffm.add(v4, v3, 7);
 *     ffm.add(v3, t, 20);
 *     ffm.add(v4, t, 4);
 *     
 *     ffm.setSource(s);
 *     ffm.setSink(t);
 *     ffm.maximumFlow();
 */
package com.makebono.algorithms.networkflow.maximumflow;

import java.util.ArrayList;

import com.makebono.datastructures.graph.BonoGraph;
import com.makebono.datastructures.graph.Edge;
import com.makebono.datastructures.graph.Vertex;
import com.makebono.datastructures.graph.graphInterface.Graph;

/** 
 * @ClassName: FordFulkersonMethod 
 * @Description: Ford-Fulkerson method
 * @author makebono
 * @date 2017年11月24日 上午10:25:24 
 *  
 */
public class FordFulkersonMethod {
    private final Graph<String> residualGraph;

    public FordFulkersonMethod() {
        this.residualGraph = new BonoGraph<String>();
    }

    public ArrayList<Edge<String>> maximumFlow() {
        final ArrayList<Edge<String>> result = new ArrayList<Edge<String>>();

        ArrayList<Vertex<String>> path = this.getResidualGraph().bfsPath().getResult();

        /*
        for (final Vertex<String> cursor : path) {
            System.out.println(cursor);
        }
        */

        // Loop until no more augmenting path.
        while (path.size() != 0) {
            final ArrayList<Edge<String>> augmentingPath = new ArrayList<Edge<String>>();

            // Array for marking if e(u,v) or e(v,u) exists.
            final boolean[] isInverted = new boolean[path.size() - 1];

            for (int i = 0; i < path.size() - 1; i++) {
                final Vertex<String> v1 = path.get(i);
                final Vertex<String> v2 = path.get(i + 1);
                final Edge<String> temp = v1.getEdge(v2);

                // System.out.println(temp);

                if (temp != null) {
                    // Update residual capacity. Resudual capacity = Capacity - Flow. This method call will return a
                    // boolean value shows if e(u,v) or e(v,u) exists.
                    isInverted[i] = temp.setResidualCapacity(v1, v2);
                } else {
                    System.out.println("Unexpected error occurs.");
                    return null;
                }

                augmentingPath.add(temp);
            }

            int minResidualCapacity = augmentingPath.get(0).getResidualCapacity();
            // Looking for minimum residual capacity in augmenting path.
            for (final Edge<String> min : augmentingPath) {
                if (minResidualCapacity > min.getResidualCapacity()) {
                    minResidualCapacity = min.getResidualCapacity();
                }
            }

            // Update flow
            for (int i = 0; i < augmentingPath.size(); i++) {
                if (isInverted[i]) {
                    augmentingPath.get(i).setFlow(augmentingPath.get(i).getFlow() - minResidualCapacity);

                } else {
                    augmentingPath.get(i).setFlow(augmentingPath.get(i).getFlow() + minResidualCapacity);
                }

                // System.out.println(augmentingPath.get(i).getResidualCapacity());
            }
            // Look for new augmenting path.
            path = this.getResidualGraph().bfsPath().getResult();
        }

        result.addAll(this.getResidualGraph().getEdges());
        this.printMaximumFlow(result);
        return result;
    }

    public void add(final Vertex<String> v1, final Vertex<String> v2, final int capacity) {
        this.residualGraph.add(v1, v2, capacity, 0);
    }

    public Graph<String> getResidualGraph() {
        return this.residualGraph;
    }

    public Vertex<String> getSource() {
        return this.getResidualGraph().getSource();
    }

    public Vertex<String> getSink() {
        return this.getResidualGraph().getSink();
    }

    public void setSource(final Vertex<String> source) {
        this.getResidualGraph().setSource(source);
    }

    public void setSink(final Vertex<String> sink) {
        this.getResidualGraph().setSink(sink);
    }

    private void printMaximumFlow(final ArrayList<Edge<String>> result) {
        System.out.printf("Maximum flow of this network is:\n");
        for (final Edge<String> cursor : result) {
            System.out.printf("    Edge(%s, %d/%d)\n", cursor.getV1().getData() + " -> " + cursor.getV2().getData(),
                    cursor.getFlow(), cursor.getCapacity());
        }
        System.out.printf(
                "The source of network is: %s, the sink is: %s\nFollow the format: Edge(Start point -> destination, flow/capacity) each edge.",
                this.getSource().getData(), this.getSink().getData());
    }
}
