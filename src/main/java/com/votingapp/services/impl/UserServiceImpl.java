package com.votingapp.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.votingapp.dtos.UserDto;
import com.votingapp.entities.Role;
import com.votingapp.entities.User;
import com.votingapp.exceptions.EmailAlreadyExistException;
import com.votingapp.exceptions.PhoneNumberAlreadyExistException;
import com.votingapp.exceptions.UserNotFoundException;
import com.votingapp.exceptions.UsernameAlreadyExistException;
import com.votingapp.repositories.RoleRepository;
import com.votingapp.repositories.UserRepository;
import com.votingapp.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		String password = user.getPassword();
		System.out.println(password);
		if(checkIsUsernameExist(user.getUsername()) == false) {
			throw new UsernameAlreadyExistException("Username already exist");
		}else if(checkIsEmailExist(user.getEmail()) == false) {
			throw new EmailAlreadyExistException("Email already exist");
		}else if(checkIsPhoneNumberExist(user.getPhoneno()) == false) {
			throw new PhoneNumberAlreadyExistException("Phone number is already exist");
		}else {
			String encodePassword = this.passwordEncoder.encode(user.getPassword());
			user.setPassword(encodePassword);
			Role ROLE_NORMAL = this.roleRepo.findById(5002).get();
			user.getRoles().add(ROLE_NORMAL);
			User user1 = this.userRepo.save(user);
			return this.modelMapper.map(user1, UserDto.class);
			
		}
		
	}

	@Override
	public UserDto createAdmin(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		if(checkIsUsernameExist(user.getUsername()) == false) {
			throw new UsernameAlreadyExistException("Username already exist");
		}else if(checkIsEmailExist(user.getEmail()) == false) {
			throw new EmailAlreadyExistException("Email already exist");
		}else if(checkIsPhoneNumberExist(user.getPhoneno()) == false) {
			throw new PhoneNumberAlreadyExistException("Phone number is already exist");
		}else {
			String encodePassword = this.passwordEncoder.encode(user.getPassword());
			user.setPassword(encodePassword);
			Role ROLE_ADMIN = this.roleRepo.findById(5001).get();
			user.getRoles().add(ROLE_ADMIN);
			User admin1 = this.userRepo.save(user);
			return this.modelMapper.map(admin1, UserDto.class);	
		}
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User does not exist with this id"));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUserByUsername(String username) {
		User user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User does not exist with this username"));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User does not exist with this email"));
		return this.modelMapper.map(user, UserDto.class);
	}
	
	@Override
	public boolean checkIsUsernameExist(String username) {
		Optional<User> findByUsername = this.userRepo.findByUsername(username);
		if (findByUsername.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean checkIsEmailExist(String email) {
		Optional<User> findByEmail = this.userRepo.findByEmail(email);
		if (findByEmail.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean checkIsPhoneNumberExist(String phoneno) {
		Optional<User> findByPhoneno = this.userRepo.findByPhoneno(phoneno);
		if (findByPhoneno.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
