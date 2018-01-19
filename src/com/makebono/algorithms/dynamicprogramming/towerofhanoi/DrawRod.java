/** It's funny.
 */

package com.makebono.algorithms.dynamicprogramming.towerofhanoi;

import java.util.ArrayList;

import com.makebono.algorithms.dynamicprogramming.towerofhanoi.TowerOfHanoi.rod;

/** 
 * @ClassName: DrawRod 
 * @Description: Class for disk and rod drawing, a good practice for playing with string. 
 * @author makebono
 * @date 2017年11月6日 上午8:42:42 
 *  
 */
public class DrawRod {
    public static String drawDisk(final int size, final int location) {
        final StringBuilder sb = new StringBuilder();

        // Disk exists or not, arrange space for it first.
        sb.setLength(2 * (location + 1) + 2);

        // If no disk here, return a stream of space.
        if (size == 0) {
            // System.out.println(" Blank sb's length: " + sb.length());
            return sb.toString();

        } else {
            int i = location + 1;
            int n = location + 2;
            int counter = 0;
            // Print the disk from middle to both sides, use 2x size for easy management .
            while (counter < size) {
                sb.setCharAt(i, '=');
                sb.setCharAt(n, '=');
                i--;
                n++;
                counter++;
            }
            sb.setCharAt(i, '[');
            sb.setCharAt(n, ']');
            return sb.toString();
        }
    }

    public static String drawRods(final ArrayList<rod> rods) {
        final StringBuilder sb = new StringBuilder();
        final int maxDiskSize = rods.get(0).getHeight() + rods.get(1).getHeight() + rods.get(2).getHeight();

        int max = rods.get(0).getHeight();
        for (int i = 1; i < 3; i++) {
            if (max < rods.get(i).getHeight()) {
                max = rods.get(i).getHeight();
            }
        }

        for (int i = max - 1; i >= 0; i--) {
            if (rods.get(0).getHeight() > i) {
                sb.append(drawDisk(rods.get(0).getDisk().get(i), maxDiskSize - 1));
                sb.append("          ");
            } else {
                sb.append(drawDisk(0, maxDiskSize - 1));
                sb.append("          ");

            }

            if (rods.get(1).getHeight() > i) {
                sb.append(drawDisk(rods.get(1).getDisk().get(i), maxDiskSize - 1));
                sb.append("          ");

            } else {
                sb.append(drawDisk(0, maxDiskSize - 1));
                sb.append("          ");
            }

            if (rods.get(2).getHeight() > i) {
                sb.append(drawDisk(rods.get(2).getDisk().get(i), maxDiskSize - 1));
            } else {
                // This is not mandantory but in case of encoding issues, it's better to be added here.
                sb.append(drawDisk(0, maxDiskSize - 1));
                sb.append("          ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
