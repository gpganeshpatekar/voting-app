package com.votingapp.dtos;

import java.util.HashSet;
import java.util.Set;

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

}
