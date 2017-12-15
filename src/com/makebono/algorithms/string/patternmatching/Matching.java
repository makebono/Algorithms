package com.makebono.algorithms.string.patternmatching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/** 
 * @ClassName: Matching 
 * @Description: Abstract matching class. Paragraphing the text according to input labels.
 * @author makebono
 * @date 2017年12月15日 下午3:14:50 
 *  
 */
public abstract class Matching {
    protected final StringBuilder text;

    public Matching(final String location) throws FileNotFoundException {
        this.text = new StringBuilder();
        final Scanner data = new Scanner(new File(location));
        while (data.hasNext()) {
            final String temp = data.next();
            this.text.append(temp);
        }
        data.close();
    }

    public abstract HashMap<String, String> paragraph(final String... labels);

    public HashMap<String, String> paragraphByBuildInMethod(final String... labels) {
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
