package com.api.paideia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.api.paideia.domain.chatbot.Answer;
import com.api.paideia.domain.chatbot.Question;
import com.api.paideia.dto.ChatbotResponseDTO;
import com.api.paideia.repositories.chatbot.AnswerRepository;
import com.api.paideia.repositories.chatbot.QuestionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@RestController
@RequestMapping("/paideia")
public class ChatbotController {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @GetMapping("/chatbot")

    public List<ChatbotResponseDTO> getChatbot() {
        List<Question> questions = questionRepository.findAll();
        List<ChatbotResponseDTO> chatbotResponseDTOs = new ArrayList<>();

        for (Question question : questions) {
            List<String> answer = question.getAnswers().stream()
                    .map(Answer::getContent).collect(Collectors.toList());

            ChatbotResponseDTO dto = new ChatbotResponseDTO(question.getContent(), answer);
            chatbotResponseDTOs.add(dto);
        }
        return chatbotResponseDTOs;
    }

    @PostMapping("/api/chatbot")
    public ResponseEntity<?> getChatbotResponse(@RequestBody Map<String, String> payload) {
        String userInput = payload.get("user_input");
        if (userInput == null || userInput.isEmpty()) {
            return ResponseEntity.badRequest().body("Pergunta não fornecida.");
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            String chatbotUrl = "http://localhost:5000/chat"; // URL da API Flask

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> requestBody = Map.of("user_input", userInput);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(chatbotUrl, request, Map.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao se comunicar com o chatbot.");
        }
    }
}
