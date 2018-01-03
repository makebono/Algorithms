package com.makebono.algorithms.string.patternmatching;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
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
    protected char[] ttext;
    protected char[] ttarget;
    protected char[][] ttargets;

    public Matching(final String location) throws FileNotFoundException {
        this.text = new StringBuilder();
        final Scanner data = new Scanner(new File(location));
        while (data.hasNext()) {
            final String temp = data.next();
            this.text.append(temp);
        }
        data.close();
    }

    public void init(final String target)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        final StringBuilder temp = this.text;
        final Field f = temp.getClass().getSuperclass().getDeclaredField("value");
        f.setAccessible(true);
        this.ttext = (char[]) f.get(temp);
        this.ttarget = (char[]) f.get(target);
    }

    public abstract void init(final String... targets)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException;

    public abstract HashMap<String, String> paragraph(final String... labels);

    public HashMap<String, String> paragraphByBuildInMethod(final String... labels) {
        final HashMap<String, String> result = new HashMap<String, String>();
        final int m = labels.length;
        final int[] index = new int[m];
        int i = 0;
        int startFrom = 0;
        while (i < m) {
            index[i] = this.text.indexOf(labels[i], startFrom);
            startFrom = index[i];
            // System.out.println(index[i]);

            // System.out.println(index);
            if (i++ == 0) {
                continue;
            } else {
                result.put(labels[i - 2], this.text.substring(index[i - 2] + labels[i - 2].length(), index[i - 1]));
            }

        }
        result.put(labels[i - 1], this.text.substring(index[i - 1] + labels[i - 1].length()));

        return result;
    }

    public int matchByBuildInMethod(final String target) {
        return this.text.indexOf(target);
    }

}
