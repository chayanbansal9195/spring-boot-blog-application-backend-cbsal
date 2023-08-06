package com.backend.blog.cbsal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.blog.cbsal.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
