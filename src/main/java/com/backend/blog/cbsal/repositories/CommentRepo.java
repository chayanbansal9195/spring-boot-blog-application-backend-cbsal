package com.backend.blog.cbsal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.blog.cbsal.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
