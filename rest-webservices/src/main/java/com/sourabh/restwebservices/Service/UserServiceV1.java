package com.sourabh.restwebservices.Service;

import com.sourabh.restwebservices.Exception.UserNotFoundException;
import com.sourabh.restwebservices.entity.UserV1;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceV1 {

    private static List<UserV1> users = new ArrayList<>();

    static {
        users.add(UserV1.builder().id(1).firstName("sourabh").lastName("rai").birthDate(LocalDate.now().minusYears(26)).build());
        users.add(UserV1.builder().id(2).firstName("arnav").lastName("rai").birthDate(LocalDate.now().minusYears(16)).build());
        users.add(UserV1.builder().id(3).firstName("hello").lastName("xyz").birthDate(LocalDate.now().minusYears(12)).build());
    }

    public List<UserV1> getAllUsers(){
        return users;
    }

    public UserV1 getUserById(Integer id) {
        return users.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User Not Found With id : "+id));
    }

    public boolean addUser(UserV1 user) {
        return users.add(user);
    }

    public UserV1 updateUser(Integer id ,UserV1 user) {
        UserV1 user1 = users.stream().filter(x -> x.getId().equals(id)).findFirst().get();
        user1.setId(user.getId());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setBirthDate(user.getBirthDate());
        return user1;
    }

    public boolean deleteUser(Integer id) {
        UserV1 obj = users.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User Not Found With id : "+id));
        users.remove(obj);
        return true;
    }
}
