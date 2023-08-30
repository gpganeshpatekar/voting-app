package com.votingapp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votingapp.config.AppConstants;
import com.votingapp.dtos.CandidateDTO;
import com.votingapp.entities.Candidate;
import com.votingapp.entities.Election;
import com.votingapp.exceptions.ElectionNotFoundException;
import com.votingapp.repositories.CandidateRepository;
import com.votingapp.repositories.ElectionRepository;
import com.votingapp.services.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {
	
	@Autowired
	private CandidateRepository candidateRepo;
	
	@Autowired
	private ElectionRepository electionRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CandidateDTO addCandidateToElection(Integer electionId,CandidateDTO candidateDTO) {
		Candidate candidate = this.modelMapper.map(candidateDTO, Candidate.class);
		Election election = this.electionRepo.findById(electionId).orElseThrow(() -> new ElectionNotFoundException(AppConstants.ELECTION_NOT_FOUND));
		Candidate saved = this.candidateRepo.save(candidate);
		election.getCandidates().add(candidate);
		this.electionRepo.save(election);
		return this.modelMapper.map(saved, CandidateDTO.class);
	}

}
