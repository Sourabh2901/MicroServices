package com.sourabh.UserService.execeptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String user, String id, String userId) {
        super(user +" Not found with Id "+userId);
    }
}
