package com.questionService.Repo;

import com.questionService.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question ,Integer>{
    List<Question> findByCategory(String category);

    @Query(value ="select q.id from Question q where q.category=:category")
    List<Integer> findRandomQuestionByCategory(String category);
}
