/**Notes:
 * This is an easy but practically useful algorithm, and funny to implement. It takes o(n^3) time complexity for solving problem.
 * A 2-D matrix is used for storing the input time cost. Nothing special about the matrix. Each row is considered as worker, column
 * is time cost for worker per job. Number of job should equal to number of worker. And if it's needed for implement a case when 
 * jobs less than number of worker, you need to assign dummy value at blank entry, this usually be the maximum cost. 
 * So now we have the matrix input, 'interpret' it following by the instruction(line drawing part), goes like below:
 * 
 *     1. Subtract miminum value from each row.
 *     2. Subtract minimum value from each column.
 *     3. Assign the jobs to each row. This means if a 0 appears, it is assignable. And if multiple 0s appears, pick an available
 *        entry and cross out other 0s in row. In each column, from top to bottom, if an entry is assigned above, entries below
 *        are also to be crossed out. If all rows assigned, it's the optimal solution.
 *     4. If not all rows assigned, tick every non assigned rows.
 *     5. Tick every column has 0 in ticked rows.
 *     6. Tick every row has assignment in ticked columns. Be careful, 'assignment', not 0s. So do not mess with corssed out 0s. 
 *     7. After all ticking step, draw lines at ticked columns and unticked rows. These lines will cover all 0s and the number of
 *        lines is minimum. Find minimum value in uncovered part and subtract it from every uncovered slot, add it to every entry
 *        covered by 2 lines.
 * 
 * In this implementation, I use a 2-D look-up table with 1s and 0s represents the lines. With help with two 1-D array of ticked 
 * rows and ticked columns.        
 * Repeat step 5~6 until no more new rows ticked, and do step 7. The table will have bunch of 0s represent the possible assignment.
 * May have multiple zero in a row. Then this table is ready to be threw in the solution tree.     
 *             
 * A tree is used for finding solution. In this particular tree, every node is possible assignment(0s) and every layer would be 
 * each row in the look-up table. Every sibling node in same layer is assignable column in a row in the look-up table. Every 
 * assignable slot in a row takes all assignable slots in next row as children. An example shows below.          
 *                       
 *                                                   Table:
 *                                                   [5,0,0
 *                                                    0,1,0
 *                                                    0,8,3]
 *                                                    
 *                                        Tree derived from table above:
 *                                                  (-1 , -1)  <---Dummy root. Index it anyway you want.
 *                                                   /      \
 *                                                  /        \
 *                                               (0,1)      (0,2)
 *                                                /\          /\
 *                                               /  \        /  \
 *                                            (1,0)(1,2)  (1,0)(1,2)
 *                                             /      \    /      \
 *                                          (2,0)   (2,0)(2,0)   (2,0)
 * 
 * You can do DFS or BFS or any search you like for the solution. If no conflict on the n value on a path from top down to bottom, 
 * it is an available solution. If there's no solution, do the line drawing part again.   
 * 
 * Instruction: 
 *     final HungarianWorkDistributor hwd = new HungarianWorkDistributor(4);
 *     hwd.solve();                                            
 */
package com.makebono.algorithms.hungarianalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.makebono.datastructures.hungariantree.HungarianNode;
import com.makebono.datastructures.hungariantree.HungarianTree;
import com.makebono.datastructures.matrix.matrix.BonoMatrix2D;
import com.makebono.datastructures.matrix.matrixinterface.Matrix;

/** 
 * @ClassName: HungarianWorkDistributor 
 * @Description: Assign work by Hungarian algorithm. 
 * @author makebono
 * @date 2017年11月20日 下午4:30:15 
 *  
 */
public class HungarianWorkDistributor {
    private final Matrix table;

    // Use it workerNum is to be equal as workNum, but I'm too lazy to change it.
    private int workerNum;
    private int workNum;
    private final int workerCapacity;
    private final int workCapacity;
    private final String[] workerList;
    private final String[] workList;

    public HungarianWorkDistributor(final int size) {
        this.table = new BonoMatrix2D(size, size);
        this.workerCapacity = size;
        this.workCapacity = size;
        this.workerList = new String[workerCapacity];
        this.workList = new String[workCapacity];
        this.workerNum = 0;
        this.workNum = 0;

        // Initialize the matrix.
        this.situationWizard();
    }

