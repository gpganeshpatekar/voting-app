package com.votingapp.services;

import java.io.InvalidObjectException;
import java.util.List;

import com.votingapp.dtos.ElectionDTO;

public interface ElectionService {
	
	ElectionDTO createElection(ElectionDTO electionDTO) throws InvalidObjectException;
	
	ElectionDTO getElectionById(Integer electionId);
	
	List<ElectionDTO> getAllElectionInfo();

}
