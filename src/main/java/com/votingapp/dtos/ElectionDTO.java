package com.votingapp.dtos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.votingapp.entities.User;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElectionDTO {
	
	private Integer id;
	
	private String name;
	
	private Integer totalVotes;
	
	private Set<UserDto> voters = new HashSet<>();

	private List<CandidateDTO> candidates;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private LocalDateTime startDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private LocalDateTime endDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private LocalDateTime createdAt;
	
	private UserDto createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private LocalDateTime updatedAt;
	
	private UserDto updatedBy;

}
