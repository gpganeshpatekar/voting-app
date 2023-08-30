package com.votingapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {
	
	private Integer id;
	private Integer electionId;
	private Integer voterId;
	private Integer candidateId;

}
