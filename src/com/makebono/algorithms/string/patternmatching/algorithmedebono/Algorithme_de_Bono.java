package com.makebono.algorithms.string.patternmatching.algorithmedebono;

import java.io.FileNotFoundException;
import java.util.HashMap;

import com.makebono.algorithms.greedyalgorithm.huffmancoding.HuffmanCoding;

/** 
 * @ClassName: Algorithme_de_Bono 
 * @Description: Algorithme de Bono. Pattern mathching of String in a text.
 * @author makebono
 * @date 2017年12月14日 下午2:04:28 
 *  
 */
public class Algorithme_de_Bono {
    private final HuffmanCoding encoder;

    public Algorithme_de_Bono(final String input) throws FileNotFoundException {
        this.encoder = new HuffmanCoding(input);
    }

    public HashMap<String, String> paragraph(final String... labels) {
        final HashMap<String, String> result = new HashMap<String, String>();
        final String[] codeSet = new String[labels.length];
        final String encoded = this.encoder.getEncoded();
        final StringBuilder sb = new StringBuilder();
        int i = 0;

        for (final String cursor : labels) {
            codeSet[i] = this.codeChain(cursor);
            // System.out.println(codeSet[i]);
            i++;
        }

        // System.out.println(encoded);
        // System.out.println(codeSet[0]);

        i = 0;
        int lastLabel = 0;
        String candidate = "";
        for (int n = 0; n < encoded.length(); n++) {

            if (i < codeSet.length) {
                candidate = encoded.substring(n, n + codeSet[i].length());

                if (candidate.equals(codeSet[i])) {
                    if (i == codeSet.length - 1) {
                        lastLabel = n + codeSet[i].length();
                    }
                    n += codeSet[i].length() - 1;
                    if (i == 0) {
                        sb.setLength(0);
                        i++;
                    } else {
                        result.put(labels[i - 1], this.getEncoder().decode(sb).toString());
                        sb.setLength(0);
                        i++;
                    }

                } else {
                    if (i != 0) {
                        sb.append(encoded.charAt(n));
                    }
                }
            } else {
                candidate += encoded.charAt(n);
            }
        }
        sb.setLength(0);
        sb.append(encoded.substring(lastLabel));
        result.put(labels[labels.length - 1], this.getEncoder().decode(sb).toString());
        return result;
    }

    public String codeChain(final String label) {
        final StringBuilder sb = new StringBuilder();
        final int length = label.length();

        for (int i = 0; i < length; i++) {
            sb.append(this.getEncoder().getCodeBook().get(label.charAt(i)));
        }

        return sb.toString();
    }

    public HuffmanCoding getEncoder() {
        return this.encoder;
    }
}
