package com.sourabh.restwebservices.Service;

import com.sourabh.restwebservices.Repository.UserRepo;
import com.sourabh.restwebservices.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User obj = repo.findByUsername(username);
        if(obj == null){
            throw new UsernameNotFoundException("User Not Found with username : "+username);
        }
        return obj;
    }
}
