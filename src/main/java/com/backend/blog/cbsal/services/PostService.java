package com.backend.blog.cbsal.services;

import java.util.List;

import com.backend.blog.cbsal.payloads.PostDto;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete
	void deletePost(Integer postId);

	// getone
	PostDto getSinglePost(Integer postId);

	// getall
	List<PostDto> getAllPosts();

	// get all post by category
	List<PostDto> getAllPostByCategory(Integer categoryId);

	// get all post by user
	List<PostDto> getAllPostByUser(Integer userId);

	// search post
	List<PostDto> searchPosts(String keyword);

}
