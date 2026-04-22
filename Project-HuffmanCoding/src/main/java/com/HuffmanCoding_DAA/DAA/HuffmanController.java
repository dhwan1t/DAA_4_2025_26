package com.HuffmanCoding_DAA.DAA;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class HuffmanController {

    @PostMapping("/compress")
    public Map<String, Object> compress(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        String text = request.get("text");

        if (text == null || text.isEmpty()) {
            response.put("error", "Input text cannot be null or empty.");
            return response;
        }

        // Call HuffmanService methods
        Map<Character, Integer> freqMap = HuffmanService.buildFrequencyMap(text);
        HuffmanNode root = HuffmanService.buildHuffmanTree(freqMap);
        Map<Character, String> codeMap = HuffmanService.generateCodes(root, "", new HashMap<>());
        String compressed = HuffmanService.encode(text, codeMap);

        // Calculate compression ratio
        int originalBits = text.length() * 8;
        int compressedBits = compressed.length();
        double ratio = compressedBits > 0 ? (double) originalBits / compressedBits : 0.0;

        // Build output JSON structure
        response.put("compressed", compressed);
        response.put("ratio", ratio);
        response.put("codes", codeMap);

        return response;
    }
}
