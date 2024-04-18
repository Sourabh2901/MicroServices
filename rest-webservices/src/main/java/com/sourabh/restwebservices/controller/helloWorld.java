package com.sourabh.restwebservices.controller;

import com.sourabh.restwebservices.payload.helloBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloWorld {

    @GetMapping("/hello")
    public String hello(){
        return "helloWorld";
    }

    @GetMapping("/hello-bean")
    public helloBean helloBean(){
        return new helloBean("hello world Bean");
    }
}
