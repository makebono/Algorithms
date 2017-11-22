/** Instruction:
 *     final AStarPathFinder ASPF = new AStarPathFinder("astarinput.txt");
 *     final ArrayList<Node> list = ASPF.findPath(97, 791); 
 */
package com.makebono.algorithms.pathfinder.astaralgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

import com.makebono.algorithms.tools.astarcomparators.FComparator;

/** 
 * @ClassName: AStarPathFinder 
 * @Description: A* algorithm
 * @author makebono
 * @date 2017年11月1日 上午9:47:01 
 *  
 */
public class AStarPathFinder {
    // nodes describes the map
    private final ArrayList<AStarNode> nodes;
    // open includes next available nodes, use priority queue to poll the next best choice
    private final PriorityQueue<AStarNode> open;
    // close contains nodes already been visited, erase the probability of cycle-back visits
    private final ArrayList<AStarNode> close;

    // Read the input and initialize the map.
    public AStarPathFinder(final String location) throws FileNotFoundException {
        final FComparator fCompare = new FComparator();
        nodes = new ArrayList<AStarNode>();
        open = new PriorityQueue<AStarNode>(1000, fCompare);
        close = new ArrayList<AStarNode>();

        final Scanner data = new Scanner(new File(location));
        Scanner scan;
        String string = new String();

        for (int i = 0; i < 2001; i++) {
            string = data.nextLine();
            string = string.replace(":", " ");
            string = string.replace(",", " ");
            scan = new Scanner(string);
            // First 1000 lines contain the location info of each node
            if (i < 1000) {
                scan.nextDouble();
                nodes.add(new AStarNode(i + 1, scan.nextDouble(), scan.nextDouble()));
            }
            // Following by another 1000 lines contain the edges between nodes.
            if (i > 1000) {
                scan.nextInt();
                while (scan.hasNext()) {
                    nodes.get(i - 1001).addChild(nodes.get(scan.nextInt() - 1));
                }
            }
        }

        data.close();
    }

    // I prefer this.get... manipulation which looks awesome somehow, and easier to debug at main class else where
    public ArrayList<AStarNode> getNodes() {
        return this.nodes;
    }

    public PriorityQueue<AStarNode> getOpen() {
        return this.open;
    }

    public ArrayList<AStarNode> getClose() {
        return this.close;
    }

    public ArrayList<AStarNode> findPath(final int start, final int target) {
        final AStarNode begin = this.getNodes().get(start - 1);
        final AStarNode end = this.getNodes().get(target - 1);

        // Starting place has purely heuristic f score and 0 as g score
        begin.setGScore(0);
        begin.setFScore(begin.getEuclideangScore(end));

        // Put starting point into open list, which is the only available point as for now.
        this.getOpen().add(begin);
        AStarNode current;

        // Until no available next move left
        while (!this.getOpen().isEmpty()) {
            // Poll the best next move out of the priority queue, depends on which node has the lowest cost
            current = this.getOpen().poll();
            // System.out.println(current.getId());

            // Destination reached
            if (current == end) {
                final ArrayList<AStarNode> path = this.reconstructPath(current.getParent(), current);
                // System.out.println(path.get(1).getGScore());
                this.printPath(path);
                return path;
            }

            // Pop current node out of the open list and insert it into the close list
            this.getOpen().remove(current);
            this.getClose().add(current);

            // Traverse through all children of current node.
            for (final AStarNode temp : current.getChildren()) {

                // If it already in the close list, skip it.
                if (this.getClose().contains(temp)) {
                    continue;
                }

                // Put it into the open list if it's not in there.
                if (!this.getOpen().contains(temp)) {
                    this.getOpen().add(temp);
                }

                // G score for current visiting child node.
                final double tempGScore = current.getGScore() + temp.getEuclideangScore(current);

                // System.out.println(current.getId());
                // System.out.println(temp.getId());
                // System.out.println(temp.getEuclideangScore(current));

                // If this child has already been traversed and has a lower g score by another path, ignore this path
                if (tempGScore > temp.getGScore()) {
                    continue;
                }

                // System.out.println("temp: " + temp.getId());
                // System.out.println("current: " + current.getId());

                // Update the g score from this visit as the new best choice
                temp.addParent(current);
                temp.setGScore(tempGScore);
                temp.setFScore(tempGScore + temp.getEuclideangScore(end));
            }
        }
        System.out.println("Cannot find the path.");
        return null;
    }

    // Build the path by reversely go back from destination to the beginning point.
    public ArrayList<AStarNode> reconstructPath(final PriorityQueue<AStarNode> parents, final AStarNode node) {
        final ArrayList<AStarNode> path = new ArrayList<AStarNode>();

        // If there's no parent node, means this node is the first and only node in the path, or say starting point is
        // same as destination
        if (parents.size() == 0) {
            path.add(node);
            return path;
        }

        // Add current node in the list.
        AStarNode current = node;
        path.add(current);

        // Traverse parents until reached the root(starting point).
        while (current.getParent().size() != 0) {
            // Use priority queue to get the best parent here.
            current = current.getParent().poll();
            path.add(current);
        }

        // Reverse the path and it's finished.
        Collections.reverse(path);
        return path;
    }

    public void printPath(final ArrayList<AStarNode> path) {
        if (path.size() == 0) {
            System.out.println("Path finding failed.");
        }

        final StringBuilder sb = new StringBuilder();
        sb.append("Success on path finding, the result is:");

        for (int i = 0; i < path.size(); i++) {
            if (i % 10 == 0) {
                sb.append("\n    ");
            }
            sb.append("node");
            sb.append(path.get(i).getId());
            if (i != path.size() - 1) {
                sb.append(" -> ");
            }
        }
        System.out.println(sb.toString() + "\n\nLength is: " + path.get(path.size() - 1).getGScore());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("This data set has nodes:");
        for (int i = 0; i < this.getNodes().size(); i++) {
            if (i % 10 == 0) {
                sb.append("\n    ");
            }
            sb.append("node");
            sb.append(this.getNodes().get(i).getId());
            if (this.getNodes().get(i).getId() != this.getNodes().size()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
};
