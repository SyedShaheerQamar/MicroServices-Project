package com.question.controller;

import com.question.service.QuestionService;
import com.question.entity.Question;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/save")
    public Question save(@RequestBody Question question){
        return this.questionService.create(question);
    }

    @GetMapping("/getAll")
    public List<Question> getAll(){
        return this.questionService.getAll();
    }

    //get all question of specific quiz
    @GetMapping("/get-quiz/{id}")
    public List<Question> getQuestionByQuizId(@PathVariable Long id){
        return this.questionService.getQuestionOfQuiz(id);
    }

    @GetMapping("/get/{id}")
    public Question getById(@PathVariable Long id){
        return this.questionService.getById(id);
    }

}
