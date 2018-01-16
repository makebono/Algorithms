package com.makebono.algorithms.greedyalgorithm.huffmancoding;

import java.io.FileNotFoundException;

/** 
 * @ClassName: HCDemo 
 * @Description: Demo of Huffman coding
 * @author makebono
 * @date 2018年1月16日 下午4:42:39 
 *  
 */
public class HCDemo {
    public static void show() throws FileNotFoundException {
        final HuffmanCoding hc = new HuffmanCoding("inputSet/huffmaninput.txt");
        hc.printText();
        hc.printCodeBook();
        hc.printDecodeBook();
        hc.printEncodedText();
        hc.printDecodedText();
    }
}