    public void solve() {
        // Get a clone table for mess around with. Because you don't want change the original data. They are needed for
        // output calculation.
        final int[][] temp = this.getTable().cloneMatrix();

        // 1. Subtract miminum value from each row.
        for (int i = 0; i < temp.length; i++) {
            int tempMin = temp[i][0];
            for (final int buffer : temp[i]) {
                if (tempMin > buffer) {
                    tempMin = buffer;
                }
            }
            for (int o = 0; o < temp[i].length; o++) {
                temp[i][o] -= tempMin;
            }
        }

        // 2. Subtract miminum value from each column.
        for (int i = 0; i < temp[0].length; i++) {
            int tempMin = temp[0][i];
            for (int o = 0; o < temp[0].length; o++) {
                if (tempMin > temp[o][i]) {
                    tempMin = temp[o][i];
                }
            }
            for (int o = 0; o < temp[0].length; o++) {
                temp[o][i] -= tempMin;
            }
        }

        /*
        for (int i = 0; i < temp.length; i++) {
            for (final int cursor : temp[i]) {
                System.out.print(cursor + " ");
            }
            System.out.println();
        }
        */

        // Throw the table in tree to see if there's solution.
        boolean optimized = false;
        HungarianTree solution = new HungarianTree(temp.length);
        solution.buildTree(temp);
        ArrayList<HungarianNode> answer = solution.findSolution();
        optimized = answer.size() != 0;

        // System.out.println(answer.size());

        /*
        System.out.println("Tree size: " + solution.size());     
        System.out.println("Answer size: " + answer.size());
        System.out.println("Optimal solution found. Shows below:");
        
        for (final HungarianNode cursor : answer) {
            System.out.println("(" + cursor.getM() + ", " + cursor.getN() + ")");
        }
        */

        // If not, do the drawing part, iterate until solution found.
        while (!optimized) {
            final int[][] draw = HungarianWorkDistributor.draw(temp);
            HungarianWorkDistributor.updateTempTable(temp, draw);

            /*
            for (int i = 0; i < temp.length; i++) {
                for (final int cursor : temp[i]) {
                    System.out.print(cursor + " ");
                }
                System.out.println();
            }
            */

            solution = new HungarianTree(temp.length);
            solution.buildTree(temp);
            answer = solution.findSolution();

            optimized = answer.size() != 0;
        }

        this.printAnswer(answer);
    }

    // Line drawing method.
    // Notice there's a role change for the int[][] draw table. Before drawing lines, It is for the dummy assignment
    // distribution, assigned entries in rows would be set to 1. And in the lines drawing part, this table becomes lines
    // representing table. 1 is stands for lines now, and 2 means such entry is covered by 2 lines.
    private static int[][] draw(final int[][] temp) {
        final int[][] draw = new int[temp.length][temp[0].length];
        boolean conflict = false;
        final int[] markedRow = new int[temp.length];
        final int[] markedCol = new int[temp[0].length];

        for (int i = 0; i < draw.length; i++) {
            Arrays.fill(draw[i], 0);
        }

        Arrays.fill(markedCol, 1);
        Arrays.fill(markedRow, 0);

        boolean newRowMarked = false;

        // Scan through the matrix and check assignable work from top to bottom. Avoid conflict and mark
        // unassigned rows.
        for (int i = 0; i < temp.length; i++) {
            conflict = false;
            for (int o = 0; o < temp[0].length; o++) {

                if (temp[i][o] == 0) {
                    // Looking for conflict.
                    for (int p = 0; p < i; p++) {
                        if (temp[p][o] == 0 && draw[p][o] == 1) {
                            conflict = true;
                        }
                    }

                    if (!conflict) {
                        draw[i][o] = 1;
                        break;
                    }
                }
            }
            if (conflict) {
                markedRow[i] = 1;
                newRowMarked = true;
            }
        }

        // Do this until no new row ticked.
        while (newRowMarked) {
            newRowMarked = false;
            final int[] tempRowMarked = Arrays.copyOf(markedRow, markedRow.length);
            /*
            for (int i = 0; i < markedRow.length; i++) {
                System.out.println(markedRow[i]);
            }
            
            for (int i = 0; i < draw.length; i++) {
                for (int o = 0; o < draw[0].length; o++) {
                    System.out.print(draw[i][o] + " ");
                }
                System.out.println();
            }
            */

            // Scan through the unassigned rows and try to mark relative columns.
            for (int i = 0; i < markedRow.length; i++) {
                if (markedRow[i] == 1) {
                    for (int o = 0; o < markedCol.length; o++) {
                        if (temp[i][o] == 0) {
                            markedCol[o] = 0;
                        }
                    }
                }
            }

            /*
            for (int i = 0; i < markedCol.length; i++) {
                System.out.println(markedCol[i]);
            }
            */

            // Mark rows having assignments in columns marked above.
            for (int o = 0; o < markedCol.length; o++) {
                if (markedCol[o] == 0) {
                    for (int i = 0; i < markedRow.length; i++) {
                        if (temp[i][o] == 0 && draw[i][o] == 1) {
                            markedRow[i] = 1;
                        }
                    }
                }
            }

            /*
            for (int i = 0; i < markedRow.length; i++) {
                System.out.println(markedRow[i]);
            }
            for (int i = 0; i < markedRow.length; i++) {
                System.out.println(markedCol[i]);
            }
            */

            if (!Arrays.equals(markedRow, tempRowMarked)) {
                newRowMarked = true;
                // System.out.println("boo!");
            }
        }

        // These steps finished the ticking part. Below goes the drawing part.
        for (int i = 0; i < draw.length; i++) {
            Arrays.fill(draw[i], 0);
        }

        // Draw lines horizontally.
        for (int i = 0; i < draw.length; i++) {
            if (markedRow[i] == 0) {
                Arrays.fill(draw[i], 1);
            }
        }

        // Draw lines vertically.
        for (int o = 0; o < draw[0].length; o++) {
            if (markedCol[o] == 0) {
                for (int i = 0; i < draw.length; i++) {
                    draw[i][o] += 1;
                }
            }
        }

        return draw;
    }

