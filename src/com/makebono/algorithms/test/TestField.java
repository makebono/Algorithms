package com.makebono.algorithms.test;

import java.io.FileNotFoundException;

import com.makebono.algorithms.greedyalgorithm.huffmancoding.HuffmanCoding;

/** 
 * @ClassName: TestField 
 * @Description: Test class
 * @author makebono
 * @date 2017年11月16日 上午9:50:03 
 *  
 */
public class TestField {
    public static void main(final String[] args) throws FileNotFoundException {
        final HuffmanCoding hc = new HuffmanCoding("huffmaninput.txt");
        hc.printText();
        hc.printCodeBook();
        hc.printDecodeBook();
        hc.printEncodedText();
        hc.printDecodedText();

    }
}
