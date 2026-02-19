package me.michalsmoladev.LibrarySystem.User.Application.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
