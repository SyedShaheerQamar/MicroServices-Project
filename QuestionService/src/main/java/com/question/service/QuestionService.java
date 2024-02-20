package com.question.service;

import com.question.entity.Question;
import com.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    Question create(Question question);

    List<Question> getAll();

    Question getById(Long id);

    List<Question> getQuestionOfQuiz(Long id);

}
