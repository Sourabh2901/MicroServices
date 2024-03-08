package com.quizService.Controllers;

import com.quizService.Model.QuestionWrapper;
import com.quizService.Model.QuizDto;
import com.quizService.Model.Response;
import com.quizService.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class Controllers {

    @Autowired
    QuizService service;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return service.createQuiz(quizDto.getCategory() ,quizDto.getNumQ() ,quizDto.getTitle());
    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer quizId){
        return service.getQuizQuestions(quizId);
    }

    @PostMapping("/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return service.getScore(responses);
    }

}