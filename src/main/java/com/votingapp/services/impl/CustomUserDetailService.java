package com.votingapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.votingapp.exceptions.UserNotFoundException;
import com.votingapp.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepoository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from db by username
//		User user = this.userRepoository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND+username));
//		return user;
		return userRepoository.findByUsername(username).map(UserDetailService::new)
//				.orElseThrow(() -> new UserNotFoundException("Invalid username.." + username));
				.orElseThrow(() -> new UserNotFoundException("Invalid Login Credentials"));
	}

}
