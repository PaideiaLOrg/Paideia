package com.api.paideia.repositories.chatbot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.paideia.domain.chatbot.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
