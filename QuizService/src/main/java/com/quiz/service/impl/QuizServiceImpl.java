package com.quiz.service.impl;

import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuestionClient;
import com.quiz.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;

    private QuestionClient questionClient;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient = questionClient;
    }

    @Override
    public Quiz add(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public List<Quiz>  getAllQuiz() {
        List<Quiz> quizzes =  this.quizRepository.findAll();
        List<Quiz> quizList = quizzes.stream()
                                    .map(quiz -> {
                                       quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
                                       return quiz;
                                    })
                                    .collect(Collectors.toList());

        return quizList;
    }

    @Override
    public Quiz getById(Long id) {
        Quiz quiz = this.quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found at id : "+ id));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
        return quiz;
    }

    @Override
    public Quiz getIdWithoutQuestion(Long id){
        Quiz quiz = this.quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found at id : "+ id));
        return quiz;
    }

    @Override
    public List<Quiz> getAll() {
        return this.quizRepository.findAll();
    }
}
