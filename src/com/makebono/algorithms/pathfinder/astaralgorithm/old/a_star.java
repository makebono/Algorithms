package com.makebono.algorithms.pathfinder.astaralgorithm.old;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class a_star {
    public static void main(final String[] args) throws FileNotFoundException {
        final Scanner data = new Scanner(new File("input.txt"));
        Scanner scan;
        String string = new String();
        final ArrayList<node> G = new ArrayList<node>();

        for (int i = 0; i < 2001; i++) {
            string = data.nextLine();
            string = string.replace(":", " ");
            string = string.replace(",", " ");
            scan = new Scanner(string);

            if (i < 1000) {
                scan.nextDouble();
                G.add(new node(i + 1, scan.nextDouble(), scan.nextDouble()));
            }

            if (i > 1000) {
                scan.nextInt();
                while (scan.hasNext()) {
                    G.get(i - 1001).setAdjacent(G.get(scan.nextInt() - 1));
                }
            }
        }

        data.close();

        int start;
        int target;

        final Scanner input = new Scanner(System.in);
        System.out.print("Enter your start node: ");
        start = input.nextInt();
        System.out.println();
        System.out.print("Enter your destination: ");
        target = input.nextInt();
        System.out.println();
        input.close();

        final node s = G.get(start - 1);
        System.out.println(s.getLabel());

        final node t = G.get(target - 1);
        System.out.println(t.getLabel());
        // read s and t from keyboard

        for (int i = 0; i < 1000; i++) { // assign initial value of dist and est
            G.get(i).setDist(Double.POSITIVE_INFINITY);
            G.get(i).setEst(Double.POSITIVE_INFINITY);
        }

        s.setDist(0);
        s.setEst(0);
        final node temp = new node(0, 0, 0);
        node u = temp;
        node v;
        final estCompare comp = new estCompare();
        final PriorityQueue<node> q = new PriorityQueue<node>(1000, comp); // the priority queue

        for (int i = 0; i < 1000; i++) {
            q.add(G.get(i));
        }

        System.out.println("The path is:");
        while (q.size() != 0 && u.getLabel() != t.getLabel()) {
            u = q.poll();
            System.out.print(u.getLabel()); // print current node

            int i = 0;
            while (i < u.countEdges()) {
                v = u.getEdge(i);

                if (q.contains(v) != true) {} // if v is already removed from the queue, ignore it
                else if (v.countEdges() == 1 && v.getLabel() != t.getLabel()) { // if v has only one edge and not the
                                                                                // destination, ignore it
                    q.remove(v);
                } else {
                    if (v.getDist() > u.getDist() + u.distTo(v)) {

                        v.setDist(u.getDist() + u.distTo(v));
                        q.remove(v); // decreaseKey, push it to the top
                        v.setEst(v.getDist() + v.distTo(t));
                        // System.out.println(v.getEst());
                        q.add(v);

                        // System.out.println(u.getLabel());
                        // System.out.println(v.getLabel());
                    }
                }

                i++;
            }

            if (u.getLabel() == t.getLabel()) { // target reached

                q.removeAll(q);
            } else {
                System.out.print(" -> ");
            }
        }
        System.out.print("\nThe distance is: ");
        System.out.println(t.getDist());
    }

    static class estCompare implements Comparator<node> {

        public int compare(final node N1, final node N2) {
            if (N1.getEst() > N2.getEst()) {
                return 1;
            }
            if (N1.getEst() < N2.getEst()) {
                return -1;
            }
            return 0;
        }

    };

}
