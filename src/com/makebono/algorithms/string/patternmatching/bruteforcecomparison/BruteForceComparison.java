/**Note:
 * Paragraphing the text by force compare selected labels, and add following text with its label into map. It matches
 * first character through the text and when found a possible match, compares the rest of the characters in the label
 * to the characters with same length in the text. For a set of labels with average length m in a |n| text, the worst 
 * case complexity is o((n-m+1)m) (in such case, every next char in text is same as the first char of the label but 
 * the rest part never matchs).
 * 
 * Notice this is slower than using build-in method String.indexOf().
 */
package com.makebono.algorithms.string.patternmatching.bruteforcecomparison;

import java.io.FileNotFoundException;
import java.util.HashMap;

import com.makebono.algorithms.string.patternmatching.Matching;

/** 
 * @ClassName: BruteForceComparison 
 * @Description: BruteForceComparison. Paragraphing the text according to input labels.
 * @author makebono
 * @date 2017年12月14日 下午2:04:28 
 *  
 */
public class BruteForceComparison extends Matching {
    public BruteForceComparison(final String location) throws FileNotFoundException {
        super(location);

    }

    public void init(final String target) {
        this.ttext = this.text.toString().toCharArray();
        this.ttarget = target.toCharArray();
    }

    public void init(final String... targets) {
        this.ttext = this.text.toString().toCharArray();
        this.ttargets = new char[targets.length][];
        int i = 0;
        for (final String target : targets) {
            this.ttargets[i++] = target.toCharArray();
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
            index[i] = this.match(this.ttargets[i], startFrom);
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

    public int match(final char[] input) {
        return this.match(input, 0);
    }

    public int match(final char[] input, final int start) {
        final int n = this.ttext.length;
        final int m = input.length;
        boolean found = false;
        int q = 0;
        int i = start;
        boolean magicalSwitch = false;
        while (!found && i < n - m) {
            // Fail to matched in last iteration. Looking next match on first character.
            if (!magicalSwitch) {
                if (input[0] != this.ttext[i]) {
                    while (++i < n - m && input[0] != this.ttext[i]);
                }
                magicalSwitch = true;
            }

            // Next character matches
            if (input[q++] == this.ttext[i]) {
                // System.out.println(i);
                // q++;
            }
            // Next character does not match
            else {
                q = 0;
                magicalSwitch = false;
                i--;
            }

            // Find ya
            if (q == m) {
                found = true;
                return i - m + 1;
            }
            i++;
        }
        return -1;
    }

}