    // Return minimum value in uncovered(by lines) region.
    private static int unselectedRegionMin(final int[][] table, final int[][] draw) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < table.length; i++) {
            for (int o = 0; o < table[0].length; o++) {
                if (draw[i][o] == 0 && table[i][o] < min) {
                    min = table[i][o];
                }
            }
        }

        return min;
    }

    // Use the line drawing table to update the original table following by instruction.
    private static void updateTempTable(final int[][] table, final int[][] draw) {
        final int unselectedRegionMin = HungarianWorkDistributor.unselectedRegionMin(table, draw);

        for (int i = 0; i < table.length; i++) {
            for (int o = 0; o < table[0].length; o++) {
                if (draw[i][o] == 0) {
                    table[i][o] -= unselectedRegionMin;
                }

                // draw[i][o] == 2 means covered by 2 lines.
                if (draw[i][o] == 2) {
                    table[i][o] += unselectedRegionMin;
                }
            }
        }
    }

    public Matrix getTable() {
        return this.table;
    }

    public int getWorkerNum() {
        return this.workerNum;
    }

    public int getWorkNum() {
        return this.workNum;
    }

    public String[] getWorkerList() {
        return this.workerList;
    }

    public String[] getWorkList() {
        return this.workList;
    }

    private void addWorker(final String name) {
        if (this.workerNum < this.workerCapacity) {
            this.workerList[this.workerNum] = name;
            this.workerNum++;
        }
    }

    private void addWork(final String name) {
        if (this.workNum < this.workCapacity) {
            this.workList[this.workNum] = name;
            this.workNum++;
        }
    }

    private void situationWizard() {
        final Scanner sc = new Scanner(System.in);
        System.out.printf("Wizard for situation set-up.\nPlease enter your workers' names");

        while (this.workerNum != this.workerCapacity) {
            System.out.printf("\n    Input your next worker's name: ");
            this.addWorker(sc.nextLine());
        }

        System.out.printf("\n\nPlease enter your jobs' names");
        while (this.workNum != this.workCapacity) {
            System.out.printf("\n    Input your next job's name: ");
            this.addWork(sc.nextLine());
        }

        System.out.printf("\n\nPlease enter the costs of works for each worker");
        for (int i = 0; i < this.workerCapacity; i++) {
            for (int o = 0; o < this.workCapacity; o++) {
                System.out.printf("\n    Input the cost of " + this.getWorkerList()[i] + " doing "
                        + this.getWorkList()[o] + ": ");
                this.getTable().set(i, o, sc.nextInt());
            }
        }

        sc.close();
    }

    private void printAnswer(final ArrayList<HungarianNode> answer) {
        System.out.println(this.toString() + "\n");
        System.out.println("Optimal solution found. Shows below:");

        int cost = 0;
        for (int i = answer.size() - 1; i != -1; i--) {
            System.out.println(this.getWorkerList()[answer.get(i).getM()] + " does "
                    + this.getWorkList()[answer.get(i).getN()] + ".");
            cost += this.getTable().get(answer.get(i).getM(), answer.get(i).getN()).getValue();
        }
        System.out.println("The optimal cost is: " + cost);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Situation is like below: \n            ");
        for (int i = 0; i < this.workCapacity; i++) {
            final StringBuilder subSb = new StringBuilder();
            subSb.setLength(20);

            for (int n = 0; n < this.getWorkList()[i].length(); n++) {
                subSb.setCharAt(n, this.getWorkList()[i].charAt(n));
            }

            sb.append(subSb.toString());
        }
        for (int i = 0; i < this.workerCapacity; i++) {
            final StringBuilder subSb = new StringBuilder();
            subSb.setLength(8);

            for (int n = 0; n < this.getWorkerList()[i].length(); n++) {
                subSb.setCharAt(n, this.getWorkerList()[i].charAt(n));
            }
            sb.append("\n    ");
            sb.append(subSb.toString());
            for (int n = 0; n < this.workCapacity; n++) {
                sb.append("         " + this.getTable().get(i, n).getValue() + "         ");
            }
        }

        return sb.toString();
    }
}
