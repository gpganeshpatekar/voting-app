package com.votingapp.services;

import com.votingapp.dtos.VoteDTO;

public interface VoteService {
	
	public String voteToCandidate(VoteDTO voteDto);

}
