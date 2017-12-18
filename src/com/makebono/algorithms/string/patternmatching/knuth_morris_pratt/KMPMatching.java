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
 *  
 *  It's still slower than using indexOf() building method. I must have missed something. Will be back for further
 *  tuning. 
 */
package com.makebono.algorithms.string.patternmatching.knuth_morris_pratt;

import java.io.FileNotFoundException;
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
    public KMPMatching(final String location) throws FileNotFoundException {
        super(location);
    }

    @Override
    public HashMap<String, String> paragraph(final String... labels) {
        final int[][] piTable = new int[labels.length][];
        final HashMap<String, String> result = new HashMap<String, String>();
        final int[] index = new int[labels.length];
        index[0] = 0;

        // Index for current label
        int i = 0;

        // Index for text
        int t = 0;

        // Index for Pi table
        int k = 0;
        boolean found = false;

        for (int n = 0; n < labels.length; n++) {
            piTable[n] = KMPMatching.computePrefix(labels[n]);
        }

        while (i < labels.length) {
            if (found) {
                k = 0;
                found = false;
            }

            while (labels[i].charAt(0) != this.text.charAt(t)) {
                t++;
            }

            while (!found) {
                while ((k > 0) && (labels[i].charAt(k) != this.text.charAt(t))) {
                    k = piTable[i][k - 1];
                }

                if (labels[i].charAt(k) == this.text.charAt(t)) {
                    // System.out.println("boo! " + k);
                    k++;
                }

                if (k == labels[i].length() - 1) {
                    found = true;
                    index[i] = t - labels[i].length() + 1;

                    if (i == 0) {
                        i++;
                    } else {
                        result.put(labels[i - 1],
                                this.text.substring(index[i - 1] + labels[i - 1].length() + 1, index[i] + 1));
                        i++;
                    }
                }
                t++;
            }

        }
        result.put(labels[labels.length - 1],
                this.text.substring((index[index.length - 1] + labels[labels.length - 1].length()) + 1));
        return result;
    }

    // Calculate prefix function of sub pattern p(k): p(1...k...label.length), p[k] = n means the longest proper prefix
    // to be the longest proper suffix of p[k] is p[n].
    // Example shows in header notes of this class above.
    public static int[] computePrefix(final String label) {
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
