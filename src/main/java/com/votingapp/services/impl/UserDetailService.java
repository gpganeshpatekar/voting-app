package com.votingapp.services.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.votingapp.entities.Role;
import com.votingapp.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailService implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String phoneno;
	private Set<Role> roles = new HashSet<>();
	private List<GrantedAuthority> authorities;

	
	
	public UserDetailService(User user) {
		id = user.getId();
		username = user.getUsername();
		password = user.getPassword();
		email = user.getEmail();
		phoneno = user.getPhoneno();
		roles = user.getRoles();
		authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
