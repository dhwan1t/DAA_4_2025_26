package com.HuffmanCoding_DAA.DAA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class HuffmanNode implements Comparable<HuffmanNode> {

    char ch;
    int freq;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char ch, int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        // Nodes with lower frequency have higher priority
        return Integer.compare(this.freq, other.freq);
    }
}
