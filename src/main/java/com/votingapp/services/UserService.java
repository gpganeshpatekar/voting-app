package com.votingapp.services;

import java.util.List;

import com.votingapp.dtos.UserDto;


public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto createAdmin(UserDto user);
	
	List<UserDto> getAllUsers();
	
	UserDto getUserById(Integer id);
	
	UserDto getUserByUsername(String username);
	
	UserDto getUserByEmail(String email);
	

}
