package com.votingapp.dtos;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto {

	private Integer id;
	
	private String username;

	private String password;

	private String email;

	private String phoneno;

	private Set<RoleDto> roles = new HashSet<>();

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
