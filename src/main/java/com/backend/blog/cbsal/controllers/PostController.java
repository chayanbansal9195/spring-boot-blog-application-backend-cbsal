package com.backend.blog.cbsal.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.cbsal.payloads.ApiResponse;
import com.backend.blog.cbsal.payloads.PostDto;
import com.backend.blog.cbsal.services.PostService;

@RestController
@RequestMapping("api/post/v1")
public class PostController {

	@Autowired
	private PostService postService;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/create")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

	}

	// update post
	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	// delete post
	@DeleteMapping("/delete/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {

		this.postService.deletePost(postId);

		return new ApiResponse("Post deleted successfully!!", true);
	}

	// get single post
	@GetMapping("/get/{postId}")
	public ResponseEntity<PostDto> getAllPosts(@PathVariable Integer postId) {

		PostDto getSinglePostDto = this.postService.getSinglePost(postId);

		return new ResponseEntity<PostDto>(getSinglePostDto, HttpStatus.OK);
	}

	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts() {

		List<PostDto> getAllPostDto = this.postService.getAllPosts();

		return new ResponseEntity<List<PostDto>>(getAllPostDto, HttpStatus.OK);
	}

	// get by category
	@GetMapping("/category/{categoryId}/get")
	public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable Integer categoryId) {

		List<PostDto> posts = this.postService.getAllPostByCategory(categoryId);

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	// get by user
	@GetMapping("/user/{userId}/get")
	public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable Integer userId) {

		List<PostDto> posts = this.postService.getAllPostByUser(userId);

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

}
