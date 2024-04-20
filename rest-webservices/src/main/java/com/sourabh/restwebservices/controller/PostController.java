package com.sourabh.restwebservices.controller;

import com.sourabh.restwebservices.Service.PostService;
import com.sourabh.restwebservices.entity.Post;
import com.sourabh.restwebservices.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;
    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postService.getAllPost();
    }

//    @GetMapping("/users/{id}")
//    public EntityModel<User> getUserById(@PathVariable Integer id){
//        User user =  userService.getUserById(id);
//        EntityModel<User> entityModel = EntityModel.of(user);
//        WebMvcLinkBuilder link = WebMvcLinkBuilder
//                                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
//        entityModel.add(link.withRel("allUsers"));
//        return entityModel;
//    }

    @PostMapping("/users/{id}/posts")
    public Post addPost(@Valid @RequestBody Post post ,@PathVariable Integer id){

        return postService.addPost(post ,id);
    }

//    @PutMapping("/users/{id}")
//    public User updateUser(@PathVariable Integer id , @RequestBody User user){
//        return userService.updateUser(id ,user);
//
//    }
//
//    @DeleteMapping("/users/{id}")
//    public boolean deleteUser(@PathVariable Integer id){
//        return userService.deleteUser(id);
//    }
}
