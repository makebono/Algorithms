/**Note: A greedy algorithm to find proper encoding for characters. In this encoding , a character will has shorter code as it has higher
 * frequency of appearance. Huffman coding will first scan through the input to count the number of appearance of each character, then put 
 * them into a Huffman tree. In such tree, every node has exactly 2 children, except the leaves, every node is dummy which has value equals
 * the sum of frequency of its 2 children. For example, 'a' has frequency of 5 and 'b' shows up 6 times, then this sub-tree is like
 *                                                   (dummy, 11)
 *                                                      /   \
 *                                                   (a,5) (b,6)
 * Follow this algorithm to build the tree, so the root is (dummy, length of input text). 
 * And for the coding part, every layer in the tree is to append '0' to left children and '1' to right children, this will make character
 * with higher frequency has shorter code. And most importantly, this will also guarantee the prefix of every coded code has uniqueness on
 * its prefix. So the decoding will be easy to dealt with.
 * 
 * Instruction:
 *     final HuffmanCoding hc = new HuffmanCoding("huffmaninput.txt");
 *     hc.printText();
 *     hc.printCodeBook();
 *     hc.printDecodeBook();
 *     hc.printEncodedText();
 *     hc.printDecodedText();
 */
package com.makebono.algorithms.greedyalgorithm.huffmancoding;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.makebono.datastructures.binarysearchtree.HTNode;
import com.makebono.datastructures.binarysearchtree.HuffmanBonoTree;

/** 
 * @ClassName: HuffmanCoding 
 * @Description: Implementation of Huffman. Use my BinarySearchBonoTree to encode. And color to store letters.
 * @author makebono
 * @date 2017年11月22日 下午2:47:52 
 *  
 */
public class HuffmanCoding {
    private final HuffmanBonoTree huffmanTree;
    private StringBuilder text;
    private final StringBuilder encodedText;
    private final StringBuilder decodedText;
    private final HashMap<Character, String> codeBook;
    private final HashMap<String, Character> decodeBook;

    public HuffmanCoding(final String location) throws FileNotFoundException {
        this.huffmanTree = new HuffmanBonoTree();
        this.text = new StringBuilder();
        this.encodedText = new StringBuilder();
        this.decodedText = new StringBuilder();
        this.codeBook = new HashMap<Character, String>();
        this.decodeBook = new HashMap<String, Character>();
        this.assignCode(location);
        this.encode();
        this.decode();
    }

    private ArrayList<HTNode> buildTree(final String location) throws FileNotFoundException {
        final Scanner data = new Scanner(new File(location));
        // Use a hash map for mapping characters to their frequency.
        final HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        ArrayList<HTNode> elements = new ArrayList<HTNode>();
        this.text = new StringBuilder();
        int countChar = 0;
        int countSpace = 0;

        while (data.hasNext()) {
            final String temp = data.next();
            this.text.append(temp);

            // For decoding, count space as well.
            if (map.get(' ') == null) {
                countSpace = 1;
            } else {
                countSpace = map.get(' ') + 1;
            }
            map.put(' ', countSpace);

            for (int i = 0; i < temp.length(); i++) {
                final char input = temp.charAt(i);
                int count = 0;

                if (map.get(input) == null) {
                    count = 1;
                } else {
                    count = map.get(input) + 1;
                }
                map.remove(input);
                map.put(input, count);
            }

            if (countChar == 20) {
                this.text.append("\n");
                countChar = 0;
            } else {
                this.text.append(" ");
                countChar++;
            }
        }

        data.close();
        // System.out.println(map.entrySet().size());

        int index = 0;

        for (final Map.Entry<Character, Integer> cursor : map.entrySet()) {
            elements.add(new HTNode(index, cursor.getValue(), cursor.getKey()));
            index++;
        }

        /*
        for (final HTNode cursor : elements) {
            System.out.println(cursor.getColor() + ": " + cursor.getData());
        }
        */

        elements = this.huffmanTree.buildTree(elements);
        return elements;
    }

    private void assignCode(final String location) throws FileNotFoundException {
        final ArrayList<HTNode> result = this.buildTree(location);
        for (final HTNode cursor : result) {
            this.codeBook.put(cursor.getColor(), cursor.getCode());
            this.decodeBook.put(cursor.getCode(), cursor.getColor());
        }
    }

    private void encode() {
        final Scanner sc = new Scanner(this.text.toString());

        while (sc.hasNext()) {
            final String cursor = sc.nextLine();

            for (int i = 0; i < cursor.length(); i++) {
                this.encodedText.append(this.codeBook.get(cursor.charAt(i)));
            }
            this.encodedText.append("\n");
        }
        sc.close();
    }

    private void decode() {
        final Scanner sc = new Scanner(this.encodedText.toString());
        while (sc.hasNext()) {
            final String cursor = sc.nextLine();
            final StringBuilder subString = new StringBuilder();

            for (int i = 0; i < cursor.length(); i++) {
                subString.append(cursor.charAt(i));
                if (this.decodeBook.get(subString.toString()) != null) {
                    this.decodedText.append(this.decodeBook.get(subString.toString()));
                    subString.delete(0, subString.length() + 1);
                }
            }
            this.decodedText.append("\n");
        }
        sc.close();
    }

    public void printText() {
        System.out.println("The original text is: ");
        System.out.println(this.text.toString() + "\n\n");
    }

    public void printEncodedText() {
        System.out.println("Encoded text is: ");
        System.out.println(this.encodedText.toString() + "\n\n");
    }

    public void printDecodedText() {
        System.out.println("Decoded text is: ");
        System.out.println(this.decodedText.toString() + "\n\n");
    }

    public void printCodeBook() {
        System.out.println("Code book of input is: ");
        int count = 0;
        for (final Map.Entry<Character, String> entry : this.codeBook.entrySet()) {
            if (entry.getKey() == ' ') {
                System.out.print("('space'" + ": " + entry.getValue() + ") ");
            } else {
                System.out.print("(" + entry.getKey() + ": " + entry.getValue() + ") ");
            }
            if (count == 10) {
                count = 0;
                System.out.println();
            } else {
                count++;
            }
        }
        System.out.println("\n\n");
    }

    public void printDecodeBook() {
        System.out.println("Decode book of input is: ");
        int count = 0;
        for (final Map.Entry<String, Character> entry : this.decodeBook.entrySet()) {
            if (entry.getValue() == ' ') {
                System.out.print("(" + entry.getKey() + ": 'space') ");
            } else {
                System.out.print("(" + entry.getKey() + ": " + entry.getValue() + ") ");
            }

            if (count == 10) {
                count = 0;
                System.out.println();
            } else {
                count++;
            }
        }
        System.out.println("\n\n");
    }

    public HashMap<Character, String> getCodeBook() {
        return this.codeBook;
    }

    public HashMap<String, Character> getDecodeBook() {
        return this.decodeBook;
    }
}
