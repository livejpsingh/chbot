package com.example.openaichatbot.llama;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/openai")
public class LamaChatbotController {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;
    private OpenAiChatModel chatModel = null;

    @PostMapping("chabot")  // Changed to POST
    public Map<String, Object> generate(@RequestBody Map<String, String> requestBody) {
        String message = requestBody.get("message");
        this.chatModel = OpenAiChatModel.builder()
                .apiKey(this.apiKey)
                .baseUrl("https://api.groq.com/openai/v1")
                .modelName("llama-3.2-90b-vision-preview")
                .temperature(0.4)
                .maxTokens(200)
                .build();

        return Map.of("generation", this.chatModel.generate(message));
    }
}
