package com.letscode.usersapi.service.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String id) {
        super ("User not found. Id " + id);
    }
}
