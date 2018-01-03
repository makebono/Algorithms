/**Notes:
 * Pick closest pair out of a graph. Brute force implementation compares n choose 2 times so it takes n*(n-1)/2 steps,
 * which is in bound of O(n^2). It takes an awefully lot of times when n grows high.
 * An optimized solution here is a divide and conquer approach. It divides the graph in half along x-axis, pre-sort all
 * vertices along x and y axis for once for further using.
 * After separating the graph, it recursively does the separation until hitting base case. Which is |n| <= 3, and then
 * invoke the brute force comparison. Now we have 2 minimums from right and left half, one of them might be the global
 * minimum. Although there's another candidate, it could be a pair of vertex accros the boundary. In such case, it must
 * be lay in this region:
 *                                          region r = xm +/- md
 *         Which xm is position of the 'line' separates the graph, md is minimum out of left and right half.
 *         
 * And there is another property in this region. Using array y' of vertices sorted along y axis, in this array, to find
 * the minimum we only need to compare 7 following vertices after a vertex p0, see the example below:
 *                                        .         ....xm....
 *                                        .              | 
 *                                        . (p0)-----(p1)|(p2)------(p3)
 *                                        .  |           |            |
 *                                       md  |           |            |
 *                                        .  |           |            |
 *                                        . (p7)-----(p6)|(p5)------(p4)
 *                                        .       md     |
 *                                                       |
 * This exmaple is an extreme case when distance between pn and pn+1(except p1,p2 p5,p6, in this case they hold the 
 * global minimum) are equally md. Since md is the minimum in both halves, there will not be more than 8 vertices 
 * in this region formed by 2 squares with side length md like in this case. So we only need to compare 8 vertices 
 * each iteration.
 * After this comparison, we get the minimum in region accross line xm, compare it to md to find the global minimum. 
 * This algorithm takes T(n) = 2T(n/2) + O(n) = O(nlgn) times.
 * 
 * Instruction:
 *     final ClosestPair<Integer> cp = new ClosestPair<Integer>();
 *     final Random rnd = new Random();
 *     for (int i = 0; i < 200000; i++) {
 *         cp.add(i + 1, 0, rnd.nextDouble() * 10, rnd.nextDouble() * 10);
 *     }
 *     cp.init();
 *     final Vertex<Integer>[] result = cp.bruteForcePickClosestPair();
 *     final Vertex<Integer>[] result2 = cp.closestPair();
 */
package com.makebono.algorithms.computationalgeometry.closestpointpair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.makebono.datastructures.graph.BonoGraph;
import com.makebono.datastructures.graph.Vertex;
import com.makebono.datastructures.graph.graphInterface.Graph;
import com.makebono.datastructures.tools.graphcomparator.GraphComparatorX;
import com.makebono.datastructures.tools.graphcomparator.GraphComparatorY;

