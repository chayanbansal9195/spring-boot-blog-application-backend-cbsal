package com.backend.blog.cbsal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.blog.cbsal.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
