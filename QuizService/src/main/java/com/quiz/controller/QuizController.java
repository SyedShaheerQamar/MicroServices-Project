package com.quiz.controller;

import com.quiz.entity.Quiz;
import com.quiz.service.QuizService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;

    Logger logger = LoggerFactory.getLogger(QuizController.class);

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz){
        return this.quizService.add(quiz);
    }

    @GetMapping("/getAll")
    @CircuitBreaker(name = "questionQuizService", fallbackMethod = "questionQuizFallBack")
    public List<Quiz> getAllQuiz(){
        return this.quizService.getAllQuiz();
    }

    public List<Quiz> questionQuizFallBack(Exception exception){
        logger.info("FallBack is executed!!!The Error Message is : "+ exception.getMessage());

        return this.quizService.getAll();
    }

    @GetMapping("/get/{id}")
    @CircuitBreaker(name = "questionQuizByIdService", fallbackMethod = "questionQuizByIdFallBack")
    public Quiz getQuizById(@PathVariable Integer id){
        return this.quizService.getById(Long.valueOf(id));
    }

    public Quiz questionQuizByIdFallBack(Integer id, Exception exception){
        logger.info("Exception!!! The error message is :"+ exception.getMessage());

        return this.quizService.getIdWithoutQuestion(Long.valueOf(id));
    }

}
