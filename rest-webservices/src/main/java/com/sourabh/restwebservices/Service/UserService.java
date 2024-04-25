package com.sourabh.restwebservices.Service;

import com.sourabh.restwebservices.Exception.UserNotFoundException;
import com.sourabh.restwebservices.Repository.UserRepo;
import com.sourabh.restwebservices.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Cacheable(cacheNames = "users")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @Cacheable(cacheNames = "users" ,key = "#id")
    public User getUserById(Integer id) {
        System.out.println("Inside Service Method");
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User Not Found with id : "+id);
        }
        return user.get();
//        return users.stream()
//                .filter(x -> x.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new UserNotFoundException("User Not Found With id : "+id));
    }

    public User addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return userRepo.save(user);
    }

    @CachePut(cacheNames = "users" ,key = "#user.id")
    public User updateUser(Integer id ,User user) {
//        User user1 = users.stream().filter(x -> x.getId().equals(id)).findFirst().get();
        Optional<User> user1 = userRepo.findById(id);
        if(user1.isEmpty()){
            throw new UserNotFoundException("User Not Found with id : "+id);
        }
        User obj = user1.get();
//        obj.setId(user.getId());
        obj.setUsername(user.getUsername());
        obj.setPassword(user.getPassword());
        return userRepo.save(obj);
    }

    @CacheEvict(cacheNames = "users" ,key = "#id")
    public boolean deleteUser(Integer id) {
//        User obj = users.stream()
//                .filter(x -> x.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new UserNotFoundException("User Not Found With id : "+id));
//        users.remove(obj);
//        return true;

        Optional<User> user1 = userRepo.findById(id);
        if(user1.isEmpty()){
            throw new UserNotFoundException("User Not Found with id : "+id);
        }
        userRepo.deleteById(id);
        return true;
    }
}
