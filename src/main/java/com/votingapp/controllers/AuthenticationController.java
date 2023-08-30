package com.votingapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votingapp.dtos.JwtRequest;
import com.votingapp.dtos.JwtResponse;
import com.votingapp.dtos.UserDto;
import com.votingapp.exceptions.InvalidCredentialsException;
import com.votingapp.security.JwtHelper;
import com.votingapp.services.impl.CustomUserDetailService;

@RestController
@RequestMapping("/voting-app/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) throws Exception {

//		authenticate
		this.authenticateUser(request.getUsername(), request.getPassword());
//		if authenticate then.. we have to create authenticationManager bean in security configuration file..
		UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());

		String token = this.helper.generateToken(userDetails);
		
		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setToken(token);
		jwtResponse.setUser(this.modelMapper.map(userDetails, UserDto.class));
		
		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
	}
	

	private void authenticateUser(String username, String password) throws Exception {

		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new InvalidCredentialsException("Invalid Login Credentials..");
		} catch (DisabledException e) {
			throw new Exception("User Is Not Active..");
		}
	}
	
	


	
	

}
