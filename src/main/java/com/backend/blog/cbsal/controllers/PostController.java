package com.backend.blog.cbsal.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.blog.cbsal.config.AppConstants;
import com.backend.blog.cbsal.payloads.ApiResponse;
import com.backend.blog.cbsal.payloads.PostDto;
import com.backend.blog.cbsal.payloads.PostResponse;
import com.backend.blog.cbsal.services.FileService;
import com.backend.blog.cbsal.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/post/v1")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

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
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
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

	// search
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword) {
		List<PostDto> searchedPosts = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(searchedPosts, HttpStatus.OK);
	}

	// post image upload by post id
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {

		PostDto postDto = this.postService.getSinglePost(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatedPost = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	//get image by post id
	
	@GetMapping(value="/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void getPostImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
