package com.quizService.Repo;

import com.quizService.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer>{

}
