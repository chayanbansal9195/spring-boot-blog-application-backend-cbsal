package com.backend.blog.cbsal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.cbsal.payloads.ApiResponse;
import com.backend.blog.cbsal.payloads.CommentDto;
import com.backend.blog.cbsal.services.CommentService;

@RestController
@RequestMapping("api/comment/v1")
public class CommentController {

	@Autowired
	private CommentService commentService;

	// create post comment
	@PostMapping("/post/{postId}/create")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {

		CommentDto createCommentDto = this.commentService.createComment(commentDto, postId);

		return new ResponseEntity<CommentDto>(createCommentDto, HttpStatus.CREATED);

	}

	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		this.commentService.deleteComment(commentId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Commented Deleted successfully", true), HttpStatus.OK);
	}
}
