package com.sourabh.restwebservices.Repository;

import com.sourabh.restwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepo extends JpaRepository<User ,Integer> {
}
