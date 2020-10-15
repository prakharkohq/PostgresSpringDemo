package com.falabella.postgres.PostgresDemo.repository;

import com.falabella.postgres.PostgresDemo.model.Answers;
import com.falabella.postgres.PostgresDemo.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answers,Long> {
    List<Answers> findByQuestionId(Long questionId);
}
