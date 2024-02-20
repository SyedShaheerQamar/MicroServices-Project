package com.question.service.impl;

import com.question.repository.QuestionRepository;
import com.question.service.QuestionService;
import com.question.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question create(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public List<Question> getAll() {
        return this.questionRepository.findAll();
    }

    @Override
    public Question getById(Long id) {
        return this.questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found!!!"));
    }

    @Override
    public List<Question> getQuestionOfQuiz(Long id) {
        return this.questionRepository.findByQuizId(id);
    }
}
