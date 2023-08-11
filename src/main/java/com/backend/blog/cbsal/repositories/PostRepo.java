package com.backend.blog.cbsal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.blog.cbsal.entities.Category;
import com.backend.blog.cbsal.entities.Post;
import com.backend.blog.cbsal.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	// custom finder methods in repo for all posts of user and all post of a
	// category

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

}
