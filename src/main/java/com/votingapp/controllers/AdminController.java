package com.votingapp.controllers;

import java.io.InvalidObjectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votingapp.dtos.CandidateDTO;
import com.votingapp.dtos.ElectionDTO;
import com.votingapp.services.CandidateService;
import com.votingapp.services.ElectionService;

@RestController
@RequestMapping("/voting-app")
public class AdminController {
	
	@Autowired
	private ElectionService electionService;
	
	@Autowired
	private CandidateService candidateService;
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/election")
	public ResponseEntity<ElectionDTO> createElection(@RequestBody ElectionDTO electionDTO) throws InvalidObjectException{
		ElectionDTO election = this.electionService.createElection(electionDTO);
		return new ResponseEntity<ElectionDTO>(election,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/candidate/{electionId}")
	public ResponseEntity<CandidateDTO> addCandidate(@PathVariable Integer electionId, @RequestBody CandidateDTO candidateDTO){
		CandidateDTO addCandidateToElection = this.candidateService.addCandidateToElection(electionId,candidateDTO);
		return new ResponseEntity<CandidateDTO>(addCandidateToElection,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/election/{electionId}")
	public ResponseEntity<ElectionDTO> getElectionInfo(@PathVariable Integer electionId){
		ElectionDTO electionDTO = this.electionService.getElectionById(electionId);
		return new ResponseEntity<ElectionDTO>(electionDTO,HttpStatus.OK);
	}
	
}
