/*Notes:
 * 1. The algorithm itself is straight forward, but output format takes times to handle with.
 * 2. The algorithm is: S(n,h,t) = S(n-1,h, not(h,t)) ; S(1,h,t) ; S(n-1,not(h,t),t). 
 * 3. n is the height, h is home rod while t is the target. Not(h,t) expressed as auxiliary rod here. Every step follows this instruction.
 * 3. Comlexity is 2^n - 1, and no table can do help here, because every state(step) would be different.
 * 4. Printing steps through recursion is hard to deal with, so use a set to erase repeated step would help. And LinkedHashSet can maintain the order of input, where TreeSet doesn't.  
 */
package com.makebono.algorithms.dynamicprogramming.towerofhanoi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Stack;

/** 
 * @ClassName: TowerOfHanoi 
 * @Description: DP solution for the Tower of Hanoi puzzle 
 * @author makebono
 * @date 2017年11月3日 上午11:56:45 
 *  
 */
public class TowerOfHanoi {
    private final int height;
    private ArrayList<rod> rods;
    private final LinkedHashSet<String> steps;

    public TowerOfHanoi(final int height) {
        this.height = height;
        final rod rod1 = new rod();
        final rod rod2 = new rod();
        final rod rod3 = new rod();
        steps = new LinkedHashSet<String>();

        // I don't allow different home rod than rod 1 here, and it's easy to change. Not necessary though.
        // Put disk from buttom to top, just use integer to represent disks.
        for (int i = height; i > 0; i--) {
            rod1.putDisk(i);;
        }

        rods = new ArrayList<rod>();
        rods.add(rod1);
        rods.add(rod2);
        rods.add(rod3);
    }

    public void setRod(final ArrayList<rod> rods) {
        this.rods = rods;
    }

    public int getHeight() {
        return this.height;
    }

    public ArrayList<rod> getRod() {
        return this.rods;
    }

    // Base case, move a smallest disk from home to target rod.
    public ArrayList<rod> baseCase(final ArrayList<rod> rod, final int home, final int target) {
        rod.get(target - 1).putDisk(rod.get(home - 1).removeDisk());
        return rod;
    }

    public ArrayList<rod> solve(final int size, final int home, final int target) {
        // If size = 1, means it reached base case
        if (size == 1) {
            this.setRod(baseCase(this.rods, home, target));
            // System.out.printf(this);
            return this.getRod();
        }

        // Decide the auxiliary rod here, thought about using Enum at first but doesn't seem necessary.
        int auxiliary = 0;

        for (int i = 1; i <= 3; i++) {
            if (i != home && i != target) {
                auxiliary = i;
            }
        }
        // S(n,h,t) = S(n-1,h, not(h,t)) ; S(1,h,t) ; S(n-1,not(h,t),t).
        // Algorithm goes here, beware of printing the steps, it will create lot of redundancies if not dealing with.

        // S(n-1,h, not(h,t))
        this.setRod(solve(size - 1, home, auxiliary));
        steps.add(this.toString());
        // System.out.printf(this);

        // S(1,h,t)
        this.setRod(solve(1, home, target));
        steps.add(this.toString());
        // System.out.printf(this);

        // S(n-1,not(h,t),t)
        this.setRod(solve(size - 1, auxiliary, target));
        steps.add(this.toString());
        // System.out.printf(this);

        return this.getRod();
    }

    // Users deploy the algorithm here. Higher tier will not possible to print so it will also generate output text
    // file.
    public void game(final int size, final int home, final int target) throws IOException {
        final StringBuilder result = new StringBuilder();
        result.append("Solution_Of_");
        result.append(this.getHeight());
        result.append("_tier.txt");
        try {
            final File output = new File(result.toString());
            final BufferedWriter writer = new BufferedWriter(new FileWriter(output));

            final StringBuilder stepFile = new StringBuilder();
            stepFile.append("Initial state: \n");
            stepFile.append(this.toString() + "\n\n\n");
            this.setRod(solve(size, home, target));

            int counter = 0;
            for (final String str : steps) {
                stepFile.append("\n******************************Step ");
                stepFile.append(++counter);
                stepFile.append(" ******************************\n");
                stepFile.append(str + "\n\n\n");
            }

            System.out.printf(stepFile.toString());
            writer.write(stepFile.toString());
            writer.close();
        }

        catch (final Exception e) {
            System.out.printf("Error occured, information: " + e.getMessage());
        }

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final StringBuilder blank = new StringBuilder();
        blank.setLength(this.getHeight() * 2);
        sb.append("\nRod1:");
        sb.append(blank.toString());
        sb.append("        ");
        sb.append("Rod2:");
        sb.append(blank.toString());
        sb.append("        ");
        sb.append("Rod3:\n");

        sb.append(DrawRod.drawRods(this.getRod()) + "\n");
        return sb.toString();
    }

    // Rod entity
    class rod {
        private final Stack<Integer> disk;
        private int rodHeight;

        rod() {
            this.rodHeight = 0;
            this.disk = new Stack<Integer>();
        }

        Stack<Integer> getDisk() {
            return this.disk;
        }

        int getHeight() {
            return this.rodHeight;
        }

        void increment() {
            this.rodHeight++;
        }

        void decrement() {
            this.rodHeight--;
        }

        // Don't forget to increase and decrease the height of rod through manipulations.
        void putDisk(final int disk) {
            increment();
            this.disk.push(disk);
        }

        int removeDisk() {
            decrement();
            return this.getDisk().pop();
        }
    }
}
