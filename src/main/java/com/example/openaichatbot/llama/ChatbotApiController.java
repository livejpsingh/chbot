package com.example.openaichatbot.llama;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatbotApiController {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @PostMapping("/chatbot")
    public ModelAndView sendMessage(@RequestParam("message") String message, Model model) {
        {
            // Prepare request body for the chatbot API
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("message", message);

            // Call the API using RestTemplate
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "http://localhost:8080/openai/chabot";
            Map<String, Object> response = restTemplate.postForObject(apiUrl, requestBody, Map.class);

            // Return the response as JSON
            if (response != null) {
                model.addAttribute("generation", response.get("generation"));
            } else {
                model.addAttribute("generation", "No response from chatbot.");
            }

            return new ModelAndView("response");
        }
    }
}
