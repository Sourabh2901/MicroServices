package com.sourabh.restwebservices.controller;

import com.sourabh.restwebservices.Service.UserServiceV1;
import com.sourabh.restwebservices.entity.User;
import com.sourabh.restwebservices.entity.UserV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/v1")
public class VersioningUserController {

    @Autowired
    private UserServiceV1 userServiceV1;

//    Based on URI
    @GetMapping("v1/users")
    public List<UserV1> getAllUser(){
        return userServiceV1.getAllUsers();
    }

    @GetMapping(path = "/users/params" ,params = "version=v1")
    public List<UserV1> getAllUserByParams() {
        return userServiceV1.getAllUsers();
    }

}
