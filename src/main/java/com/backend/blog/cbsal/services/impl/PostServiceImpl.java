package com.backend.blog.cbsal.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.cbsal.entities.Category;
import com.backend.blog.cbsal.entities.Post;
import com.backend.blog.cbsal.entities.User;
import com.backend.blog.cbsal.exceptions.ResourceNotFoundException;
import com.backend.blog.cbsal.payloads.PostDto;
import com.backend.blog.cbsal.repositories.CategoryRepo;
import com.backend.blog.cbsal.repositories.PostRepo;
import com.backend.blog.cbsal.repositories.UserRepo;
import com.backend.blog.cbsal.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		Post createPost = this.modelMapper.map(postDto, Post.class);
		createPost.setImageName("default.png");
		createPost.setAddedDate(new Date());
		createPost.setUser(user);
		createPost.setCategory(category);

		Post addedPost = this.postRepo.save(createPost);

		return this.modelMapper.map(addedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post getPost = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		getPost.setTitle(postDto.getTitle());
		getPost.setContent(postDto.getContent());
		getPost.setImageName(postDto.getImageName());

		Post getUpdatedPost = this.postRepo.save(getPost);

		return this.modelMapper.map(getUpdatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post getPost = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepo.delete(getPost);

	}

	@Override
	public PostDto getSinglePost(Integer postId) {

		Post getPost = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		return this.modelMapper.map(getPost, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPosts() {

		List<Post> getAllPosts = this.postRepo.findAll();
		List<PostDto> getAllPostsDto = getAllPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return getAllPostsDto;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {

		Category getCategoryId = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> getPosts = this.postRepo.findByCategory(getCategoryId);
		List<PostDto> getPostsDto = getPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return getPostsDto;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User getUserId = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> getPosts = this.postRepo.findByUser(getUserId);
		List<PostDto> getPostsDto = getPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return getPostsDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
