package com.HuffmanCoding_DAA.DAA;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@Service
public class HuffmanService {

    public static Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        if (text == null || text.isEmpty()) {
            return freqMap;
        }

        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        return freqMap;
    }

    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        if (freqMap == null || freqMap.isEmpty()) {
            return null;
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue(), null, null));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode merged = new HuffmanNode('\0', left.freq + right.freq, left, right);
            pq.add(merged);
        }

        return pq.poll();
    }

    public static Map<Character, String> generateCodes(HuffmanNode root, String currentCode, Map<Character, String> codeMap) {
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

    public static String encode(String text, Map<Character, String> codeMap) {
        if (text == null || text.isEmpty() || codeMap == null || codeMap.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(codeMap.get(c));
        }

        return sb.toString();
    }

    public static String decode(String compressed, HuffmanNode root) {
        if (root == null || compressed == null || compressed.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

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
