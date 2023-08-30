package com.votingapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votingapp.dtos.ElectionDTO;
import com.votingapp.dtos.VoteDTO;
import com.votingapp.services.ElectionService;
import com.votingapp.services.VoteService;

@RestController
@RequestMapping("/voting-app")
public class VotingController {
	
	@Autowired
	private VoteService voteService;
	
	@Autowired
	private ElectionService electionService;
	
//	@PreAuthorize("hasRole('ROLE_NORMAL')")
	@PostMapping(value = "/vote",consumes = "application/json")
	public ResponseEntity<String> voteToCandidate(@RequestBody VoteDTO voteDTO){
		System.out.println(voteDTO.getCandidateId());
		String voted = this.voteService.voteToCandidate(voteDTO);
		return new ResponseEntity<String>(voted,HttpStatus.CREATED);
	}
	
//	@PreAuthorize("hasRole('ROLE_NORMAL') or hasRole('ROLE_ADMIN')")
	@GetMapping("/elections/elections-info")
	public ResponseEntity<List<ElectionDTO>> getAllElection(){
		List<ElectionDTO> allElectionInfo = this.electionService.getAllElectionInfo();
		return new ResponseEntity<List<ElectionDTO>>(allElectionInfo,HttpStatus.OK);
	}

}
