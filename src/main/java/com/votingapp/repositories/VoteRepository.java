package com.votingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votingapp.entities.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

}
