package com.webApp.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webApp.entity.User;
import com.webApp.exceptions.UserAlreadyExistsException;
import com.webApp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam("file") MultipartFile file, @RequestParam("user") String userDetails) {
        try {
            // Convert JSON string to User object
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(userDetails, User.class);
            
            // Check if user with the same username already exists
            if (userService.getUserByUsername(user.getUsername()).isPresent()) {
                throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists.",user.getUsername());
            }
            
            // Validate user details
            validateUser(user);
            
            // Save user details
            User savedUser = userService.createUser(user);
            
            // Save user image as Blob
            userService.updateUserImageBlob(savedUser.getId(), file);
            
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@Valid @PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        User updatedUserDetails = userService.updateUser(id, updatedUser);
        if (updatedUserDetails != null) {
            return ResponseEntity.ok(updatedUserDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/search/{letter}")
    public ResponseEntity<List<User>> searchUsersByFirstLetter(@PathVariable String letter) {
        List<User> users = userService.searchUsersByFirstLetter(letter);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " deleted successfully.");
    }
    
    @DeleteMapping("/self")
    public ResponseEntity<String> deleteUser(Principal principal) {
        String username = principal.getName();
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok("User " + username + " deleted successfully.");
    }
    
    @PutMapping("/{id}/image")
    public ResponseEntity<User> updateUserImage(@PathVariable Long id, @RequestParam("image") MultipartFile imageFile) {
        User updatedUser = userService.updateUserImage(id, imageFile);
        return ResponseEntity.ok(updatedUser);
    }
}
