/**Note:
 * Following Welsh-Powell's algorithm, it's very sound and clear on idea and easy on implementation. Consider following steps:
 * 1. Sort vertices by their degree(in and out) on descending order. Let k = 0.
 * 2. Get the next uncolored candidate, assign k-th color from color table to it. 
 * 3. Find vertices which are not attached to newly colored vertices, color it with k-th color as well.
 * 4. k++, repeat step 2 and 3 until all vertices colored.
 * 
 * Instruction in KCDemo.java
 */
package com.makebono.algorithms.graphalgorithms.kcoloring;

import java.util.ArrayList;
import java.util.List;

import com.makebono.datastructures.graph.Edge;
import com.makebono.datastructures.graph.Vertex;
import com.makebono.datastructures.tools.graphcomparator.VertexDegreeComparator;

/** 
 * @ClassName: WelshPowellKColoringSolver 
 * @Description: Solver for k-Coloring problem, use char number a, b, c... to indicate the color. 〇 means no color.
 * @author makebono
 * @date 2018年1月16日 上午8:58:19 
 *  
 */
public class WelshPowellKColoringSolver extends AbstractKColoringPortal {
    public WelshPowellKColoringSolver() {
        this.sidekick = new VertexDegreeComparator<Character>();
    }

    @Override
    public void dealWithIt() {
        boolean colorAssigned = false;
        this.k = 0;

        final ArrayList<Vertex<Character>> vertices = this.graph.getVertices();
        // Sort the vertices list by descending order on degree.
        vertices.sort(this.sidekick);
        // System.out.println(vertices);
        final int size = vertices.size();

        for (int i = 0; i < size; i++) {
            colorAssigned = false;
            final Vertex<Character> cursor = vertices.get(i);
            final List<Vertex<Character>> coloredVertices = new ArrayList<Vertex<Character>>();

            if (cursor.getData() == '〇') {
                // System.out.println("Boo! New color assigned for " + cursor);
                cursor.setData(Character.toChars(a + this.k)[0]);
                colorAssigned = true;
                coloredVertices.add(cursor);
            }

            for (int n = i + 1; n < size; n++) {
                final Vertex<Character> sameColorCandidate = vertices.get(n);
                // System.out.println(cursor + " with " + sameColorCandidate);
                boolean magicalTokarev = true;

                for (final Vertex<Character> tempCursor : coloredVertices) {
                    for (final Edge<Character> edges : tempCursor.getEdges()) {
                        final Vertex<Character> child = edges.getV2();
                        if (child == sameColorCandidate) {
                            magicalTokarev = false;
                            // No need to continue while same color candidate found.
                            break;
                        }
                    }
                }

                if (magicalTokarev) {
                    // System.out.println(sameColorCandidate);
                    if (sameColorCandidate.getData() == '〇') {
                        sameColorCandidate.setData(Character.toChars(a + this.k)[0]);
                        coloredVertices.add(sameColorCandidate);
                    }
                }
            }

            if (colorAssigned) {
                this.k++;
            }
        }

        this.colored = true;
    }

    /* 
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Vertex<Character> cursor : this.graph.getVertices()) {
            sb.append("V" + cursor.getIndex() + "(" + cursor.getData() + ", " + cursor.getDegree() + ") ");
        }
        return super.toString() + "\n    " + sb.toString();
    }
    */
}
