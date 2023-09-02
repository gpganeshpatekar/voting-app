package com.votingapp.services.impl;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.votingapp.config.AppConstants;
import com.votingapp.dtos.ElectionDTO;
import com.votingapp.entities.Election;
import com.votingapp.entities.User;
import com.votingapp.exceptions.ElectionNotFoundException;
import com.votingapp.exceptions.UserNotFoundException;
import com.votingapp.repositories.ElectionRepository;
import com.votingapp.repositories.UserRepository;
import com.votingapp.services.ElectionService;

@Service
public class ElectionServiceImpl implements ElectionService{
	

	@Autowired
	private ElectionRepository electionRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public ElectionDTO createElection(ElectionDTO electionDTO) throws InvalidObjectException {
		Election election = this.modelMapper.map(electionDTO, Election.class);
		SecurityContext context = SecurityContextHolder.getContext();
		UserDetails userDetails = (UserDetails)context.getAuthentication().getPrincipal();
		if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = this.userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException(AppConstants.USER_NOT_FOUND));
            election.setTotalVotes(0);
            election.setCreatedBy(user);
            Election saved = this.electionRepo.save(election);
            return this.modelMapper.map(saved, ElectionDTO.class);
            
        } else {
            throw new InvalidObjectException(AppConstants.INVALID_OPERATION);
        }
	}


	@Override
	public ElectionDTO getElectionById(Integer electionId) {
		Election election = this.electionRepo.findById(electionId).orElseThrow(() -> new ElectionNotFoundException(AppConstants.ELECTION_NOT_FOUND));
		ElectionDTO electionDTO = this.modelMapper.map(election, ElectionDTO.class);
		return electionDTO;
	}


	@Override
	public List<ElectionDTO> getAllElectionInfo() {
		List<Election> elections = this.electionRepo.findAll();
		List<ElectionDTO> electionDtos = elections.stream().map(e -> this.modelMapper.map(e, ElectionDTO.class)).collect(Collectors.toList());
		return electionDtos;
	}

}
