/**Note:
 * Paragraphing the text by force compare selected labels, and add following text with its label into map. It matches
 * first character through the text and when found a possible match, compares the rest of the characters in the label
 * to the characters with same length in the text. For a set of labels with average length m in a |n| text, the worst 
 * case complexity is o((n-m+1)m) (in such case, every next char in text is same as the first char of the label but 
 * the rest part never matchs).
 * 
 * Notice this is slower than using build-in method String.indexOf().
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
        final StringBuilder sb = new StringBuilder();
        int i = 0;
        int lastLabel = 0;

        for (int n = 0; n < this.text.length(); n++) {
            if (labels[i].charAt(0) == this.text.charAt(n)) {
                if (labels[i].substring(1).equals(this.text.substring(n + 1, n + labels[i].length()))) {
                    if (i == 0) {
                        i++;
                        sb.setLength(0);
                    } else if (i == labels.length - 1) {
                        lastLabel = n + labels[i].length();
                    } else {
                        result.put(labels[i - 1], sb.toString());
                        sb.setLength(0);
                        i++;
                    }
                } else {
                    sb.append(this.text.charAt(n));
                }
            } else {
                sb.append(this.text.charAt(n));
            }
        }
        sb.setLength(0);
        sb.append(this.text.substring(lastLabel));
        result.put(labels[labels.length - 1], sb.toString());
        return result;
    }

}
