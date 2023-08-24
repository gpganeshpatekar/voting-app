package com.votingapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votingapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	Optional<User> findById(Integer id);
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);

}
