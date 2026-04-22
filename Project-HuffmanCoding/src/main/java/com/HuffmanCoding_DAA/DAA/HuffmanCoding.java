package com.HuffmanCoding_DAA.DAA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCoding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to compress:");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Error: input cannot be empty.");
                scanner.close();
                return;
            }
            System.out.println("---");
            System.out.println("Input received: " + input);
            System.out.println("---");

            HashMap<Character, Integer> freqMap = buildFrequencyMap(input);
            System.out.println("---");

            HuffmanNode root = buildHuffmanTree(freqMap);
            System.out.println("---");

            HashMap<Character, String> codeMap = generateCodes(
                root,
                "",
                new HashMap<>()
            );
            System.out.println("---");

            String compressed = encode(input, codeMap);
            System.out.println("---");

            String decoded = decode(compressed, root);
            System.out.println(
                "Decoded text matches original: " + input.equals(decoded)
            );
            System.out.println("---");

            printStats(input, compressed);
            System.out.println("---");
            System.out.println("Done. Process complete.");
        }

        scanner.close();
    }

    // building frequencyMap()
    public static HashMap<Character, Integer> buildFrequencyMap(String text) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(
            freqMap.entrySet()
        );
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("Character Frequency Map:");
        for (Map.Entry<Character, Integer> entry : entries) {
            System.out.println(
                "  '" + entry.getKey() + "' → " + entry.getValue()
            );
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
        if (root != null) {
            System.out.println(
                "Huffman tree built. Root frequency: " + root.freq
            );
        }
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

        // Print table only at the root call level
        if (currentCode.isEmpty()) {
            List<Map.Entry<Character, String>> entries = new ArrayList<>(
                codeMap.entrySet()
            );
            entries.sort((a, b) -> {
                int lenCmp = Integer.compare(
                    a.getValue().length(),
                    b.getValue().length()
                );
                if (lenCmp != 0) return lenCmp;
                return Character.compare(a.getKey(), b.getKey());
            });

            System.out.println("Huffman Code Table:");
            for (Map.Entry<Character, String> entry : entries) {
                String bitLabel =
                    entry.getValue().length() == 1 ? "bit" : "bits";
                System.out.println(
                    "  '" +
                        entry.getKey() +
                        "' → " +
                        entry.getValue() +
                        "  (" +
                        entry.getValue().length() +
                        " " +
                        bitLabel +
                        ")"
                );
            }
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

        System.out.println("Compressed binary string:");
        if (compressed.length() > 80) {
            System.out.println("  " + compressed.substring(0, 80) + "...");
            System.out.println(
                "  (showing first 80 chars, total length: " +
                    compressed.length() +
                    " bits)"
            );
        } else {
            System.out.println("  " + compressed);
            System.out.println(
                "  (total length: " + compressed.length() + " bits)"
            );
        }

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


    public static void printStats(String input, String compressed) {
        int originalBits = input.length() * 8;
        int compressedBits = compressed.length();
        System.out.println("Original size: " + originalBits + " bits");
        System.out.println("Compressed size: " + compressedBits + " bits");
        double ratio = (double) originalBits / compressedBits;
        System.out.printf("Compression ratio: %.2f:1\n", ratio);
    }
}
