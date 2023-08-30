package com.votingapp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votingapp.config.AppConstants;
import com.votingapp.dtos.VoteDTO;
import com.votingapp.entities.Candidate;
import com.votingapp.entities.Election;
import com.votingapp.entities.User;
import com.votingapp.entities.Vote;
import com.votingapp.exceptions.ElectionNotFoundException;
import com.votingapp.exceptions.VoterNotFoundException;
import com.votingapp.repositories.CandidateRepository;
import com.votingapp.repositories.ElectionRepository;
import com.votingapp.repositories.UserRepository;
import com.votingapp.repositories.VoteRepository;
import com.votingapp.services.VoteService;

@Service
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	private VoteRepository voteRepo;
	
	@Autowired
	private ElectionRepository electionRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Autowired
	private CandidateRepository candidateRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public String voteToCandidate(VoteDTO voteDto) {
		Vote vote = this.modelMapper.map(voteDto, Vote.class);
		Election election = this.electionRepo.findById(vote.getElectionId()).orElseThrow(() -> new ElectionNotFoundException(AppConstants.ELECTION_NOT_FOUND));
		User voter = this.userRepo.findById(voteDto.getVoterId()).orElseThrow(() -> new VoterNotFoundException(AppConstants.VOTER_NOT_FOUND));
		boolean contains = election.getVoters().contains(voter);
		if(contains == false) {
			election.getVoters().add(voter);
			this.electionRepo.save(election);
			Candidate candidate = this.candidateRepo.findById(vote.getCandidateId()).get();
			candidate.setVotes(candidate.getVotes() + 1);
			this.candidateRepo.save(candidate);
			this.voteRepo.save(vote);
			return "You have successfully voted.";
			
		}else {
			return "You already voted";
		}
	}
}
