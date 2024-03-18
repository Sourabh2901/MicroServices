package com.questionService.service;

import com.questionService.Model.Question;
import com.questionService.Model.QuestionWrapper;
import com.questionService.Model.Response;
import com.questionService.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo repo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(repo.findAll() , HttpStatus.OK);
    }

    public ResponseEntity<Question> addQuestion(Question q) {
       return new ResponseEntity<>(repo.save(q),HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        return new ResponseEntity<>(repo.findByCategory(category) ,HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category) {
        List<Integer> res = repo.findRandomQuestionByCategory(category);
        return new ResponseEntity<>(res ,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionById(List<Integer> ids) {
        List<QuestionWrapper> question = new ArrayList<>();

        for(Integer id:ids){
            Question obj = repo.findById(id).get();
            QuestionWrapper tmp = new QuestionWrapper(obj.getId() ,obj.getQuestionTitle() ,obj.getOption1() ,obj.getOption2() ,obj.getOption3() ,obj.getOption4());
            question.add(tmp);
        }

        return new ResponseEntity<>(question ,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        Integer score = 0;
        for(Response res : responses){
            Question obj = repo.findById(res.getId()).get();
            if(obj.getRightAnswer().equals(res.getResponse())){
                score++;
            }
        }

        return new ResponseEntity<>(score ,HttpStatus.OK);
    }

}
