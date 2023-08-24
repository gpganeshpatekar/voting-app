package com.votingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votingapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
