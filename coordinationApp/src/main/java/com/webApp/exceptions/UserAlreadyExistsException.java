package com.webApp.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    private String username;

    public UserAlreadyExistsException(String message, String username) {
    	super("User with username '" + username + "' already exists.");
    	this.username = username;
    }
}



