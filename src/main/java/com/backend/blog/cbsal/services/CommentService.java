package com.backend.blog.cbsal.services;

import com.backend.blog.cbsal.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	 void deleteComment(Integer commentId);
}
