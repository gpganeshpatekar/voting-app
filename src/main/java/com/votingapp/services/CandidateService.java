package com.votingapp.services;

import com.votingapp.dtos.CandidateDTO;

public interface CandidateService {
	
	CandidateDTO addCandidateToElection(Integer electionId, CandidateDTO candidateDTO);

}
