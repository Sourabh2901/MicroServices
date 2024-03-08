package com.quizService.Feign;


import com.quizService.Model.QuestionWrapper;
import com.quizService.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    //generateQuiz
    @GetMapping("question/generateQuiz")
    public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category);

    //getQuestionById
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> ids);

    //calculateScore
    @PostMapping("question/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
