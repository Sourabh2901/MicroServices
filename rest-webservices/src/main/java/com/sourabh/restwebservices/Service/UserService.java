package com.sourabh.restwebservices.Service;

import com.sourabh.restwebservices.Exception.UserNotFoundException;
import com.sourabh.restwebservices.Repository.UserRepo;
import com.sourabh.restwebservices.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

//    private static List<User> users = new ArrayList<>();

//    static {
//        users.add(User.builder().id(1).name("sourabh").birthDate(LocalDate.now().minusYears(26)).build());
//        users.add(User.builder().id(2).name("arnav").birthDate(LocalDate.now().minusYears(16)).build());
//        users.add(User.builder().id(3).name("hello").birthDate(LocalDate.now().minusYears(12)).build());
//    }

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

//    public User getUserById(Integer id) {
//        return users.stream()
//                .filter(x -> x.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new UserNotFoundException("User Not Found With id : "+id));
//    }
//
    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(Integer id ,User user) {
//        User user1 = users.stream().filter(x -> x.getId().equals(id)).findFirst().get();
        Optional<User> user1 = userRepo.findById(id);
        if(user1.isEmpty()){
            throw new UserNotFoundException("User Not Found with id : "+id);
        }
        User obj = user1.get();
        obj.setId(user.getId());
        obj.setName(user.getName());
        obj.setBirthDate(user.getBirthDate());
        return userRepo.save(obj);
    }

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
