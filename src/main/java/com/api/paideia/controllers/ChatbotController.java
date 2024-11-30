package com.api.paideia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
