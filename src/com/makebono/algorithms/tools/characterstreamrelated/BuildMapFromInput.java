/**Note:
 * Could be somewhat useful. Build a HashMap<String,String> according to a input String. Define characters expressing match and end of a pair first.
 * Then separate the String and put each matching pair into map.
 * 
 * For example:
 *     
 *     final String str = "abc:123,ddd:333,dwq:155,aaa:612";
 *
 *     final Map<String, String> map = BuildMapFromInput.build(str, ',', ':');
 *     
 * Will generate a map:
 *     map: 
 *         [abc:123
 *          ddd:333
 *          dwq:155
 *          aaa:612] 
 */
package com.makebono.algorithms.tools.characterstreamrelated;

import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: BuildMapFromInput 
 * @Description: According to input stream and convert it into a map 
 * @author makebono
 * @date 2017年12月13日 上午11:53:54 
 *  
 */
public class BuildMapFromInput {
    public static Map<String, String> build(final String inputString, final char splitPair, final char splitMatch) {
        final Map<String, String> result = new HashMap<String, String>();
        final String input = inputString.trim();
        String newKey = "";
        String newElement = "";
        final StringBuilder sb = new StringBuilder();
        int count = 0;

        for (final char c : inputString.toCharArray()) {
            count++;
            if (c == splitPair || count == input.length()) {
                if (count == input.length()) {
                    sb.append(c);
                }
                newElement = sb.toString();
                result.put(newKey, newElement);
                sb.delete(0, sb.length());
            } else if (c == splitMatch) {
                newKey = sb.toString();
                sb.delete(0, sb.length());
            } else {
                sb.append(c);
            }
        }

        return result;
    }
}
