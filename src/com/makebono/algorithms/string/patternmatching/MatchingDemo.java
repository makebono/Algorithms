package com.makebono.algorithms.string.patternmatching;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map.Entry;

import com.makebono.algorithms.string.patternmatching.bruteforcecomparison.BruteForceComparison;
import com.makebono.algorithms.string.patternmatching.knuth_morris_pratt.KMPMatching;

/** 
 * @ClassName: KMPDemo 
 * @Description: Demo of Knuth Morris Pratt's algorithm
 * @author makebono
 * @date 2018年1月16日 下午4:51:53 
 *  
 */
public class MatchingDemo {
    public static void showKMP() throws FileNotFoundException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        final KMPMatching kmpm = new KMPMatching("inputSet/output.txt");
        final String[] input = { "西安办事处", "郑州办事处", "江苏办事处", "山东办事处", "云南办事处", "宁夏办事处", "广州办事处" };
        kmpm.init(input);
        final HashMap<String, String> result = kmpm.paragraph(input);

        for (final Entry<String, String> cursor : result.entrySet()) {
            System.out.println(cursor.getKey());
            System.out.println(cursor.getValue());
        }
    }

    public static void showBruteForce() throws FileNotFoundException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        final BruteForceComparison bfm = new BruteForceComparison("inputSet/output.txt");
        final String[] input = { "西安办事处", "郑州办事处", "江苏办事处", "山东办事处", "云南办事处", "宁夏办事处", "广州办事处" };
        bfm.init(input);
        final HashMap<String, String> result = bfm.paragraph(input);

        for (final Entry<String, String> cursor : result.entrySet()) {
            System.out.println(cursor.getKey());
            System.out.println(cursor.getValue());
        }
    }
}
