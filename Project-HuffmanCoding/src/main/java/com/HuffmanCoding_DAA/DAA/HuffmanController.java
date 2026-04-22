package com.HuffmanCoding_DAA.DAA;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HuffmanController {

    @PostMapping("/compress")
    public Map<String, String> compress(
        @RequestBody Map<String, String> request
    ) {
        Map<String, String> response = new HashMap<>();
        String text = request.get("text");

        if (text == null || text.isEmpty()) {
            response.put("error", "Input text cannot be null or empty.");
            return response;
        }

        // Call the static compress method from HuffmanCoding
        String compressed = HuffmanCoding.compress(text);

        response.put("compressed", compressed);

        return response;
    }
}
