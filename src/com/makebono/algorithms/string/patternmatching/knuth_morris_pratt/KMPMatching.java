/**Note: 
 * Knuth-Morris-Pratt algorithm for seaching and mapping multiple labels and items. Faster than force machting.
 * It takes o(m) times for generating a reference table to help the matching. This pi table cotains information
 * about if a pattern failed to match at some position. What's the next shortest possible match from anylazing 
 * the item itself.
 * 
 *  For example a Pi table for ababaca is:
 *  p[i]     a b a b a c a
 *  pi[i] =  0 0 1 2 3 0 1
 *  
 *  0 means no possible match for shift within p.length.
 *   
 *  Matching part takes around o(n) * [(at most o(m-1)) -> for while loop at line 59 down below.] 
 */
package com.makebono.algorithms.string.patternmatching.knuth_morris_pratt;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.HashMap;

import com.makebono.algorithms.string.patternmatching.Matching;

/** 
 * @ClassName: KMPMatching 
 * @Description: Search a input String by its pattern from a text.
 * @author makebono
 * @date 2017年12月14日 下午2:00:38 
 *  
 */
public class KMPMatching extends Matching {
    private int[][] piTables;

    public KMPMatching(final String location) throws FileNotFoundException {
        super(location);
    }

    @Override
    public void init(final String... targets)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        final StringBuilder temp = this.text;
        Field f = temp.getClass().getSuperclass().getDeclaredField("value");
        f.setAccessible(true);
        this.ttext = (char[]) f.get(temp);

        f = String.class.getDeclaredField("value");
        f.setAccessible(true);

        this.ttargets = new char[targets.length][];
        this.piTables = new int[targets.length][];
        int i = 0;
        for (final String target : targets) {
            this.piTables[i] = computePrefix(target);
            this.ttargets[i++] = (char[]) f.get(target);;
        }
    }

    @Override
    public HashMap<String, String> paragraph(final String... labels) {
        final HashMap<String, String> result = new HashMap<String, String>();
        final int m = labels.length;
        final int[] index = new int[m];
        int i = 0;
        int startFrom = 0;
        while (i < m) {
            index[i] = this.match(this.ttargets[i], startFrom, this.piTables[i]);
            startFrom = index[i];
            // System.out.println(index[i]);

            // System.out.println(index);
            if (i++ == 0) {
                continue;
            } else {
                result.put(labels[i - 2],
                        this.text.substring(index[i - 2] + this.ttargets[i - 2].length, index[i - 1]));
            }

        }
        result.put(labels[i - 1], this.text.substring(index[i - 1] + this.ttargets[i - 1].length));

        return result;
    }

    public int match(final char[] input, final int[] piTable) {
        return this.match(input, 0, piTable);
    }

    // Search for single target
    public int match(final char[] input, final int start, final int[] piTable) {
        final int n = this.ttext.length;
        final int m = input.length;

        int q = -1;
        for (int i = start; i < n; i++) {
            while (q > 0 && input[q + 1] != this.ttext[i]) {
                q = piTable[q];
            }

            if (input[q + 1] == this.ttext[i]) {
                q = q + 1;
            }

            if (q == m - 1) {
                return i - m + 1;
            }
        }

        return -1;
    }

    // Calculate prefix function of sub pattern p(k): p(1...k...label.length), p[k] = n means the longest proper prefix
    // to be the longest proper suffix of p[k] is p[n].
    // Example shows in header notes of this class above.
    public static int[] computePrefix(final String label) {
        // System.out.println("boo!");
        final int[] pi = new int[label.length()];
        pi[0] = 0;
        int k = 0;
        for (int i = 1; i < label.length(); i++) {
            while ((k > 0) && label.charAt(k) != label.charAt(i)) {
                k = pi[k - 1];
            }

            if (label.charAt(k) == label.charAt(i)) {
                k++;
            }

            pi[i] = k;
        }
        return pi;
    }
}
