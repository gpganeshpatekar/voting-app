package com.votingapp.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "elections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Election {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer totalVotes;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<User> voters = new HashSet<>();
	@OneToMany(fetch = FetchType.EAGER)
	private List<Candidate> candidates;
	
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@OneToOne
	private User createdBy;
	
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updatedAt;
	
	@OneToOne
	private User updatedBy;

}
