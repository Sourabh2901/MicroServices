package com.questionService.Controllers;

import com.questionService.Model.Question;
import com.questionService.Model.QuestionWrapper;
import com.questionService.Model.Response;
import com.questionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class Controllers {

    @Autowired
    QuestionService service;
    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> question(){
        return service.getAllQuestions();
    }

    @PostMapping("/addquestion")
    public ResponseEntity<Question> addquestion(@RequestBody Question q){
        return service.addQuestion(q);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return service.getQuestionByCategory(category);
    }

    //generateQuiz
    @GetMapping("/generateQuiz")
    public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category){
        return service.getQuestionForQuiz(category);
    }

    //getQuestionById
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> ids){
        return service.getQuestionById(ids);
    }

    //calculateScore
    @PostMapping("/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return service.getScore(responses);
    }

}
