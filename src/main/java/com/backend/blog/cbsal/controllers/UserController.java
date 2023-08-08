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
import com.backend.blog.cbsal.payloads.UserDto;
import com.backend.blog.cbsal.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users/v1")
public class UserController {

	@Autowired
	private UserService userService;

	// POST - create user
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		UserDto createUserserDto = this.userService.createUser(userDto);

		return new ResponseEntity<>(createUserserDto, HttpStatus.CREATED);
	}

	// PUT - update user

	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {

		UserDto updatedUserDto = this.userService.updateUser(userDto, userId);

		return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
	}

	// DELETE - delete user

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
	}

	// GET - All user get

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(), HttpStatus.OK);
	}

	// GET - single user get

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId) {
		return new ResponseEntity<UserDto>(this.userService.getUserById(userId), HttpStatus.OK);
	}

}
