package com.sourabh.restwebservices.Service;

import com.sourabh.restwebservices.Exception.UserNotFoundException;
import com.sourabh.restwebservices.Repository.PostRepo;
import com.sourabh.restwebservices.Repository.UserRepo;
import com.sourabh.restwebservices.entity.Post;
import com.sourabh.restwebservices.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;
    public List<Post> getAllPost() {
        return postRepo.findAll();
    }

    public Post addPost(Post post ,Integer id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User Not Found with id : "+id);
        }
        post.setUser(user.get());
        return postRepo.save(post);
    }
}
