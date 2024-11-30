package com.api.paideia.repositories.chatbot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.paideia.domain.chatbot.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
