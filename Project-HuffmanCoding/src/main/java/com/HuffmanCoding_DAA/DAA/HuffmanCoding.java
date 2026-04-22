package com.HuffmanCoding_DAA.DAA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {

    public static String compress(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        HashMap<Character, Integer> freqMap = buildFrequencyMap(input);
        HuffmanNode root = buildHuffmanTree(freqMap);
        HashMap<Character, String> codeMap = generateCodes(
            root,
            "",
            new HashMap<>()
        );
        String compressed = encode(input, codeMap);

        return compressed;
    }

    // building frequencyMap()
    public static HashMap<Character, Integer> buildFrequencyMap(String text) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        return freqMap;
    }

    // building huffmanTree()
    public static HuffmanNode buildHuffmanTree(
        HashMap<Character, Integer> freqMap
    ) {
        if (freqMap == null || freqMap.isEmpty()) {
            return null;
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(
                new HuffmanNode(entry.getKey(), entry.getValue(), null, null)
            );
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode merged = new HuffmanNode(
                '\0',
                left.freq + right.freq,
                left,
                right
            );
            pq.add(merged);
        }

        HuffmanNode root = pq.poll();
        return root;
    }

    // generating codes()
    public static HashMap<Character, String> generateCodes(
        HuffmanNode root,
        String currentCode,
        HashMap<Character, String> codeMap
    ) {
        if (root == null) {
            return codeMap;
        }

        if (root.left == null && root.right == null) {
            codeMap.put(root.ch, currentCode.isEmpty() ? "0" : currentCode);
        } else {
            generateCodes(root.left, currentCode + "0", codeMap);
            generateCodes(root.right, currentCode + "1", codeMap);
        }

        return codeMap;
    }

    public static String encode(
        String text,
        HashMap<Character, String> codeMap
    ) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(codeMap.get(c));
        }
        String compressed = sb.toString();

        return compressed;
    }

    public static String decode(String compressed, HuffmanNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();

        // Handle single character edgecase
        if (root.left == null && root.right == null) {
            for (int i = 0; i < compressed.length(); i++) {
                sb.append(root.ch);
            }
            return sb.toString();
        }

        HuffmanNode curr = root;
        for (char bit : compressed.toCharArray()) {
            curr = (bit == '0') ? curr.left : curr.right;
            if (curr.left == null && curr.right == null) {
                sb.append(curr.ch);
                curr = root;
            }
        }
        return sb.toString();
    }
}
