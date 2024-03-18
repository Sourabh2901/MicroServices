package com.quizService.service;

import com.quizService.Feign.QuizInterface;
import com.quizService.Model.QuestionWrapper;
import com.quizService.Model.Quiz;
import com.quizService.Model.Response;
import com.quizService.Repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepo repo;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questionId = quizInterface.generateQuiz(category).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionId);
        repo.save(quiz);

        return new ResponseEntity<>("Created" , HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
        Quiz quiz = repo.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> res = quizInterface.getQuestionsFromId(questionIds);
        return res;
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
       return quizInterface.getScore(responses);
    }

}
