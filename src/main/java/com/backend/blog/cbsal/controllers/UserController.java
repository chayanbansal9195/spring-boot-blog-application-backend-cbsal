package com.backend.blog.cbsal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.cbsal.payloads.UserDto;
import com.backend.blog.cbsal.services.UserService;

@RestController
@RequestMapping("api/users/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	//POST - create user
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		
		UserDto createUserserDto = this.userService.createUser(userDto);
		
		return new ResponseEntity<>(createUserserDto,HttpStatus.CREATED);
	}
	
	
	//PUT - update user
	
	//DELETE - delete user
	
	//GET - user get
	
}
