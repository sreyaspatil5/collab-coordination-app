package com.webApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webApp.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	boolean existsByUsername(String username);
}