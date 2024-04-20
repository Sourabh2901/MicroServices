package com.sourabh.restwebservices.Repository;

import com.sourabh.restwebservices.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post ,Integer> {
}
