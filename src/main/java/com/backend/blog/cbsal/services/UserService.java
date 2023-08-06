package com.backend.blog.cbsal.services;

import java.util.List;

import com.backend.blog.cbsal.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	String deleteUser(Integer userId);
}
