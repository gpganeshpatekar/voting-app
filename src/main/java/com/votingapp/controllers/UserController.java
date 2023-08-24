package com.votingapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votingapp.dtos.UserDto;
import com.votingapp.services.UserService;

@RestController
@RequestMapping("/voting-app/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/register-admin",consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserDto> registerAdmin(@RequestBody UserDto userDto){
		UserDto admin = this.userService.createAdmin(userDto);
		return new ResponseEntity<UserDto>(admin,HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/register",consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		UserDto user1 = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(user1,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/",produces = "application/json")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users = this.userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}",produces = "application/json")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
		UserDto user = this.userService.getUserById(id);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByUsername/{username}",produces = "application/json")
	public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){
		UserDto user = this.userService.getUserByUsername(username);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByEmail/{email}",produces = "application/json")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
		UserDto user = this.userService.getUserByEmail(email);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}

}
