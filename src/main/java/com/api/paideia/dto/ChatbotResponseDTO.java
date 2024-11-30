package com.api.paideia.dto;

import java.util.List;

public record ChatbotResponseDTO(String questionContent, List<String> answers) {
}
