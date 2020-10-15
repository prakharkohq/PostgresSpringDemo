package com.falabella.postgres.PostgresDemo.controller;

import com.falabella.postgres.PostgresDemo.exceptions.ResourceNotFoundException;
import com.falabella.postgres.PostgresDemo.model.Questions;
import com.falabella.postgres.PostgresDemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class QuestionsController {
    @Autowired
    QuestionRepository questionRepository;

    // Read
    @GetMapping("/questions")
    public Page<Questions> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @GetMapping("/questions/{id}")
    public Questions getQuestionsById(@PathVariable Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    // Update

    @PostMapping("/questions")
    public Questions createQuestions(@RequestBody Questions questions) {
        questionRepository.save(questions);
        return questions;
    }

    @PutMapping("/questions/{questionsId}")
    public Questions updateQuestion(@RequestBody Questions questions, @PathVariable Long questionsId) {
        return questionRepository.findById(questionsId).map(
                questions1 -> {
                    questions1.setTitle(questions.getTitle());
                    questions1.setDescription(questions.getDescription());
                    questions1.setId(questions.getId());
                    return questionRepository.save(questions1);
                }
        ).orElseThrow(() -> new ResourceNotFoundException(" Qustion not found with ID" + questionsId));
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return questionRepository.findById(questionId).map(questions -> {
            questionRepository.delete(questions);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(" Qustion not found with ID" + questionId));
    }


}
