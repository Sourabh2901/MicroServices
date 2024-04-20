package com.sourabh.restwebservices.controller;

import com.sourabh.restwebservices.Service.UserService;
import com.sourabh.restwebservices.entity.User;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
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

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Integer id , @RequestBody User user){
        return userService.updateUser(id ,user);

    }

    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }
}
