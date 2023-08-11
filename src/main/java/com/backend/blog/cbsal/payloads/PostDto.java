package com.backend.blog.cbsal.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private String postId;
	private String title;
	private String content;
	private Date addedDate;
	private String imageName;

	// user id and category id
	private CategoryDto category;
	private UserDto user;

}
