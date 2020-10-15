package com.falabella.postgres.PostgresDemo.repository;

import com.falabella.postgres.PostgresDemo.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {
}
