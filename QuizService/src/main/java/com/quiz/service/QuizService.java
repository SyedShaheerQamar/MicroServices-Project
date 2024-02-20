package com.quiz.service;

import com.quiz.entity.Quiz;

import java.util.List;

public interface QuizService {

    Quiz add(Quiz quiz);

    List<Quiz> getAllQuiz();

    Quiz getById(Long id);

    List<Quiz> getAll();

    Quiz getIdWithoutQuestion(Long id);
}
