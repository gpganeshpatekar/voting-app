package com.votingapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
	
//	@Email
//	@Pattern(regexp = "[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+",message = "Please enter valid email address.")
	private String username;
	private String password;
	
	
}
