package com.sourabh.UserService.services;

import com.sourabh.UserService.entities.User;

import java.util.List;

public interface UserService {

//    create
    User saveUser(User user);

//    getAllUser
    List<User> getAllUser();

//    getSingleUser
    User getUser(String userId);

}
