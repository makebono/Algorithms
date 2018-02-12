package com.makebono.algorithms.graphalgorithms.rochathatte;

import java.util.Arrays;
import java.util.List;

import com.makebono.datastructures.graph.BonoGraph;
import com.makebono.datastructures.graph.Edge;
import com.makebono.datastructures.graph.Vertex;

/** 
 * @ClassName: RochaThatteCycleDetection 
 * @Description: RochaThatteCycleDetection, skeleton for comlete later. 
 * @author makebono
 * @date 2018年2月12日 上午9:45:54 
 *  
 */
@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class RochaThatteCycleDetection<T> {
    public static List<Vertex> detect(final BonoGraph graph, final Vertex source) {
        graph.setSource(source);
        final int size = graph.size();
        final List<Vertex>[] receives = new List[size];
        final List<Vertex>[] sends = new List[size];
        final boolean[] halts = new boolean[size];
        Arrays.fill(halts, false);
        final List<Vertex> vertices = graph.getVertices();
        final Boolean detected = Boolean.FALSE;
        final int halted = 0;
        int i = 0;

        while (halted != size && detected) {
            if (i == 0) {

            }

            i++;
        }

        return null;
    }

    public void send(final Vertex v, final List<Vertex>[] receives) {
        v.getEdges().forEach(x -> {
            final List<Vertex> cursor = receives[((Edge) x).getV2().getIndex()];
            cursor.clear();

        });
    }
}
