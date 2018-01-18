package com.makebono.algorithms.graphalgorithms.kcoloring;

import java.util.List;

import com.makebono.datastructures.graph.Edge;
import com.makebono.datastructures.graph.Vertex;
import com.makebono.datastructures.tools.graphcomparator.VertexIndexComparator;

/** 
 * @ClassName: BackTrackingKColoring 
 * @Description: K-Coloring problem by back-tracking
 * @author makebono
 * @date 2018年1月17日 上午8:57:39 
 *  
 */
public class BackTrackingKColoringSolver extends AbstractKColoringPortal {
    public BackTrackingKColoringSolver() {
        this.sidekick = new VertexIndexComparator<Character>();
    }

    @Override
    public void dealWithIt() {
        this.k = 0;
        final List<Vertex<Character>> vertices = this.graph.getVertices();
        // Sort vertices by index.
        vertices.sort(this.sidekick);
        // System.out.println(vertices);
        final int size = vertices.size();
        // Record the parents and children of each vertex.
        final int[][] parentTable = new int[size][size];
        final int[][] childTable = new int[size][size];

        // Initialize the parent and children table.
        for (final Vertex<Character> cursor : vertices) {
            for (final Edge<Character> edges : cursor.getEdges()) {
                parentTable[edges.getV2().getIndex() - 1][cursor.getIndex() - 1] = 1;
                childTable[cursor.getIndex() - 1][edges.getV2().getIndex() - 1] = 1;
            }
        }

        for (int i = 0; i < size; i++) {
            this.k = 0;
            final Vertex<Character> cursor = vertices.get(i);
            int n = 0;

            // Loop until a legit coloring is found.
            while (n < size) {
                // System.out.println("がおーココアさん、食べちゃいますよー");

                if (n == i) {
                    n++;
                } else {
                    cursor.setData(Character.toChars(a + this.k)[0]);
                    boolean magicalTokarev = false;

                    // Compare current candidate to it's children and parents. Back track on value of n if needed.
                    // Increase value of k by each failed coloring attempt.
                    if (parentTable[i][n] == 1) {
                        final Vertex<Character> parent = vertices.get(n);

                        if (cursor.getData() == parent.getData() && parent.getData() != '〇') {
                            n = 0;
                            k++;
                            magicalTokarev = true;
                            continue;
                        }
                    }

                    if (childTable[i][n] == 1) {
                        final Vertex<Character> child = vertices.get(n);

                        if (cursor.getData() == child.getData() && child.getData() != '〇') {
                            n = 0;
                            k++;
                            magicalTokarev = true;
                            continue;
                        }
                    }

                    // Currently the assigned color seems to be good, move on to next.
                    if (!magicalTokarev) {
                        n++;
                    }
                }
            }
        }
        this.colored = true;
    }
}
