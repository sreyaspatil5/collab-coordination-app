package com.webApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webApp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
