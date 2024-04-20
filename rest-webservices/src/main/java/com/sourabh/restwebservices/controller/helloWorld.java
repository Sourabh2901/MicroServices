package com.sourabh.restwebservices.controller;

import com.sourabh.restwebservices.payload.helloBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/hello-bean/{id}")
    public helloBean helloBean(@PathVariable Integer id){
//        return new helloBean("hello world Bean with id: "+id);
        return new helloBean(String.format("Hello word from id , %s %s",id ,id));
    }
}
