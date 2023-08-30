package com.votingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votingapp.entities.Election;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Integer> {

}
