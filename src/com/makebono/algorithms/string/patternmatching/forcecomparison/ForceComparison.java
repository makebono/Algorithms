/**Note:
 * Paragraphing the text by force compare selected labels, and add following text with its label into map. For a set of
 * labels with average length m in a |n| text, the complexity is o((n-m+1)m).
 */
package com.makebono.algorithms.string.patternmatching.forcecomparison;

import java.io.FileNotFoundException;
import java.util.HashMap;

import com.makebono.algorithms.string.patternmatching.Matching;

/** 
 * @ClassName: ForceComparison 
 * @Description: ForceComparison. Paragraphing the text according to input labels.
 * @author makebono
 * @date 2017年12月14日 下午2:04:28 
 *  
 */
public class ForceComparison extends Matching {
    public ForceComparison(final String location) throws FileNotFoundException {
        super(location);

    }

    @Override
    public HashMap<String, String> paragraph(final String... labels) {
        final HashMap<String, String> result = new HashMap<String, String>();
        final int[] index = new int[labels.length];
        int i = 0;

        while (i < labels.length) {
            index[i] = this.text.indexOf(labels[i]);

            // System.out.println(index);
            if (i == 0) {
                i++;
                continue;
            } else {
                result.put(labels[i - 1], this.text.substring(index[i - 1] + labels[i - 1].length(), index[i]));
            }
            i++;

        }
        result.put(labels[i - 1], this.text.substring(index[i - 1] + labels[i - 1].length()));

        return result;
    }
}
