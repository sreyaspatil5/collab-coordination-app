package com.webApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webApp.entity.Admin;
import com.webApp.exceptions.AdminAlreadyExistsException;
import com.webApp.exceptions.ResourceNotFoundException;
import com.webApp.repository.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
    	
    	if (adminRepository.existsByUsername(admin.getUsername())) {
            throw new AdminAlreadyExistsException("Admin with username " + admin.getUsername() + " already exists.");
        }
        // Validation
        validateAdmin(admin);

        // Business logic (if any)

        // Save to the database
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));
    }

    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        // Validation
        validateAdmin(updatedAdmin);

        // Business logic (if any)

        // Update the existing admin
        Admin existingAdmin = getAdminById(id);
        existingAdmin.setUsername(updatedAdmin.getUsername());
        existingAdmin.setPassword(updatedAdmin.getPassword());
        existingAdmin.setEmail(updatedAdmin.getEmail());

        return adminRepository.save(existingAdmin);
    }

    public void deleteAdmin(Long id) {
        // Business logic (if any)

        // Delete the admin
        adminRepository.deleteById(id);
    }

    private void validateAdmin(Admin admin) {
        if (admin.getUsername() == null || admin.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        // Add more validation rules as needed
    }
}