/** 
 * @ClassName: ClosestPair 
 * @Description: A short program finding closest pair of vertices in a graph
 * @author makebono
 * @date 2017年12月21日 下午2:52:47 
 *  
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class ClosestPair<T> {
    private final Graph<T> graph;
    // Vertices sorted along x axis
    private final List<Vertex<T>> x;
    // Vertices sorted along y axis
    private final List<Vertex<T>> y;
    private final GraphComparatorX<T> sideKickX;
    private final GraphComparatorY<T> sideKickY;

    public ClosestPair() {
        this.graph = new BonoGraph<T>();
        this.x = new ArrayList<Vertex<T>>();
        this.y = new ArrayList<Vertex<T>>();
        this.sideKickX = new GraphComparatorX<T>();
        this.sideKickY = new GraphComparatorY<T>();
    }

    // Sorting for optimized solution. Call before invoke optimized method.
    public void init() {
        this.x.addAll(this.getGraph().getVertices());
        this.y.addAll(this.getGraph().getVertices());
        this.x.sort(sideKickX);
        this.y.sort(sideKickY);
    }

    public Vertex<T>[] closestPair() {
        return this.closestPair(this.x, this.y);
    }

    private Vertex<T>[] closestPair(final List<Vertex<T>> x, final List<Vertex<T>> y) {
        if (x.size() <= 3) {
            return this.closest(x);
        }
        final int size = x.size();
        final int l = size / 2;
        final List<Vertex<T>> xl = x.subList(0, l);
        final List<Vertex<T>> xr = x.subList(l, size);
        final List<Vertex<T>> yl = new ArrayList<Vertex<T>>();
        final List<Vertex<T>> yr = new ArrayList<Vertex<T>>();

        // Use a 'vertical line' to divide the graph in half. Every vertex on or at right side of the line considered to
        // be on right half.
        final double xm = x.get(l).getX();

        for (int i = 0; i < size; i++) {
            if (y.get(i).getX() < xm) {
                yl.add(y.get(i));
            } else {
                yr.add(y.get(i));
            }
        }

        final Vertex<T>[] minL = this.closestPair(xl, yl);
        final Vertex<T>[] minR = this.closestPair(xr, yr);
        Vertex<T>[] min;

        final double dl = minL[0].dist(minL[1]);
        final double dr = minR[0].dist(minR[1]);
        double md = 0;

        // Pick minimum out of both sides.
        if (dl <= dr) {
            min = minL;
            md = dl;
        } else {
            min = minR;
            md = dr;
        }

        // Medium region
        final double xlr = xm - md;
        final double xrr = xm + md;
        int count = 0;
        final List<Vertex<T>> yym = new ArrayList<Vertex<T>>();

        for (int i = 0; i < size; i++) {
            if (y.get(i).getX() >= xlr && y.get(i).getX() <= xrr) {
                yym.add(y.get(i));
                count++;
            }
        }

        // If medium region has only 1 vertex, no need for comparison.
        if (count >= 2) {
            final Vertex<T>[] minM = this.regionalClosest(yym);

            // Now see if minimum from medium region is the global minimum.
            final double dm = minM[0].dist(minM[1]);
            if (dm <= md) {
                min = minM;
            }
        }

        return min;
    }

    public Vertex<T>[] bruteForcePickClosestPair() {
        return this.closest(this.graph.getVertices());
    }

    // Trivial method for finding closest pair in an array of vertices input. Takes O(n^2) for computing
    // (n chooes 2 comparison).
    private Vertex<T>[] closest(final List<Vertex<T>> input) {
        if (input.size() < 3) {
            return input.toArray(new Vertex[input.size()]);
        }
        Vertex<T>[] result = new Vertex[2];
        final HashMap<Double, Vertex<T>[]> map = new HashMap<Double, Vertex<T>[]>();

        double min = Double.MAX_VALUE;

        for (int i = 0; i < input.size() - 1; i++) {
            int n = i + 1;
            while (n < input.size()) {
                final double tempDist = input.get(i).dist(input.get(n));

                if (min > tempDist) {
                    min = tempDist;
                    map.put(min, new Vertex[] { input.get(i), input.get(n) });
                }
                n++;
            }
        }

        result = map.get(min);
        return result;
    }

    // Core method of the optimized algorithm. Notice it only compares 7 following vertices after a vertex p. Details
    // described above in notes section.
    public Vertex<T>[] regionalClosest(final List<Vertex<T>> input) {
        if (input.size() < 8) {
            return this.closest(input);
        }
        Vertex<T>[] result = new Vertex[2];
        final int size = input.size();
        final HashMap<Double, Vertex<T>[]> map = new HashMap<Double, Vertex<T>[]>();
        double min = Double.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            if (size - i >= 8) {
                final List<Vertex<T>> buffer = input.subList(i, i + 8);
                final Vertex<T>[] tempMin = this.closest(buffer);
                final double tempD = tempMin[0].dist(tempMin[1]);
                if (tempD <= min) {
                    min = tempD;
                }
                map.put(tempD, tempMin);

            } else {
                break;
            }
        }

        result = map.get(min);

        return result;
    }

    public Graph<T> getGraph() {
        return this.graph;
    }

    public void add(final int index, final T data, final double x, final double y) {
        final Vertex<T> newVertex = new Vertex<T>(index, data, x, y);
        this.graph.bulkAdd(newVertex);
    }

    public List<Vertex<T>> getX() {
        return this.x;
    }

    public List<Vertex<T>> getY() {
        return this.y;
    }

    public static void printVertices(final Vertex[] v) {
        for (final Vertex cursor : v) {
            System.out.println(cursor);
        }
        System.out.println("--------------------------------------------------------------------------");
    }
}
