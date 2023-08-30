package com.votingapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {
	
	private Integer id;

	private String name;
	
	private String party;
	
	private Integer votes;

}
