package com.sourabh.restwebservices.controller;

import com.sourabh.restwebservices.Service.JwtService;
import com.sourabh.restwebservices.Service.MyUserDetailsService;
import com.sourabh.restwebservices.Service.UserService;
import com.sourabh.restwebservices.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws Exception{

        this.authenticateUser(user.getUsername() , user.getPassword());

        User userObjFromDb = (User) myUserDetailsService.loadUserByUsername(user.getUsername());

        String jwtToken = jwtService.generateToken(userObjFromDb.getUsername());

        return "Token : " + jwtToken;
    }


    private void authenticateUser(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            authenticationManager.authenticate(authenticationToken);
        }catch (Exception e) {
            throw new RuntimeException("Invalid Username or Password !! ");
        }
    }

    @GetMapping("/")
    public String greet(){
        return "Namaste";
    }
}
