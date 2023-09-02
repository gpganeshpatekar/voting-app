package com.votingapp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.votingapp.entities.Role;
import com.votingapp.repositories.RoleRepository;

@SpringBootApplication
public class VotingAppApplication implements CommandLineRunner {
	
	@Autowired
	private RoleRepository roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(VotingAppApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

	@Override
	public void run(String... args) throws Exception {
		
		Role admin = new Role();
		admin.setId(5001);
		admin.setName("ROLE_ADMIN");
		
		Role normal = new Role();
		normal.setId(5002);
		normal.setName("ROLE_NORMAL");
		
		List<Role> roles = new ArrayList<>();
		roles.add(admin);
		roles.add(normal);
		
		roleRepo.saveAll(roles);
		
		
	}

}
