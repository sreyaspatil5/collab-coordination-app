package com.webApp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webApp.entity.User;
import com.webApp.exceptions.ResourceNotFoundException;

import com.webApp.exceptions.UserNotFoundException;
import com.webApp.repository.UserRepository;
import com.webApp.exceptions.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        // Check if user with the same details already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists.",user.getUsername());
        }

        // Validation
        validateUser(user);

        // Save the user to the database
        return userRepository.save(user);
    }

    public void updateUserImageBlob(Long userId, MultipartFile imageFile) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // Convert MultipartFile to byte array
        byte[] imageData = imageFile.getBytes();

        // Update user image as Blob in the database
        user.setImage(imageData); // Assuming there's a setImage method in your User entity

        // Save the updated user with the image Blob
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        // Validation
        validateUser(updatedUser);

        // Get the existing user from the database
        Optional<User> existingUserOptional = getUserById(id);

        // Check if the user exists
        if (existingUserOptional.isPresent()) {
            // Extract the actual User object from the Optional
            User existingUser = existingUserOptional.get();

            // Update the user properties
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setContactNo(updatedUser.getContactNo());
            existingUser.setImage(updatedUser.getImage());

            // Save the updated user to the database
            return userRepository.save(existingUser);
        } else {
            // If the user doesn't exist, throw an exception or handle it accordingly
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }


    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }




    public void deleteUser(Long id) {
        // Delete the user
        userRepository.deleteById(id);
    }

    public void deleteUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
        } else {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }
    }

    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
    }

    public List<User> searchUsersByFirstLetter(String letter) {
        return userRepository.findByUsernameStartingWith(letter);
    }

    public User updateUserImage(Long id, MultipartFile imageFile) {
        // Get the existing user from the database
        Optional<User> existingUserOptional = getUserById(id);

        // Check if the user exists
        if (existingUserOptional.isPresent()) {
            // Extract the actual User object from the Optional
            User existingUser = existingUserOptional.get();

            try {
                // Set the image of the existing user
                existingUser.setImage(imageFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload image for user with ID: " + id, e);
            }

            // Save the updated user to the database
            return userRepository.save(existingUser);
        } else {
            // If the user doesn't exist, throw an exception or handle it accordingly
            throw new UserNotFoundException("User not found with Id: " + id);
        }
    }

}
